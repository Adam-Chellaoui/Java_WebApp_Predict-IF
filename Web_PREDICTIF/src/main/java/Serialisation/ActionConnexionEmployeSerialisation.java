package Serialisation;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Employe;

/**
 *
 * @author Serenz
 */
public class ActionConnexionEmployeSerialisation extends Serialisation{
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
        
        Employe employe=(Employe) request.getAttribute("employe");
        JsonObject container = new JsonObject();
        JsonObject clientJSON = new JsonObject();
        if (employe!=null){
        clientJSON.addProperty("id",employe.getId());
        clientJSON.addProperty("nom",employe.getNom());
        clientJSON.addProperty("prenom",employe.getPrenom());
        clientJSON.addProperty("mail",employe.getMail());
        container.addProperty("connexion", true);}
        else {
        container.addProperty("connexion", false);    
        }
        container.add("employe", clientJSON);
        PrintWriter out = this.getWriter(response); 
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();   
    }
}
