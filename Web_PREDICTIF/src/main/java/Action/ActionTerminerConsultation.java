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
import metier.modele.Consultation;
import metier.service.Service;

/**
 *
 * @author adamchellaoui
 */
public class ActionTerminerConsultation extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        HttpSession session = request.getSession(true);
        Consultation consultation=(Consultation) session.getAttribute("consultation");
        String commentaire= request.getParameter("commentaire");
        try {
            service.terminerConsultation(consultation, commentaire);
        } catch (Exception ex) {
            Logger.getLogger(ActionTerminerConsultation.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.removeAttribute("consultation");
    }
    
}
