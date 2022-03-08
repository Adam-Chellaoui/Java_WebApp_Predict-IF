/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import dao.ClientDao;
import dao.ConsultationDao;
import dao.EmployeDao;
import dao.JpaUtil;
import dao.MediumDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import metier.modele.Astrologue;
import metier.modele.Cartomancien;
import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import metier.modele.Medium;
import metier.modele.ProfilAstral;
import metier.modele.Spirite;

/**
 *
 * @author adamchellaoui
 */
public class Service {

    public Service() {
    }

    public Client chercherClient(long id) {
        ClientDao clientDao = new ClientDao();
        Client client = new Client();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            client = clientDao.chercherParId(id);
            JpaUtil.validerTransaction();

            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            client = null;
        } finally {
            JpaUtil.fermerContextePersistance();
            return client;
        }
    }

    public Consultation chercherConsultationEnCours(Employe employe) {
        ConsultationDao consDao = new ConsultationDao();
        Consultation consultation = new Consultation();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            consultation = consDao.chercherEnCours(employe);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès");
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            consultation = null;
        } finally {
            JpaUtil.fermerContextePersistance();
            return consultation;
        }
    }

    public Employe chercherEmploye(long id) {
        EmployeDao empDao = new EmployeDao();
        Employe employe = new Employe();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            employe = empDao.chercherParId(id);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            employe = null;
        } finally {
            JpaUtil.fermerContextePersistance();
            return employe;
        }
    }

    public Medium chercherMedium(long id) {
        MediumDao medDao = new MediumDao();
        Medium medium = new Medium();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            medium = medDao.chercherParId(id);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            medium = null;
        } finally {
            JpaUtil.fermerContextePersistance();
            return medium;
        }
    }

    public Client authentifierClient(String login, String mdp) {
        ClientDao clientDao = new ClientDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            Client client = new Client();
            client = clientDao.chercherParMail(login);
            JpaUtil.validerTransaction();
            if (client.getMotDePasse().equals(mdp)) {
                System.out.println("Authentification réussie.");
                Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
                return client;
            } else {
                System.out.println("Mot de passe incorrect. Authentification échouée");
                Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
                return null;
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            return null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public Employe authentifierEmploye(String login, String mdp) {
        EmployeDao employeDao = new EmployeDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            Employe employe;
            employe = employeDao.chercherParMail(login);
            JpaUtil.validerTransaction();
            if (employe.getMotDePasse().equals(mdp)) {
                Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
                return employe;
            } else {
                Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
                return null;
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            return null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public Consultation demandeConsultation(Client client, Medium medium) throws Exception {
        EmployeDao empDao = new EmployeDao();
        ConsultationDao consultationDao = new ConsultationDao();
        try {
            JpaUtil.creerContextePersistance();
            String genreDuMedium = medium.getGenre();
            List<Employe> resultat = empDao.chercherDisponibles(genreDuMedium);
            Employe emp = resultat.get(0); //On prend l'employé disponible qui a le moins travaillé
            emp.setDisponible(false);
            JpaUtil.ouvrirTransaction();
            empDao.modifier(emp);
            Date date = new Date();
            Consultation consultation = new Consultation(date, 0, "", client, emp, medium);
            consultationDao.creer(consultation);
            JpaUtil.validerTransaction();
            StringWriter message = new StringWriter();
            PrintWriter notificationWriter = new PrintWriter(message);
            if (client.getGenre() == "M") {
                notificationWriter.println("Bonjour " + emp.getPrenom() + ". Consultation requise pour Mr " + client.getPrenom() + " " + client.getNom() + ". Medium à incarner : " + medium.getDenomination());
            } else if (client.getGenre() == "F") {
                notificationWriter.println("Bonjour " + emp.getPrenom() + ". Consultation requise pour Mme " + client.getPrenom() + " " + client.getNom() + ". Medium à incarner : " + medium.getDenomination());
            }
            Message.envoyerNotification(
                    emp.getTelephone(),
                    message.toString()
            );

            Logger.getAnonymousLogger().log(Level.INFO, "succès");
            return consultation;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace();// affiche sur la console l'erreur complète.
            return null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }

    }

    public void notifierClient(Consultation consultation) {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        StringWriter message = new StringWriter();
        PrintWriter notificationWriter = new PrintWriter(message);
        notificationWriter.println("Bonjour " + consultation.getClient().getPrenom() + ". J’ai bien reçu votre demande de consultation du "
                + format.format(consultation.getDate()) + ". Vous pouvez dès à présent me contacter au " + consultation.getEmploye().getTelephone() + ". A tout de suite ! "
                + "Médiumiquement vôtre, " + consultation.getMedium().getDenomination());
        Message.envoyerNotification(
                consultation.getEmploye().getTelephone(),
                message.toString()
        );
    }

    public void commencerConsultation(Consultation consultation) throws Exception {
        ConsultationDao consultationDao = new ConsultationDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            consultation.setDate(date);
            consultationDao.modifier(consultation);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès");

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace();// affiche sur la console l'erreur complète.
        } finally {
            JpaUtil.fermerContextePersistance();
        }

    }

    public void terminerConsultation(Consultation consultation, String commentaire) throws Exception {

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date now = new Date();
        long duree = (now.getTime() - consultation.getDate().getTime()) / 60000;
        System.out.println(duree);
        long nouveauTempsTravail = consultation.getEmploye().getTempsTravail() + duree;
        ConsultationDao consultationDao = new ConsultationDao();
        EmployeDao employeDao = new EmployeDao();
        MediumDao mediumDao = new MediumDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            consultation.setDuree(duree);
            int nbConsultation = consultation.getEmploye().getNombreConsultations() + 1;
            consultation.getEmploye().setNombreConsultations(nbConsultation);
            consultation.getEmploye().setTempsTravail(nouveauTempsTravail);
            consultation.getEmploye().setDisponible(true);
            employeDao.modifier(consultation.getEmploye());
            int nbConsultationMed = consultation.getMedium().getNombreConsultations() + 1;
            consultation.getMedium().setNombreConsultations(nbConsultationMed);
            mediumDao.modifier(consultation.getMedium());
            consultation.setCommentaire(commentaire);
            consultationDao.modifier(consultation);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès");
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace();// affiche sur la console l'erreur complète.
        } finally {
            JpaUtil.fermerContextePersistance();
        }

    }

    public void initEmploye() throws IOException {
        EmployeDao employeDao = new EmployeDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            Employe emp1 = new Employe("Bernard", "TAPIE", "bernard.tapie@predictif.fr", "0756453477", "M", true, 15, "vivelargent", 9);
            employeDao.creer(emp1);
            Employe emp2 = new Employe("Raphaël", "BORROTI MATIAS DANTAS", "raphael.borroti@predictif.fr", "0328178508", "M", true, 16, "borogogo", 8);
            employeDao.creer(emp2);
            Employe emp3 = new Employe("Alice", "VOYRET", "alice.voyret@predictif.fr", "0486856520", "F", true, 17, "genialgogo", 7);
            employeDao.creer(emp3);
            Employe emp4 = new Employe("Lou", "ANNE", "lou.anne@predictif.fr", "0522345678", "F", true, 18, "thisisthevoice", 6);
            employeDao.creer(emp4);
            Employe emp5 = new Employe("Agnès", "BENCHETRIT", "agnes.benchetrit@predictif.fr", "0522456780", "F", true, 19, "waaaaw", 3);
            employeDao.creer(emp5);
            Employe emp6 = new Employe("Romain", "MIE", "romain.mie@predictif.fr", "0307363387", "M", true, 20, "romain123", 8);
            employeDao.creer(emp6);
            Employe emp7 = new Employe("Jose Luis", "DALMARRE", "joseluis.dalmarre@predictif.fr", "0549997540", "M", true, 21, "marreaucanard", 5);
            employeDao.creer(emp7);
            Employe emp8 = new Employe("Charles", "ATAN", "charles.atan@predictif.fr", "0549996789", "M", true, 1, "escroc", 12);
            employeDao.creer(emp8);
            Employe emp9 = new Employe("Les", "CROC", "les.croc@predictif.fr", "0942244718", "F", true, 2, "hahahaha", 10);
            employeDao.creer(emp9);
            Employe emp10 = new Employe("Yaelle", "ROUVEIRE", "yaelle.rouveire@predictif.fr", "0722193930", "F", true, 16, "hahahaha", 7);
            employeDao.creer(emp10);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.

        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public void initMedium() throws IOException {
        MediumDao mediumDao = new MediumDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            Spirite Gwenaelle = new Spirite("Boule de cristal", "Spécialiste des grandes conversations au-delà de TOUTES les frontières.", "F", "Gwenaëlle", 11);
            mediumDao.creer(Gwenaelle);
            Spirite Tran = new Spirite("Marc de café, boule de cristal, oreilles de lapin", "Votre avenir est devant vous : regardons-le ensemble !", "M", "Professeur Tran", 5);
            mediumDao.creer(Tran);
            Spirite Fou = new Spirite("Canettes de Red-Bull, mégots de cigarette, objets personnels", "Venez vivre une aventure sans pareil à mes côtés, je vous en apprendrais sur vous.", "M", "Docteur Fou", 5);
            mediumDao.creer(Fou);
            Spirite Bachelor = new Spirite("Costumes, cravates, lunettes de soleil, pochette", "Véritable Maître du spiritisme et de la sapologie, vos sapes me permettront de lire votre destin.", "M", "Le Bachelor", 20);
            mediumDao.creer(Bachelor);
            Cartomancien Irma = new Cartomancien("Comprenez votre entourage grâce à mes cartes ! Résultats rapides.", "F", "Mme Irma", 5);
            mediumDao.creer(Irma);
            Cartomancien Endora = new Cartomancien("Mes cartes répondront à toutes vos questions personnelles.", "F", "Endora", 8);
            mediumDao.creer(Endora);
            Cartomancien Phill = new Cartomancien("Je joue avec les cartes comme je joue avec les esprits.", "F", "La conquéreuse", 9);
            mediumDao.creer(Phill);
            Cartomancien Mec = new Cartomancien("J'ai plus d'un tour dans mon sac, et plus d'une carte dans mon tour !", "M", "Le Grand Houdini", 10);
            mediumDao.creer(Mec);
            Astrologue Serena = new Astrologue("École Normale Supérieure d’Astrologie (ENS-Astro)", "2006", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.", "F", "Serena", 6);
            mediumDao.creer(Serena);
            Astrologue Mr_M = new Astrologue("Institut des Nouveaux Savoirs Astrologiques", "2010", "Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter!", "M", "Mr M", 2);
            mediumDao.creer(Mr_M);
            Astrologue Bg = new Astrologue("ENA Ecole Nationale d'Astrologie ", "2019", "Appenez à oser, apprenez en plus sur vous et votre destin à travers mes précieux savoirs d'Enarque.", "M", "Mr Hubble", 16);
            mediumDao.creer(Bg);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.

        } finally {
            JpaUtil.fermerContextePersistance();
        }

    }
    

    public Client inscrireClient(Client client) throws IOException {
        AstroTest astroApi = new AstroTest();
        List<String> profil = astroApi.getProfil(client.getPrenom(), client.getDateNaissance());
        String signeZodiaque = profil.get(0);
        String signeChinois = profil.get(1);
        String couleur = profil.get(2);
        String animal = profil.get(3);
        ProfilAstral prof = new ProfilAstral(signeZodiaque, signeChinois, couleur, animal);
        System.out.println("Profil Astral : " + prof);
        client.setProfil(prof);
        ClientDao clientDao = new ClientDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            clientDao.creer(client);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
            StringWriter corps = new StringWriter();
            PrintWriter mailWriter = new PrintWriter(corps);
            mailWriter.println("  Bienvenue chez PREDICT’IF\n"
                    + "Bonjour " + client.getPrenom() + ", nous vous confirmons votre inscription au service PREDICT’IF. Rendez- vous vite sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos mediums.");
            Message.envoyerMail(
                    "contact@predictif.if",
                    client.getMail(),
                    "Bienvenue chez PREDICT'IF",
                    corps.toString()
            );
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            client = null;

            StringWriter corps = new StringWriter();
            PrintWriter mailWriter = new PrintWriter(corps);
            mailWriter.println(
                    "Bonjour " + client.getPrenom() + ", votre inscription au service PREDICT’IF a malencontreusement échoué... Merci de recommencer ultérieurement.");
            Message.envoyerMail(
                    "contact@predictif.if",
                    client.getMail(),
                    "Echec de l’inscription chez PREDICT’IF",
                    corps.toString()
            );
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return client;
    }

    public List<String> getPredictions(int niveauAmour, int niveauSante, int niveauTravail, Client client) throws IOException {
        AstroTest astroApi = new AstroTest();
        List<String> predictions = astroApi.getPredictions(client.getProfil().getCouleur(), client.getProfil().getAnimal(), niveauAmour, niveauSante, niveauTravail);
        return predictions;
    }

    public List<String> getProfilAstral(Client client) {
        List<String> profil = new ArrayList<>();
        ProfilAstral Profil = client.getProfil();
        profil.add(Profil.getAnimal());
        profil.add(Profil.getChinois());
        profil.add(Profil.getCouleur());
        profil.add(Profil.getZodiaque());
        return profil;
    }

    public List<Consultation> historiqueClientEnConsultation(Client client, Consultation consultation) {
        ConsultationDao consDao = new ConsultationDao();
        List<Consultation> cons;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            cons = consDao.historiqueDuClient(client, consultation);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            cons = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return cons;
    }

    public List<Consultation> historiqueClient(Client client) {
        ConsultationDao consDao = new ConsultationDao();
        List<Consultation> cons;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            cons = consDao.historiqueDuClient(client);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            cons = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return cons;
    }

    public List<Medium> getMediums() {
        MediumDao medDao = new MediumDao();
        List<Medium> mediums;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            mediums = medDao.getTheMediums();
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            mediums = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return mediums;
    }

    public List<Medium> getMediums(Boolean disponible) {
        MediumDao medDao = new MediumDao();
        List<Medium> mediums;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            mediums = medDao.getTheMediums(disponible);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            mediums = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return mediums;
    }

    public List<Astrologue> getAstrologues() {
        MediumDao medDao = new MediumDao();
        List<Astrologue> astrologues;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            astrologues = medDao.getTheAstrologues();
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            astrologues = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return astrologues;
    }

    public List<Astrologue> getAstrologues(Boolean disponible) {
        MediumDao medDao = new MediumDao();
        List<Astrologue> astrologues;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            astrologues = medDao.getTheAstrologues(disponible);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            astrologues = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return astrologues;
    }

    public List<Cartomancien> getCartomanciens() {
        MediumDao medDao = new MediumDao();
        List<Cartomancien> cartomanciens;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            cartomanciens = medDao.getTheCartomanciens();
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            cartomanciens = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return cartomanciens;
    }

    public List<Cartomancien> getCartomanciens(Boolean disponible) {
        MediumDao medDao = new MediumDao();
        List<Cartomancien> cartomanciens;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            cartomanciens = medDao.getTheCartomanciens(disponible);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            cartomanciens = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return cartomanciens;
    }

    public List<Spirite> getSpirites() {
        MediumDao medDao = new MediumDao();
        List<Spirite> spirites;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            spirites = medDao.getTheSpirites();
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            spirites = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return spirites;
    }

    public List<Spirite> getSpirites(Boolean disponible) {
        MediumDao medDao = new MediumDao();
        List<Spirite> spirites;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            spirites = medDao.getTheSpirites(disponible);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            spirites = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return spirites;
    }

    public List<Medium> topMediums(int n) {
        MediumDao mediumDao = new MediumDao();
        List<Medium> mediums;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            mediums = mediumDao.topTheMediums(n);
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            mediums = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return mediums;
    }

    public List<Employe> getEmployes() {
        EmployeDao empDao = new EmployeDao();
        List<Employe> employes;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            employes = empDao.chercherTous();
            JpaUtil.validerTransaction();
            Logger.getAnonymousLogger().log(Level.INFO, "succès"); //ça a marché
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Erreur !");
            JpaUtil.annulerTransaction();
            ex.printStackTrace(); // affiche sur la console l'erreur complète.
            employes = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return employes;
    }

}
