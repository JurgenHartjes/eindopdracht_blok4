package nl.hu.bep.shopping.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Vragenlijst implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private ArrayList<Vraag> vragenlijst;
    private String naam;
    private LocalDateTime momentVanInvoer;
    private static ArrayList<Vragenlijst> alleVragenlijsten = new ArrayList<>();

    public Vragenlijst(String naam) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID
        this.vragenlijst = new ArrayList<>();
        this.naam = naam;
        this.momentVanInvoer = LocalDateTime.now(); // Initialize momentVanInvoer
    }

    public String getId() {
        return id;
    }

    public void addVraag(Vraag vraag) {
        vragenlijst.add(vraag);
    }

    public List<Vraag> getVragen() {
        return vragenlijst;
    }

    public String getName() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setMomentVanInvoer() {
        this.momentVanInvoer = LocalDateTime.now();
    }

    public String getMomentVanInvoer() {
        return momentVanInvoer.toString();
    }

    public void saveVragenlijst() {
        for (int i = 0; i < alleVragenlijsten.size(); i++) {
            if (alleVragenlijsten.get(i).getId().equals(this.getId())) {
                alleVragenlijsten.remove(i);
            }
        }
        alleVragenlijsten.add(this);
    }

    public static ArrayList<Vragenlijst> getAlleVragenlijsten() {
        return alleVragenlijsten;
    }

    public static Vragenlijst getVragenlijstById(String id) {
        for (Vragenlijst vragenlijst : alleVragenlijsten) {
            if (vragenlijst.getId().equals(id)) {
                return vragenlijst;
            }
        }
        return null;
    }

    public LocalDateTime getInvulDatum()  {
        return momentVanInvoer;
    }
}
