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
public class Medium {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    String presentation;
    String genre;
    String denomination;
    int nombreConsultations;

    public Medium() {
    }
    

    public Medium(String presentation, String genre, String denomination, int nombreConsultations) {
        this.presentation = presentation;
        this.genre = genre;
        this.denomination = denomination;
        this.nombreConsultations = nombreConsultations;
    }

    public int getNombreConsultations() {
        return nombreConsultations;
    }

    public void setNombreConsultations(int nombreConsultations) {
        this.nombreConsultations = nombreConsultations;
    }
    
    

    public void setId(Long id) {
        this.id = id;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Long getId() {
        return id;
    }

    public String getPresentation() {
        return presentation;
    }

    public String getGenre() {
        return genre;
    }

    public String getDenomination() {
        return denomination;
    }

    @Override
    public String toString() {
        return "Medium{" + "id=" + id + ", presentation=" + presentation + ", genre=" + genre + ", denomination=" + denomination + ", nombreConsultations=" + nombreConsultations + '}';
    }

    
    
    
    
    
    
    
    
}
