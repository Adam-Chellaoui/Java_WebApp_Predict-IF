/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Astrologue;
import metier.modele.Cartomancien;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Spirite;
import metier.service.Service;

/**
 *
 * @author adamchellaoui
 */
public class ActionHistoriqueClient extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        Service service= new Service();
        HttpSession session = request.getSession(true);
        long id=(long) session.getAttribute("id");
        Client client=service.chercherClient(id);
        List<Consultation> consultations=service.historiqueClient(client);
        request.setAttribute("consultations", consultations);
  
    }
}
