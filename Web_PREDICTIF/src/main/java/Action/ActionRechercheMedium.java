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
import metier.modele.Medium;
import metier.modele.Spirite;
import metier.service.Service;

/**
 *
 * @author adamchellaoui
 */
public class ActionRechercheMedium extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        Service service= new Service();
        List<Astrologue> astrologues=service.getAstrologues();
        List<Spirite> spirites=service.getSpirites();
        List<Cartomancien> cartomanciens=service.getCartomanciens();
        request.setAttribute("astrologues",astrologues);
        request.setAttribute("spirites",spirites);
        request.setAttribute("cartomanciens",cartomanciens);
  
    }
}
