/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.service.Service;

/**
 *
 * @author adamchellaoui
 */
public class ActionPredictions extends Action {
    
    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        HttpSession session = request.getSession(true);
        String amourString=request.getParameter("amour");
        String santeString=request.getParameter("sante");
        String travailString=request.getParameter("travail");
        int amour=Integer.parseInt(amourString);
        int sante=Integer.parseInt(santeString);
        int travail=Integer.parseInt(travailString);
        Consultation consultation=(Consultation) session.getAttribute("consultation");
        Client client=consultation.getClient(); 
        List<String> predictions=null;
        try {
            predictions = service.getPredictions(amour, sante, travail, client);
        } catch (IOException ex) {
            Logger.getLogger(ActionPredictions.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("predictions", predictions);
    }
}
