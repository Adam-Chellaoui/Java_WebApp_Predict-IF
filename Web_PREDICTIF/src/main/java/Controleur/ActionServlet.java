package Controleur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Action.Action;
import Action.ActionCommencerConsultation;
import Action.ActionConnexionClient;
import Action.ActionConnexionEmploye;
import Action.ActionConsultationEnCours;
import Action.ActionDeconnexionClient;
import Action.ActionDeconnexionEmploye;
import Action.ActionDemandeConsultation;
import Action.ActionHistoriqueClient;
import Action.ActionHistoriqueClientEmploye;
import Action.ActionInscriptionClient;
import Action.ActionNotifierClient;
import Action.ActionPredictions;
import Action.ActionProfilAstral;
import Action.ActionRechercheMedium;
import Action.ActionSessionClient;
import Action.ActionSessionEmploye;
import Action.ActionStatistiques;
import Action.ActionTerminerConsultation;
import Serialisation.ActionCommencerConsultationSerialisation;
import Serialisation.Serialisation;
import Serialisation.ActionConnexionClientSerialisation;
import Serialisation.ActionConnexionEmployeSerialisation;
import Serialisation.ActionConsultationEnCoursSerialisation;
import Serialisation.ActionDeconnexionClientSerialisation;
import Serialisation.ActionDeconnexionEmployeSerialisation;
import Serialisation.ActionDemandeConsultationSerialisation;
import Serialisation.ActionHistoriqueClientEmployeSerialisation;
import Serialisation.ActionHistoriqueClientSerialisation;
import Serialisation.ActionInscriptionClientSerialisation;
import Serialisation.ActionNotifierClientSerialisation;
import Serialisation.ActionPredictionsSerialisation;
import Serialisation.ActionProfilAstralSerialisation;
import Serialisation.ActionRechercheMediumSerialisation;
import Serialisation.ActionSessionClientSerialisation;
import Serialisation.ActionSessionEmployeSerialisation;
import Serialisation.ActionStatistiquesSerialisation;
import Serialisation.ActionTerminerConsultationSerialisation;
import dao.JpaUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adamchellaoui
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    @Override
    public void init()
            throws ServletException {
        JpaUtil.init();
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        String todo = request.getParameter("todo");

        Action action = null;
        Serialisation serialisation = null;

        switch (todo) {
            case "connexion-client": {
                action = new ActionConnexionClient();
                serialisation = new ActionConnexionClientSerialisation();
            }
            break;

            case "inscription-client": {
                action = new ActionInscriptionClient();
                serialisation = new ActionInscriptionClientSerialisation();
            }
            break;

            case "session-client": {
                action = new ActionSessionClient();
                serialisation = new ActionSessionClientSerialisation();
            }
            break;

            case "recherche-medium": {
                action = new ActionRechercheMedium();
                serialisation = new ActionRechercheMediumSerialisation();
            }
            break;

            case "historique-client": {
                action = new ActionHistoriqueClient();
                serialisation = new ActionHistoriqueClientSerialisation();
            }
            break;

            case "deconnexion-client": {
                action = new ActionDeconnexionClient();
                serialisation = new ActionDeconnexionClientSerialisation();
            }
            break;

            case "demande-consultation": {
                action = new ActionDemandeConsultation();
                serialisation = new ActionDemandeConsultationSerialisation();
            }
            break;

            case "connexion-employe": {
                action = new ActionConnexionEmploye();
                serialisation = new ActionConnexionEmployeSerialisation();
            }
            break;

            case "session-employe": {
                action = new ActionSessionEmploye();
                serialisation = new ActionSessionEmployeSerialisation();
            }
            break;

            case "deconnexion-employe": {
                action = new ActionDeconnexionEmploye();
                serialisation = new ActionDeconnexionEmployeSerialisation();
            }
            break;

            case "consultation-encours": {
                action = new ActionConsultationEnCours();
                serialisation = new ActionConsultationEnCoursSerialisation();
            }
            break;

            case "historique-client-employe": {
                action = new ActionHistoriqueClientEmploye();
                serialisation = new ActionHistoriqueClientEmployeSerialisation();
            }
            break;
            
            case "profil-astral": {
                action = new ActionProfilAstral();
                serialisation = new ActionProfilAstralSerialisation();
            }
            break;
            
            case "predictions": {
                action = new ActionPredictions();
                serialisation = new ActionPredictionsSerialisation();
            }
            break;
            
            case "notifier-client": {
                action = new ActionNotifierClient();
                serialisation = new ActionNotifierClientSerialisation();
            }
            break;
            
            case "terminer-consultation": {
                action = new ActionTerminerConsultation();
                serialisation = new ActionTerminerConsultationSerialisation();
            }
            break;
            
            case "commencer-consultation": {
                action = new ActionCommencerConsultation();
                serialisation = new ActionCommencerConsultationSerialisation();
            }
            break;
            
            case "statistiques" :{
                action= new ActionStatistiques();
                serialisation = new ActionStatistiquesSerialisation();
            }
            break;


        }
        if (action != null && serialisation != null) {
            action.execute(request); // Exécuter l'Action
            serialisation.serialiser(request, response); // Sérialiser le résultat de l'Action
        } else { // Erreur: pas d'Action ou de Sérialisation pour traiter cette requête
            response.sendError(400, "Bad Request (pas d'Action ou de Serialisation pour traiter cette requete)");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
