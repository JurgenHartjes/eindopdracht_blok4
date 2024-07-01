package nl.hu.bep.shopping.model;

import java.io.Serializable;

public class Vraag implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 0; // Static counter to generate unique IDs
    private int id; // Unique identifier for each Vraag
    private String vraag;
    private String antwoord;
    private String vraagtype;

    public Vraag(String vraag, String type) {
        this.id = idCounter++; // Assign a unique ID and increment the counter
        this.vraag = vraag;
        this.vraagtype = type;
    }

    public int getId() {
        return id;
    }

    public String getVraag() {
        return vraag;
    }

    public void setVraag(String vraag) {
        this.vraag = vraag;
    }

    public String getAntwoord() {
        System.out.println("\nvraag: " + vraag);
        System.out.println("antword: " + antwoord);
        return antwoord;
    }

    public void setAntwoord(String antwoord) {
        this.antwoord = antwoord;
    }

    public void setVraagtype(String type) {
        this.vraagtype = type;
    }

    public String getVraagtype() {
        return vraagtype;
    }

    public String getVraagTekst() {
        return vraag;
    }
}
