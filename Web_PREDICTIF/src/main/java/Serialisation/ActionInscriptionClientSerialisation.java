/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Client;

/**
 *
 * @author adamchellaoui
 */
public class ActionInscriptionClientSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Client client=(Client) request.getAttribute("client");
        JsonObject container = new JsonObject();
        JsonObject clientJSON = new JsonObject();
        clientJSON.addProperty("id",client.getId());
        clientJSON.addProperty("nom",client.getNom());
        clientJSON.addProperty("prenom",client.getPrenom());
        clientJSON.addProperty("mail",client.getMail());
        if (client!=null) {
        container.addProperty("inscription", true);}
        else {
        container.addProperty("inscription", false);    
        }
        container.add("client", clientJSON);
        
        PrintWriter out = this.getWriter(response); 
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();   
    }
}
