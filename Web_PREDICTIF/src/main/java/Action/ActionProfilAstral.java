/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.service.Service;

/**
 *
 * @author adamchellaoui
 */
public class ActionProfilAstral extends Action {
    
    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        HttpSession session = request.getSession(true);
        Consultation consultation=(Consultation) session.getAttribute("consultation");
        Client client=consultation.getClient();      
        List<String> profil = service.getProfilAstral(client);
        request.setAttribute("profil", profil);

    }
}
