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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Astrologue;
import metier.modele.Cartomancien;
import metier.modele.Client;
import metier.modele.Medium;
import metier.modele.Spirite;

/**
 *
 * @author adamchellaoui
 */
public class ActionRechercheMediumSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Astrologue> astrologues= (List<Astrologue>) request.getAttribute("astrologues");
        List<Spirite> spirites= (List<Spirite>) request.getAttribute("spirites");
        List<Cartomancien> cartomanciens= (List<Cartomancien>) request.getAttribute("cartomanciens");
        
        JsonObject mediums= new JsonObject();
        
        JsonArray jsonListeAstrologues= new JsonArray();
        for(Astrologue astrologue : astrologues) {
            JsonObject jsonAstrologue = new JsonObject();
            
            jsonAstrologue.addProperty("id",astrologue.getId());
            jsonAstrologue.addProperty("presentation",astrologue.getPresentation());
            jsonAstrologue.addProperty("denomination",astrologue.getDenomination());
            jsonAstrologue.addProperty("genre",astrologue.getGenre());
            jsonAstrologue.addProperty("formation",astrologue.getFormation());
            jsonAstrologue.addProperty("promotion",astrologue.getPromotion());
            
            jsonListeAstrologues.add(jsonAstrologue);
        }
        mediums.add("astrologues",jsonListeAstrologues);
        
        
        JsonArray jsonListeSpirites= new JsonArray();
        for(Spirite spirite : spirites) {
            JsonObject jsonSpirite = new JsonObject();
            
            jsonSpirite.addProperty("id",spirite.getId());
            jsonSpirite.addProperty("presentation",spirite.getPresentation());
            jsonSpirite.addProperty("denomination",spirite.getDenomination());
            jsonSpirite.addProperty("genre",spirite.getGenre()); 
            jsonSpirite.addProperty("support",spirite.getSupport());
            
            jsonListeSpirites.add(jsonSpirite);
        }
        mediums.add("spirites",jsonListeSpirites);
        
        
        JsonArray jsonListeCartomanciens= new JsonArray();
        for(Cartomancien cartomancien : cartomanciens) {
            JsonObject jsonCartomancien = new JsonObject();
            
            jsonCartomancien.addProperty("id",cartomancien.getId());
            jsonCartomancien.addProperty("presentation",cartomancien.getPresentation());
            jsonCartomancien.addProperty("denomination",cartomancien.getDenomination());
            jsonCartomancien.addProperty("genre",cartomancien.getGenre()); 
            
            jsonListeCartomanciens.add(jsonCartomancien);
        }
        mediums.add("cartomanciens",jsonListeCartomanciens);
        
        
        PrintWriter out = this.getWriter(response); 
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(mediums, out);
        out.close();   
    }
}
