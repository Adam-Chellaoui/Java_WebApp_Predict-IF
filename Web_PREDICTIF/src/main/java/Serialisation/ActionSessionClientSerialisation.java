/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisation;

import Action.Action;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.modele.Client;
import metier.service.Service;

/**
 *
 * @author adamchellaoui
 */
public class ActionSessionClientSerialisation extends Serialisation {
     @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Client client=(Client) request.getAttribute("client");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date javaDate=client.getDateNaissance();
        String date=dateFormat.format(javaDate);
        JsonObject clientJSON = new JsonObject();
        clientJSON.addProperty("id",client.getId());
        clientJSON.addProperty("nom",client.getNom());
        clientJSON.addProperty("prenom",client.getPrenom());
        clientJSON.addProperty("date",date);
        clientJSON.addProperty("mail",client.getMail());
        clientJSON.addProperty("telephone",client.getTelephone());
        clientJSON.addProperty("adresse",client.getAdressePostale());
        clientJSON.addProperty("genre",client.getGenre());
        clientJSON.addProperty("animal",client.getProfil().getAnimal());
        clientJSON.addProperty("chinois",client.getProfil().getChinois());
        clientJSON.addProperty("zodiaque",client.getProfil().getZodiaque());
        clientJSON.addProperty("couleur",client.getProfil().getCouleur());
        PrintWriter out = this.getWriter(response); 
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(clientJSON, out);
        out.close();   
    }
}
