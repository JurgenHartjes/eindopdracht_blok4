package nl.hu.bep.shopping.model;

import java.util.ArrayList;
import java.util.List;

public class Gebruiker {

    protected String naam;
    protected String mailAdres;
    protected String telefoonnummer;
    protected String wachtwoord;
    protected String klantenNr;

    private ArrayList<Vragenlijst> ingevuldeLijsten; //lijst met objecten van vragenlijsten (waar de vragen en antwoorden aan zijn gekoppeld



    public Gebruiker(String nm, String mail, String tlfnnr, String ww, String klantenNr)    {       //maakt een gebruiker aan

        this.naam = nm;
        this.mailAdres = mail;
        this.telefoonnummer = tlfnnr;
        this.wachtwoord = ww;
        this.klantenNr = klantenNr;
        this.ingevuldeLijsten = new ArrayList<>();
    }

    public Vragenlijst getIngevuldeLijst(int index)    {
        return ingevuldeLijsten.get(index);        //geeft de gevraagde vragenlijst terug
    }

    public String getKlantenNr()    {return klantenNr;}


    public void ingevuldeVragenlijstToevoegen(Vragenlijst vragenlijst)    {      //voegt een beantwoordde lijst toe aan de gebruiker
        System.out.println("vragenlijst wordt toegevoegd...");
        ingevuldeLijsten.add(vragenlijst);
        System.out.println("Gelukt!");

    }

    public String getName() {return naam;}
}