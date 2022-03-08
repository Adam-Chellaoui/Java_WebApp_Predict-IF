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
import java.sql.Time;
        import java.util.Date;
/**
 *
 * @author adamchellaoui
 */
@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    Date date;
    long duree;
    String commentaire;
    Client client;
    Employe employe;
    Medium medium;

    public Consultation(Date date, long duree, String commentaire, Client client, Employe employe, Medium medium) {
        this.date = date;
        this.duree = duree;
        this.commentaire = commentaire;
        this.client = client;
        this.employe = employe;
        this.medium = medium;
    }

    public Consultation() {
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", date=" + date + ", duree=" + duree + ", commentaire=" + commentaire + ", client=" + client + ", employe=" + employe + ", medium=" + medium + '}';
    }

    

   

    public long getDuree() {
        return duree;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Client getClient() {
        return client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    

    public void setDuree(long duree) {
        this.duree = duree;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }
    
    
    
    
    
    
}
