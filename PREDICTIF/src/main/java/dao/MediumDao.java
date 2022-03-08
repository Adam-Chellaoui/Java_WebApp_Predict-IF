/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Astrologue;
import metier.modele.Cartomancien;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.Spirite;

/**
 *
 * @author adamchellaoui
 */
public class MediumDao {
    public MediumDao() {
    }

    public void creer(Medium medium) {
        JpaUtil.obtenirContextePersistance().persist(medium);
    }

    public void supprimer(Medium medium) {
        JpaUtil.obtenirContextePersistance().remove(medium);
    }

    public Medium modifier(Medium medium) {
        return JpaUtil.obtenirContextePersistance().merge(medium);
    }

    public Medium chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Medium.class, id);
    }
    
    public List<Medium> topTheMediums (int n) {
        String jpql = "select m from Medium m order by m.nombreConsultations desc";
        TypedQuery query;
        query=JpaUtil.obtenirContextePersistance().createQuery(jpql,Medium.class);
        List<Medium> mediums=query.setMaxResults(n).getResultList();
        return mediums;
    }
   
    public List<Medium> getTheMediums () {
        String s = "select m from Medium m";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }
    
    public List<Medium> getTheMediums (Boolean disponible) {
        String jpql = "select e from Employe e where e.disponible = :unEtat and e.genre=:unGenre" ; 
        
        TypedQuery queryM; 
        queryM = JpaUtil.obtenirContextePersistance().createQuery(jpql, Employe.class);
        queryM.setParameter("unEtat", true);
        queryM.setParameter("unGenre","M");
        List<Medium> males=  queryM.getResultList();
        
        TypedQuery queryF; 
        queryF = JpaUtil.obtenirContextePersistance().createQuery(jpql, Employe.class);
        queryF.setParameter("unEtat", true);
        queryF.setParameter("unGenre","F");
        List<Medium> femelles=  queryF.getResultList();
        
        if (males.isEmpty()==true && femelles.isEmpty()==false) {
            String s = "select m from Medium m where m.genre=:unGenre";
            TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
            query.setParameter("unGenre","F");
            return query.getResultList();
        }
        else if (males.isEmpty()==false && femelles.isEmpty()==true) {
            String s = "select m from Medium m where m.genre=:unGenre";
            TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
            query.setParameter("unGenre","M");
            return query.getResultList();
        }
        else if (males.isEmpty()==false && femelles.isEmpty()==false) {
            String s = "select m from Medium m";
            TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
            return query.getResultList();
        }
        else {
            System.out.println("Aucun Medium Disponible.");
            return null;
        }
        
        
    }

    
    public List<Astrologue> getTheAstrologues () {
        String s = "select a from Astrologue a";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }
    
    public List<Astrologue> getTheAstrologues (Boolean disponible) {
        String jpql = "select e from Employe e where e.disponible = :unEtat and e.genre=:unGenre" ; 
        
        TypedQuery queryM; 
        queryM = JpaUtil.obtenirContextePersistance().createQuery(jpql, Employe.class);
        queryM.setParameter("unEtat", true);
        queryM.setParameter("unGenre","M");
        List<Medium> males=  queryM.getResultList();
        
        TypedQuery queryF; 
        queryF = JpaUtil.obtenirContextePersistance().createQuery(jpql, Employe.class);
        queryF.setParameter("unEtat", true);
        queryF.setParameter("unGenre","F");
        List<Medium> femelles=  queryF.getResultList();
        
        if (males.isEmpty()==true && femelles.isEmpty()==false) {
            String s = "select a from Astrologue a where a.genre=:unGenre";
            TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
            query.setParameter("unGenre","F");
            return query.getResultList();
        }
        else if (males.isEmpty()==false && femelles.isEmpty()==true) {
            String s = "select a from Astrologue a where a.genre=:unGenre";
            TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
            query.setParameter("unGenre","M");
            return query.getResultList();
        }
        else if (males.isEmpty()==false && femelles.isEmpty()==false) {
            String s = "select a from Astrologue a";
            TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
            return query.getResultList();
        }
        else {
            System.out.println("Aucun Astrologue Disponible.");
            return null;
        }
        
        
    }
    
    public List<Cartomancien> getTheCartomanciens () {
        String s = "select c from Cartomancien c";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }
    
    public List<Cartomancien> getTheCartomanciens (Boolean disponible) {
        String jpql = "select e from Employe e where e.disponible = :unEtat and e.genre=:unGenre" ; 
        
        TypedQuery queryM; 
        queryM = JpaUtil.obtenirContextePersistance().createQuery(jpql, Employe.class);
        queryM.setParameter("unEtat", true);
        queryM.setParameter("unGenre","M");
        List<Medium> males=  queryM.getResultList();
        
        TypedQuery queryF; 
        queryF = JpaUtil.obtenirContextePersistance().createQuery(jpql, Employe.class);
        queryF.setParameter("unEtat", true);
        queryF.setParameter("unGenre","F");
        List<Medium> femelles=  queryF.getResultList();
        
        if (males.isEmpty()==true && femelles.isEmpty()==false) {
            String s = "select c from Cartomancien c where c.genre=:unGenre";
            TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
            query.setParameter("unGenre","F");
            return query.getResultList();
        }
        else if (males.isEmpty()==false && femelles.isEmpty()==true) {
            String s = "select c from Cartomancien c where c.genre=:unGenre";
            TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
            query.setParameter("unGenre","M");
            return query.getResultList();
        }
        else if (males.isEmpty()==false && femelles.isEmpty()==false) {
            String s = "select c from Cartomancien c";
            TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
            return query.getResultList();
        }
        else {
            System.out.println("Aucun Cartomancien Disponible.");
            return null;
        }
        
        
    }
    
    public List<Spirite> getTheSpirites () {
        String s = "select s from Spirite s";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }
    
    public List<Spirite> getTheSpirites (Boolean disponible) {
        String jpql = "select e from Employe e where e.disponible = :unEtat and e.genre=:unGenre" ; 
        
        TypedQuery queryM; 
        queryM = JpaUtil.obtenirContextePersistance().createQuery(jpql, Employe.class);
        queryM.setParameter("unEtat", true);
        queryM.setParameter("unGenre","M");
        List<Medium> males=  queryM.getResultList();
        
        TypedQuery queryF; 
        queryF = JpaUtil.obtenirContextePersistance().createQuery(jpql, Employe.class);
        queryF.setParameter("unEtat", true);
        queryF.setParameter("unGenre","F");
        List<Medium> femelles=  queryF.getResultList();
        
        if (males.isEmpty()==true && femelles.isEmpty()==false) {
            String s = "select s from Spirite s where s.genre=:unGenre";
            TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
            query.setParameter("unGenre","F");
            return query.getResultList();
        }
        else if (males.isEmpty()==false && femelles.isEmpty()==true) {
            String s = "select s from Spirite s where s.genre=:unGenre";
            TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
            query.setParameter("unGenre","M");
            return query.getResultList();
        }
        else if (males.isEmpty()==false && femelles.isEmpty()==false) {
            String s = "select s from Spirite s";
            TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
            return query.getResultList();
        }
        else {
            System.out.println("Aucun Spirite Disponible.");
            return null;
        }
        
        
    }
    
}
