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

/**
 *
 * @author ajari
 */
@Named(value = "modifCompte")
@ViewScoped
public class ModifCompte implements Serializable {
    private Long id;
    private CompteBancaire compte;
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    /**
     * Creates a new instance of ModifCompte
     */
    public ModifCompte() {
    }
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getDetails() {
        return compte;
    }

    public String update() {
        compte = gestionnaireCompte.update(compte);
        return "ListeCompte?faces-redirect=true";
    }

    public void loadCompte() {
        this.compte = gestionnaireCompte.getCompte(id);
    }
}
