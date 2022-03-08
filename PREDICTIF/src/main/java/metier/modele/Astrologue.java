/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author adamchellaoui
 */
@Entity
public class Astrologue extends Medium {
    
    String formation;
    String promotion;

    public Astrologue(String formation, String promotion, String presentation, String genre, String denomination, int nombreConsultations) {
        super(presentation, genre, denomination, nombreConsultations);
        this.formation = formation;
        this.promotion = promotion;
    }

    

    public Astrologue() {
    }
    
    

    public String getFormation() {
        return formation;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
    
    
    
}
