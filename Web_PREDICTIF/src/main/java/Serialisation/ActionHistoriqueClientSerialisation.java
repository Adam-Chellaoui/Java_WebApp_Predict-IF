/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Astrologue;
import metier.modele.Cartomancien;
import metier.modele.Consultation;
import metier.modele.Spirite;

/**
 *
 * @author adamchellaoui
 */
public class ActionHistoriqueClientSerialisation extends Serialisation{
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Consultation> consultations=(List<Consultation>) request.getAttribute("consultations");
        
        JsonObject container= new JsonObject();
        
        JsonArray jsonListeConsultations= new JsonArray();
        
        
        for(Consultation consultation : consultations) {
            JsonObject jsonConsultation = new JsonObject();
            jsonConsultation.addProperty("medium",consultation.getMedium().getDenomination());
            Date date=consultation.getDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            String dateString=simpleDateFormat.format(date);
            jsonConsultation.addProperty("date",dateString);
            jsonConsultation.addProperty("duree",consultation.getDuree());
            
            jsonListeConsultations.add(jsonConsultation);
        }
        container.add("consultations",jsonListeConsultations);
        
        PrintWriter out = this.getWriter(response); 
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();   
    }
    
}
