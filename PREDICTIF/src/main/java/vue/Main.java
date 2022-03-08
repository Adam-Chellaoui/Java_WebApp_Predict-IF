package vue;


import dao.JpaUtil;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import metier.modele.Client;
import metier.service.Service;
import java.text.SimpleDateFormat; 
import java.util.List;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;

/**
 *
 * @author adamchellaoui
 */
public class Main {
    
    public static void testInscriptionClient () throws IOException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = simpleDateFormat.parse("25/12/2010");
        Service service=new Service();
        Client client= new Client("Chellaoui","Adam","5 rue des Antonnins","0762653478","M",date,"dam.chellaoui@yahoo.com","MDP");
        client= service.inscrireClient(client);
        System.out.println(client);
    }
    
    //Test presque complet de l'application
    public static void testClient() throws IOException, ParseException, Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = simpleDateFormat.parse("25/12/2010");
        Service service=new Service();
        Client client= new Client("Chellaoui","Adam","5 rue des Antonnins","0762653478","M",date,"adam.chellaoui@yahoo.com","MDP");
        client= service.inscrireClient(client);
        System.out.println(client);
        client=service.authentifierClient("adam.chellaoui@yahoo.com","MDP"); //Le client s'authentifie, on vérfie que les deux affichages sont identiques...
        System.out.println(client);
        List<Medium> mediums=service.getMediums(true);
        for(int i=0;i<mediums.size();i++){
        System.out.println(mediums.get(i));
        }
        //Le client choisit de prendre le premier medium
        Medium medium=(Medium)mediums.get(1);
        Consultation consultation=service.demandeConsultation(client,medium);
        List<Consultation> consultations=service.historiqueClientEnConsultation(client,consultation);// Le medium s'informe sur le client avec l'historique
        for(int i=0;i<consultations.size();i++){
        System.out.println(consultations.get(i));
        }
        List<String> profil=service.getProfilAstral(client);// Le medium s'informe sur le client en regardant son profil
        for(int i=0;i<profil.size();i++){
        System.out.println(profil.get(i));
        }
        service.notifierClient(consultation); //on notifie le client une fois que l'employe est prêt
        service.commencerConsultation(consultation);//une fois que l'appel commence
        List<String> predictions=service.getPredictions(3, 3, 3, client);// Le medium sèche ...
        for(int i=0;i<predictions.size();i++){
        System.out.println(predictions.get(i));
        }
        service.terminerConsultation(consultation,"blabla");
        //On vérifie que la conusultation, l'employe, et le medium ont bien été mis à jour
        System.out.println(consultation);
        System.out.println(consultation.getEmploye());
        System.out.println(consultation.getMedium());
    }
    
    //Test d'authentification d'un employe
    public static void testAthentificationEmploye(String login, String mdp)  {
        Service service= new Service();
        Employe employe=service.authentifierEmploye(login, mdp);
        System.out.println(employe);
    }
    
    //Test de la statistique pour un Top mediums (ici Top3)
    public static void testTopMediums() {
        Service service=new Service();
        //On invente un scenario pour montrer que l'application focntionne en initialisant des mediums avec des consultations dans initMediums() : 
        List<Medium> topMediums=service.topMediums(3);
        for(int i=0;i<topMediums.size();i++){
        System.out.println(topMediums.get(i));
        }
    }

    public static void main(String [] args) throws ParseException, IOException, Exception {
        JpaUtil.init();
        Service service=new Service();
        //On initialise les employés et les médiums
        service.initEmploye();
        service.initMedium();
        //On effectue nos tests
        //testAthentificationEmploye("bernard.tapie@predictif.fr","vivelargent");
        //testClient();
        //testTopMediums();

        JpaUtil.destroy();
    }
    
}
