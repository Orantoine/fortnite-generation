package fr.Orantoine.fortnitegeneration.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value = "Joueur")
public class Player {

    @Id
    private String id;

    private String accountid;

    private String pseudo;

    private String adresse;

    public Player(String id, String accountid, String pseudo, String adresse) {
        this.id = id;
        this.accountid = accountid;
        this.pseudo = pseudo;
        this.adresse = adresse;
    }

    public Player() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "id='" + id + '\'' +
                ", accountid='" + accountid + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}

