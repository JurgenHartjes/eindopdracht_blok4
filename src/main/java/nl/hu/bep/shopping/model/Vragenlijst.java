package nl.hu.bep.shopping.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Vragenlijst {
    private ArrayList<Vraag> vragenlijst;
    private String naam;
    private LocalDateTime momentVanInvoer;

    public Vragenlijst(String naam) {       //maakt de lijst
        this.vragenlijst = new ArrayList<>();
        this.naam = naam;
    }

    public void addVraag(Vraag vraag) {     //voegt een vraag toe aan de lijst
        vragenlijst.add(vraag);
    }

    public List<Vraag> getVragen() {        //geeft alle vragen uit de lijst terug
        return vragenlijst;
    }

    public String getName() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setMomentVanInvoer()    {
        this.momentVanInvoer = LocalDateTime.now();
    }

}
