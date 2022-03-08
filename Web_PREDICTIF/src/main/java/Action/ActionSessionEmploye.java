/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Employe;
import metier.service.Service;

/**
 *
 * @author rimbenzekri
 */
public class ActionSessionEmploye extends Action {
    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        HttpSession session = request.getSession(true);
        long idEmp=(long) session.getAttribute("idEmp");
        Employe employe=service.chercherEmploye(idEmp);
        request.setAttribute("employe",employe);
    }
}

