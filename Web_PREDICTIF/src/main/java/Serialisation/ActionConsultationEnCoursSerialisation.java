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
import javax.servlet.http.HttpSession;
import metier.modele.Consultation;
import metier.modele.Employe;

/**
 *
 * @author adamchellaoui
 */
public class ActionConsultationEnCoursSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Consultation consultation = (Consultation) request.getAttribute("consultationEnCours");
        JsonObject container = new JsonObject();
        if (consultation != null) {
            container.addProperty("encours", true);
            JsonObject consultationJSON = new JsonObject();
            consultationJSON.addProperty("prenomClient", consultation.getClient().getPrenom());
            consultationJSON.addProperty("nomClient",consultation.getClient().getNom());
            consultationJSON.addProperty("medium", consultation.getMedium().getDenomination());
            container.add("consultation", consultationJSON);
            HttpSession session = request.getSession(true);
            session.setAttribute("consultation",consultation);
        } else {
            container.addProperty("encours", false);
        }
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
