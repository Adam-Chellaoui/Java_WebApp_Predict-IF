package Action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.modele.Employe;
import metier.service.Service;
/**
 *
 * @author Serenz
 */

public class ActionConnexionEmploye extends Action{
    
    @Override
    public void execute(HttpServletRequest request) {
        Service service= new Service();
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        Employe employe=service.authentifierEmploye(login,password);
        request.setAttribute("employe",employe);
        if (employe!=null) {
        HttpSession session = request.getSession(true);
        long id=employe.getId();
        session.setAttribute("idEmp",id);
        }
    }
}
