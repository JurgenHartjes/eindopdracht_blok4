package nl.hu.bep.shopping;

import nl.hu.bep.shopping.model.Gebruiker;
import nl.hu.bep.shopping.model.Vragenlijst;
import nl.hu.bep.shopping.model.Vraag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GebruikerTest {

    @Test
    public void testGebruikerConstruction() {
        Gebruiker gebruiker = new Gebruiker("John", "john@example.com", "0612345678", "wachtwoord123", 1233);
        assertEquals("John", gebruiker.getNaam());
        assertEquals("john@example.com", gebruiker.getMailAdres());
        assertEquals("0612345678", gebruiker.getTelefoonnummer());
        assertEquals("wachtwoord123", gebruiker.getWachtwoord());
        assertEquals("K123", gebruiker.getKlantenNr());
    }

    @Test
    public void testIngevuldeVragenlijstToevoegen() {
        Gebruiker gebruiker = new Gebruiker("John", "john@example.com", "0612345678", "wachtwoord123", 12355);
        Vragenlijst vragenlijst = new Vragenlijst("Test Vragenlijst");
        Vraag vraag1 = new Vraag("What is your name?", "John");
        Vraag vraag2 = new Vraag("How old are you?", "25");
        vragenlijst.addVraag(vraag1);
        vragenlijst.addVraag(vraag2);
        gebruiker.ingevuldeVragenlijstToevoegen(vragenlijst);
        assertEquals(1, gebruiker.getIngevuldeLijsten().size());
    }

}