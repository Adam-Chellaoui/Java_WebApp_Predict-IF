/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Client;
import metier.modele.Employe;
/**
 *
 * @author adamchellaoui
 */
public class EmployeDao {
    public EmployeDao() {
    }

    public void creer(Employe employe) {
        JpaUtil.obtenirContextePersistance().persist(employe);
    }

    public void supprimer(Employe employe) {
        JpaUtil.obtenirContextePersistance().remove(employe);
    }

    public Employe modifier(Employe employe) {
        return JpaUtil.obtenirContextePersistance().merge(employe);
    }

    public Employe chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Employe.class, id);
    }
    
    public List<Employe> chercherDisponibles(String genre) {
        //On trie par ordre décroissant de tempsTravail pour préparer à prendre celui qui a le moins travaillé
        String jpql = "select e from Employe e where e.disponible = :unEtat and e.genre=:unGenre order by e.tempsTravail" ; 
        TypedQuery query; 
        query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Employe.class);
        query.setParameter("unEtat", true);
        query.setParameter("unGenre",genre);
        return  query.getResultList();
    }
    
     public Employe chercherParMail(String mail) {
        String s = "select e from Employe e where e.mail=:unMail";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        query.setParameter("unMail",mail);
        return (Employe) query.getSingleResult();
    }

    public List<Employe> chercherTous() {
        String s = "select c from Employe c order by c.nom asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        return query.getResultList();
    }
    
}
