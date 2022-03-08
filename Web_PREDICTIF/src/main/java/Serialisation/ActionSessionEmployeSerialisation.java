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
import metier.modele.Employe;
//import metier.service.Service;

/**
 *
 * @author rimbenzekri
 */
public class ActionSessionEmployeSerialisation extends Serialisation {
     @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Employe employe=(Employe) request.getAttribute("employe");
        JsonObject container= new JsonObject();
        JsonObject employeJSON = new JsonObject();
        employeJSON.addProperty("id",employe.getId());
        employeJSON.addProperty("nom",employe.getNom());
        employeJSON.addProperty("prenom",employe.getPrenom());
        employeJSON.addProperty("mail",employe.getMail());
        employeJSON.addProperty("telephone",employe.getTelephone());
        employeJSON.addProperty("genre",employe.getGenre());
        container.add("employe",employeJSON);
        PrintWriter out = this.getWriter(response); 
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();   
    }
}

