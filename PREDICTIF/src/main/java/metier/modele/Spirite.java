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
public class Spirite extends Medium {
    
    String support;

    public Spirite(String support, String presentation, String genre, String denomination, int nombreConsultations) {
        super(presentation, genre, denomination, nombreConsultations);
        this.support = support;
    }

    

    public Spirite() {
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
    
    
    
    
    
}
