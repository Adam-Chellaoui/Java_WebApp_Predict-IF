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
public class Employe {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    String nom;
    String prenom;
    String mail;
    String telephone;
    String genre;
    Boolean disponible;
    long tempsTravail;
    String motDePasse;
    int nombreConsultations;

    public Employe(String nom, String prenom, String mail, String telephone, String genre, Boolean disponible, long tempsTravail, String motDePasse, int nombreConsultations) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.telephone = telephone;
        this.genre = genre;
        this.disponible = disponible;
        this.tempsTravail = tempsTravail;
        this.motDePasse = motDePasse;
        this.nombreConsultations = nombreConsultations;
    }

    public Employe() {
    }

    public int getNombreConsultations() {
        return nombreConsultations;
    }

    public void setNombreConsultations(int nombreConsultations) {
        this.nombreConsultations = nombreConsultations;
    }


    public long getTempsTravail() {
        return tempsTravail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setTempsTravail(long tempsTravail) {
        this.tempsTravail = tempsTravail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }


    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Boolean getDisponible() {
        return disponible;
    }
    
    

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getGenre() {
        return genre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", telephone=" + telephone + ", genre=" + genre + ", disponible=" + disponible + ", tempsTravail=" + tempsTravail + ", motDePasse=" + motDePasse + ", nombreConsultations=" + nombreConsultations + '}';
    }

    
    
    
    
        
    
}
