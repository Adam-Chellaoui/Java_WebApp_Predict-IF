/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;

/**
 *
 * @author adamchellaoui
 */
public class ConsultationDao {
    public ConsultationDao() {
    }

    public void creer(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }

    public void supprimer(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().remove(consultation);
    }

    public Consultation modifier(Consultation consultation) {
        return JpaUtil.obtenirContextePersistance().merge(consultation);
    }

    public Consultation chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Consultation.class, id);
    }
    
    public List<Consultation> chercherTous() {
        String s = "select c from Consultation c";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
        return query.getResultList();
    }
    
    public Consultation chercherEnCours(Employe employe) {
        String s="select c from Consultation c where c.employe= :unEmploye and c.duree=:uneDuree";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
        query.setParameter("uneDuree",0);
        query.setParameter("unEmploye",employe);
        return (Consultation) query.getSingleResult();
    }
    
    //Pour ne pas prendre en compte la consultation qui débute
    public List<Consultation> historiqueDuClient (Client client,Consultation consultation) {
        String s = "select c from Consultation c where c.client= :unClient and c!=:uneConsultation";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
        query.setParameter("unClient",client);
        query.setParameter("uneConsultation",consultation);
        return query.getResultList();
    }
    
    //Pour l'historique global
    public List<Consultation> historiqueDuClient (Client client) {
        String s = "select c from Consultation c where c.client= :unClient";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
        query.setParameter("unClient",client);
        return query.getResultList();
    }
    
    
    //Ancienne version lorsque nombreConsultations n'était pas encore un attribut de Medium
    /*
    public List<Medium> topTheMediums (int n) {
        String jpql = "select c.medium,count(c.medium) AS nb from Consultation c group by c.medium order by nb desc";
        TypedQuery query;
        query=JpaUtil.obtenirContextePersistance().createQuery(jpql, Consultation.class);
        List<Object[]> results=query.setMaxResults(n).getResultList();
        List<Medium> mediums;
        mediums = new ArrayList<>();
        for (Object[] result : results) {
        Medium medium = (Medium) result[0];
        mediums.add(medium);
        int count = ((Number) result[1]).intValue();
        }
        
        return mediums;
    }*/
}
