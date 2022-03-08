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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamchellaoui
 */
public class ActionPredictionsSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> predictions=(List<String>) request.getAttribute("predictions");
        JsonObject container= new JsonObject();
        JsonObject predictionsJSON=new JsonObject();
        predictionsJSON.addProperty("amour",predictions.get(0));
        predictionsJSON.addProperty("sante",predictions.get(1));
        predictionsJSON.addProperty("travail",predictions.get(2));
        container.add("predictions",predictionsJSON);
        PrintWriter out = this.getWriter(response); 
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();   
    }
}
