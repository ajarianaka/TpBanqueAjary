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
@Named(value = "transfert")
@ViewScoped
public class Transfert implements Serializable {

    private Long idSource;
    private Long idDestination;
    private int amount;
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    /**
     * Creates a new instance of Transfert
     */
    public Transfert() {
    }
    
    public Long getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public String transferAmount() {
        boolean erreur = false;
        CompteBancaire source = gestionnaireCompte.getCompte(idSource);
        if (source == null) {
            erreur = true;
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:idSource");
        }
        CompteBancaire destination = gestionnaireCompte.getCompte(idDestination);
        if (destination == null) {
            erreur = true;
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:idDestination");
        }
        if (!erreur) {
            if (source.getSolde() < amount) {
                Util.messageErreur("Montant insuffisant", "Montant insuffisant", "form:amount");
                return null;
            } else {
                gestionnaireCompte.transferer(source, destination, amount);
                Util.addFlashInfoMessage("Transfert de "+this.amount+" correctement effectué depuis le compte de "+source.getNom()+" vers le compte de "+destination.getNom());
                return "ListeCompte?faces-redirect=true";
            }
        }
        
        return null;
    }
}
