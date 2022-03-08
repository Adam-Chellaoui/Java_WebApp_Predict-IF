/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Medium;
import metier.modele.Employe;
import metier.service.Service;

/**
 *
 * @author rimbenzekri
 */
public class ActionStatistiques extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        Service service= new Service();
        
        List<Medium> mediums=service.getMediums();
        request.setAttribute("mediums",mediums);
        
        List<Employe> employes=service.getEmployes();
        request.setAttribute("employes",employes);
        
        List<Medium> mediumsTop5=service.topMediums(5);
        request.setAttribute("top5",mediumsTop5);
    }
    
}
