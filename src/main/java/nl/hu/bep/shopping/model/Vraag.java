package nl.hu.bep.shopping.model;

public class Vraag {
    private static int idCounter = 0;
    private String vraag;
    private String antwoord;

    public Vraag(String vraag) {        //maakt een vraag
        this.vraag = vraag;
    }

    public String getVraag() {
        return vraag;
    }

    public void setVraag(String vraag) {
        this.vraag = vraag;
    }

    public String getAntwoord() {
        return antwoord;
    }

    public void setAntwoord(String antwoord) {
        this.antwoord = antwoord;
    }
}
