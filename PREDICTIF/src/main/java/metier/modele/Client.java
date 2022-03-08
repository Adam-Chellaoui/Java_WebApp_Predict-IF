package metier.modele;


import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 *
 * @author adamchellaoui
 */
@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    String nom;
    String prenom;
    String adressePostale;
    String telephone;
    String genre;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateNaissance;
    @Column(unique=true)
    String mail;
    String motDePasse;
    @Embedded
    private ProfilAstral profil;

    public Client() {
    }

    public Client(String nom, String prenom, String adressePostale, String telephone, String genre, Date dateNaissance, String mail, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adressePostale = adressePostale;
        this.telephone = telephone;
        this.genre = genre;
        this.dateNaissance = dateNaissance;
        this.mail = mail;
        this.motDePasse = motDePasse;
      
    }

    public void setProfil(ProfilAstral profil) {
        this.profil = profil;
    }

    public ProfilAstral getProfil() {
        return profil;
    }

    
    
    public Long getId() {
        return this.id;
    }
    public String getNom() {
        return this.nom;
    }
    void setNom(String nom) {
        this.nom=nom;
    }
    public String getPrenom() {
        return this.prenom;
    }
    void setPrenom(String prenom) {
        this.prenom=prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public String getAdressePostale() {
        return adressePostale;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getGenre() {
        return genre;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    
    
    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adressePostale=" + adressePostale + ", telephone=" + telephone + ", genre=" + genre + ", dateNaissance=" + dateNaissance + ", mail=" + mail + ", motDePasse=" + motDePasse + ", profil=" + profil + '}';
    }
    
    
    
    
}
