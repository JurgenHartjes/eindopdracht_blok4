package nl.hu.bep.shopping.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.hu.bep.shopping.database.ConnectGebruiker;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Gebruiker implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String naam;
    protected String mailAdres;
    protected String telefoonnummer;
    protected String wachtwoord;
    protected int klantenNr;
    private ArrayList<Vragenlijst> ingevuldeLijsten;
    static ArrayList<Gebruiker> alleGebruikers = new ArrayList<>();

    @JsonCreator
    public Gebruiker(@JsonProperty("naam") String naam, @JsonProperty("mailAdres") String mailAdres,
                     @JsonProperty("telefoonnummer") String telefoonnummer, @JsonProperty("wachtwoord") String wachtwoord,
                     @JsonProperty("klantenNr") int klantenNr) {
        boolean bestaatAl = false;
        for (Gebruiker gebruiker : getAlleGebruikers()) {
            if (gebruiker.getKlantenNr() == klantenNr)  {
                bestaatAl = true;
            }
        }
        if (!bestaatAl) {
            this.naam = naam;
            this.mailAdres = mailAdres;
            this.telefoonnummer = telefoonnummer;
            this.wachtwoord = wachtwoord;
            this.klantenNr = klantenNr;
            this.ingevuldeLijsten = new ArrayList<>();
            alleGebruikers.add(this);
            this.saveGebruiker();
            System.out.println("klant aangemaakt");
        } else {System.out.println("klant bestaat al, niet aangemaakt");}
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getMailAdres() {
        return mailAdres;
    }

    public void setMailAdres(String mailAdres) {
        this.mailAdres = mailAdres;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public int getKlantenNr() {
        return klantenNr;
    }

    public void setKlantenNr(int klantenNr) {
        this.klantenNr = klantenNr;
    }

    public ArrayList<Vragenlijst> getIngevuldeLijsten() {
        return ingevuldeLijsten;
    }

    public void setIngevuldeLijsten(ArrayList<Vragenlijst> ingevuldeLijsten) {
        this.ingevuldeLijsten = ingevuldeLijsten;
    }

    public Vragenlijst getIngevuldeLijst(int index) {
        return ingevuldeLijsten.get(index);
    }

    public Vragenlijst getIngevuldeLijstById(String ID) {
        for (Vragenlijst lijst : ingevuldeLijsten) {
            if (lijst.getId().equals(ID)) {
                return lijst;
            }
        }
        return null;
    }

    public void ingevuldeVragenlijstToevoegen(Vragenlijst vragenlijst) {
        if (this.ingevuldeLijsten == null) {
            this.ingevuldeLijsten = new ArrayList<>();
        }
        this.ingevuldeLijsten.add(vragenlijst);
    }

    public boolean hasFilledInLastWeek(Vragenlijst vragenlijst) {
        LocalDateTime now = LocalDateTime.now();
        for (Vragenlijst ingevulde : ingevuldeLijsten) {
            if (ingevulde.getId().equals(vragenlijst.getId()) &&
                    ChronoUnit.DAYS.between(ingevulde.getInvulDatum(), now) < 7) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Gebruiker> getAlleGebruikers() {
        return alleGebruikers;
    }

    public static void setAlleGebruikers(ArrayList<Gebruiker> alleGebruikers) {
        Gebruiker.alleGebruikers = alleGebruikers;
    }

    public void saveGebruiker() {
        System.out.println("Saving gebruiker: " + naam);
        ConnectGebruiker.saveKlantToDatabase(klantenNr, naam, mailAdres, telefoonnummer, wachtwoord);
    }



    public void addIngevuldeLijst(Vragenlijst vragenlijst) {
        ingevuldeLijsten.add(vragenlijst);
    }
}
