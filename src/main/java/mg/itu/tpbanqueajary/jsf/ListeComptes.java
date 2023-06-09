/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueajary.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import mg.itu.tpbanqueajary.ejb.GestionnaireCompte;
import mg.itu.tpbanqueajary.entities.CompteBancaire;
import mg.itu.tpbanqueajary.jsf.util.Util;

/**
 *
 * @author ajari
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    private List<CompteBancaire> Comptes;

    @EJB
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

    public List<CompteBancaire> getAllComptes() {
        if (Comptes == null) {
            Comptes = gestionnaireCompte.getAllComptes();
        }
        return Comptes;
    }

    public String supprimerCompte(CompteBancaire c) {
        if (c != null) {
            gestionnaireCompte.supprimerCompte(c);
            Util.addFlashInfoMessage("suppression effectuée");
            return "ListeCompte?faces-redirect=true";
        }
        Util.addFlashInfoMessage("suppression non effectuée");
        return null;
    }
}
