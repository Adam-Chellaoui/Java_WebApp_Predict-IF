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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Medium;
import metier.modele.Employe;
/**
 *
 * @author rimbenzekri
 */
public class ActionStatistiquesSerialisation extends Serialisation{

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject statistiques= new JsonObject();
        
        List<Medium> ListeMediums= (List<Medium>) request.getAttribute("mediums");
        
        JsonArray jsonListeMediums= new JsonArray();
        for(Medium medium : ListeMediums) {
            JsonObject jsonMedium = new JsonObject();
            
            jsonMedium.addProperty("nombre-consultations",medium.getNombreConsultations());
            jsonMedium.addProperty("denomination",medium.getDenomination());
            
            jsonListeMediums.add(jsonMedium);
        }
        statistiques.add("mediums",jsonListeMediums);
        
        //Pour le nombre de consultations par employé
        List<Employe> ListeEmployes= (List<Employe>) request.getAttribute("employes");
        
        JsonArray jsonListeEmployes= new JsonArray();
        for(Employe employe : ListeEmployes) {
            JsonObject jsonEmploye = new JsonObject();
            
            jsonEmploye.addProperty("nombre-consultations",employe.getNombreConsultations());
            jsonEmploye.addProperty("prenom",employe.getPrenom());
            jsonEmploye.addProperty("nom",employe.getNom());
            
            jsonListeEmployes.add(jsonEmploye);
        }
        statistiques.add("employes",jsonListeEmployes);
        
        //Pour le nombre de consultations par médium
        List<Medium> ListeMediumsTop5= (List<Medium>) request.getAttribute("top5");
        
        JsonArray jsonMediumsTop5= new JsonArray();
        for(Medium topMedium : ListeMediumsTop5) {
            JsonObject jsonMediumTop5 = new JsonObject();
            
            jsonMediumTop5.addProperty("nombre-consultations",topMedium.getNombreConsultations());
            jsonMediumTop5.addProperty("denomination",topMedium.getDenomination());
            
            jsonMediumsTop5.add(jsonMediumTop5);
        }
        statistiques.add("top5",jsonMediumsTop5);
        
        PrintWriter out = this.getWriter(response); 
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(statistiques, out);
        out.close(); 
    }
    
}
