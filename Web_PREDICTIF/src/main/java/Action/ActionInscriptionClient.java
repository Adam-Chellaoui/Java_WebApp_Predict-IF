package Action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Client;
import metier.service.Service;


/**
 *
 * @author adamchellaoui
 */
public class ActionInscriptionClient extends Action {
    

    @Override
    public void execute(HttpServletRequest request) {
        try {
            Service service= new Service();
            String prenom=request.getParameter("prenom");
            String nom=request.getParameter("nom");
            String dateString=request.getParameter("date");
            String mail=request.getParameter("mail");
            String adresse=request.getParameter("adresse");
            String telephone=request.getParameter("telephone");
            String genre=request.getParameter("genre");
            String mdp=request.getParameter("mdp");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            
            try {
                date = simpleDateFormat.parse(dateString);
            } catch (ParseException ex) {
                Logger.getLogger(ActionInscriptionClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Client newClient= new Client(nom,prenom,adresse,telephone,genre,date,mail,mdp);
            
            
            Client client=service.inscrireClient(newClient);
            request.setAttribute("client",client);
            
            
        } catch (IOException ex) {
            Logger.getLogger(ActionInscriptionClient.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
  
    }

    
    

}
