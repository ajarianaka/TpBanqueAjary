/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanqueajary.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import mg.itu.tpbanqueajary.entities.CompteBancaire;

/**
 *
 * @author ajari
 */
@Startup
@Singleton
public class Init {

    @EJB
    //@Inject
    private GestionnaireCompte gestEjb;
   
    @PostConstruct
    public void initDb(){   
        if(gestEjb.nbComptes()>0){
            return;
        } 
        gestEjb.creerCompte(new CompteBancaire("John Lennon", 150000));
        gestEjb.creerCompte(new CompteBancaire("Paul McCartney", 950000));
        gestEjb.creerCompte(new CompteBancaire("Ringo Starr", 20000));
        gestEjb.creerCompte(new CompteBancaire("Georges Harrisson", 100000)); 
    }
}
