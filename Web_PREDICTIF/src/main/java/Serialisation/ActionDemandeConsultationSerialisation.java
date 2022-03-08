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
import metier.modele.Consultation;

/**
 *
 * @author adamchellaoui
 */
public class ActionDemandeConsultationSerialisation extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Consultation consultation=(Consultation) request.getAttribute("consultation");
        JsonObject container = new JsonObject();
        JsonObject consultationJSON = new JsonObject();
        consultationJSON.addProperty("medium",consultation.getMedium().getDenomination());
        if (consultation!=null) {
        container.addProperty("demande", true);}
        else {
        container.addProperty("demande", false);    
        }
        container.add("consultation",consultationJSON) ;
        PrintWriter out = this.getWriter(response); 
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();   
    }
}
