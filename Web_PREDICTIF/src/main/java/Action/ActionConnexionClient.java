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
import metier.modele.Client;
import metier.service.Service;

/**
 *
 * @author adamchellaoui
 */

public class ActionConnexionClient extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Service service= new Service();
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        Client client=service.authentifierClient(login,password);
        request.setAttribute("client",client);
        if (client!=null) {
        HttpSession session = request.getSession(true);
        long id=client.getId();
        session.setAttribute("id",id);
        }
    }

}
