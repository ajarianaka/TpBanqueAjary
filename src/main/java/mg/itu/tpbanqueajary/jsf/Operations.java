/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueajary.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mg.itu.tpbanqueajary.ejb.GestionnaireCompte;
import mg.itu.tpbanqueajary.entities.CompteBancaire;
import mg.itu.tpbanqueajary.entities.OperationBancaire;

/**
 *
 * @author ajari
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

     private Long id;
    
    private CompteBancaire compte;
    
     private List<OperationBancaire> allOperations;
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    /**
     * Creates a new instance of Operation
     */
    public Operations() {
        this.allOperations = new ArrayList<>();
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<OperationBancaire> getAllOperations() {
        return allOperations;
    }
    public void loadOperations() {
        this.compte = gestionnaireCompte.getCompte(id);
        this.allOperations = compte.getOperations();
    }
}
