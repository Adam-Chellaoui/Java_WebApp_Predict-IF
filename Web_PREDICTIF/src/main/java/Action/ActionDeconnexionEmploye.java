/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adamchellaoui
 */
public class ActionDeconnexionEmploye extends Action{
    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.removeAttribute("idEmp");
        session.invalidate();
    }
}
