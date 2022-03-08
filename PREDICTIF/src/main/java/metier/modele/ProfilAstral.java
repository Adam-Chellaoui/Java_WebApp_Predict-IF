/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

/**
 *
 * @author adamchellaoui
 */
@Embeddable
public class ProfilAstral implements Serializable {
    
    String zodiaque;
    String chinois;
    String couleur;
    String animal;

    public ProfilAstral() {
    }

    public ProfilAstral(String zodiaque, String chinois, String couleur, String animal) {
        this.zodiaque = zodiaque;
        this.chinois = chinois;
        this.couleur = couleur;
        this.animal = animal;
    }

    public void setZodiaque(String zodiaque) {
        this.zodiaque = zodiaque;
    }

    public void setChinois(String chinois) {
        this.chinois = chinois;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    

    public String getZodiaque() {
        return zodiaque;
    }

    public String getChinois() {
        return chinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getAnimal() {
        return animal;
    }

    @Override
    public String toString() {
        return "ProfilAstral{" + "zodiaque=" + zodiaque + ", chinois=" + chinois + ", couleur=" + couleur + ", animal=" + animal + '}';
    }
    
    
    
}
