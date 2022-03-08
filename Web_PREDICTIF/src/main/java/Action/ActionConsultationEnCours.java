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
import metier.modele.Employe;
import metier.modele.Medium;
import metier.service.Service;

/**
 *
 * @author adamchellaoui
 */
public class ActionConsultationEnCours extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        HttpSession session = request.getSession(true);
        long id=(long) session.getAttribute("idEmp");
        Employe employe=service.chercherEmploye(id);
        Consultation consultation=null;
        consultation = service.chercherConsultationEnCours(employe);
        request.setAttribute("consultationEnCours",consultation);
    }
}
