/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Medium;
import metier.service.Service;

/**
 *
 * @author adamchellaoui
 */
public class ActionDemandeConsultation extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        String idMediumString=request.getParameter("medium");
        long idMedium=Long.parseLong(idMediumString);
        Medium medium=service.chercherMedium(idMedium);
        HttpSession session = request.getSession(true);
        long idClient=(long) session.getAttribute("id");
        Client client=service.chercherClient(idClient);
        Consultation consultation=null;
        try {
            consultation = service.demandeConsultation(client, medium);
        } catch (Exception ex) {
            Logger.getLogger(ActionDemandeConsultation.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("consultation",consultation);
    }
}
