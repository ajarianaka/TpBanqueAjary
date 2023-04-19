/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanqueajary.ejb;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import mg.itu.tpbanqueajary.entities.CompteBancaire;

/**
 *
 * @author ajari
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root", 
        password = "123456", 
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true"
        }
)
@Stateless
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        Query query = em.createQuery("select c from CompteBancaire c", CompteBancaire.class);
        return query.getResultList();
    }
    
    public Long nbComptes(){
        Query query = em.createQuery("select count(c) from CompteBancaire c"); 
        return (Long)query.getSingleResult();             
    }
    
    public CompteBancaire getCompte(Long idCompte) {
        return em.find(CompteBancaire.class, idCompte);
    }
    
    public CompteBancaire update(CompteBancaire compte) {
        return em.merge(compte);
    }
    
    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }

    public void deposer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.deposer(montant);
        update(compteBancaire);
    }
    
    public void retirer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.retirer(montant);
        update(compteBancaire);
    }
    
    public void supprimerCompte(CompteBancaire c) {
        if (!em.contains(c)) {
            c = em.merge(c);
        }
        em.remove(c);
    }
}
