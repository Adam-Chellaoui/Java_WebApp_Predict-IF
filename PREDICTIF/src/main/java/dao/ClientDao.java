/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Client;

/**
 *
 * @author adamchellaoui
 */
public class ClientDao {

    public ClientDao() {
    }

    public void creer(Client client) {
        JpaUtil.obtenirContextePersistance().persist(client);
    }

    public void supprimer(Client client) {
        JpaUtil.obtenirContextePersistance().remove(client);
    }

    public Client modifier(Client client) {
        return JpaUtil.obtenirContextePersistance().merge(client);
    }

    public Client chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Client.class, id);
    }
    
    public Client chercherParMail(String mail) {
        String s = "select c from Client c where c.mail=:unMail";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        query.setParameter("unMail",mail);
        return (Client) query.getSingleResult();
    }

    public List<Client> chercherTous() {
        String s = "select c from Client c order by nom asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        return query.getResultList();
    }

}
