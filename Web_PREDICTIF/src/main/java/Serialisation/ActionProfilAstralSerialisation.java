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
import metier.modele.Consultation;

/**
 *
 * @author adamchellaoui
 */
public class ActionProfilAstralSerialisation extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> profil=(List<String>) request.getAttribute("profil");
        JsonObject container= new JsonObject();
        JsonObject profilJSON=new JsonObject();
        profilJSON.addProperty("animal",profil.get(0));
        profilJSON.addProperty("chinois",profil.get(1));
        profilJSON.addProperty("couleur",profil.get(2));
        profilJSON.addProperty("zodiaque",profil.get(3));
        container.add("profil",profilJSON);
        PrintWriter out = this.getWriter(response); 
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();   
    }
    
}
