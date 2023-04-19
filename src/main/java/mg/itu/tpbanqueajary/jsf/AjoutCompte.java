/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueajary.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import mg.itu.tpbanqueajary.ejb.GestionnaireCompte;
import mg.itu.tpbanqueajary.entities.CompteBancaire;
import mg.itu.tpbanqueajary.jsf.util.Util;

/**
 *
 * @author ajari
 */
@Named(value = "ajoutCompte")
@ViewScoped
public class AjoutCompte implements Serializable {

    private String nom;
    private int solde;
    @EJB
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public int getSolde() {
        return solde;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String action(){
        CompteBancaire object = new CompteBancaire(nom, solde);
        
        gestionnaireCompte.creerCompte(object);
        
        Util.addFlashInfoMessage("Compte de "+nom+" correctement effectué");
                return "ListeCompte?faces-redirect=true";
    }
}
