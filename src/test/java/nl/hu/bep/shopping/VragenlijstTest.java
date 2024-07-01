package nl.hu.bep.shopping.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VragenlijstTest {

    private Vragenlijst vragenlijst;

    @BeforeEach
    void setup() {
        vragenlijst = new Vragenlijst("Test Vragenlijst");
    }

    @Test
    public void testConstructor() {
        assertNotNull(vragenlijst.getId());
        assertEquals("Test Vragenlijst", vragenlijst.getName());
        assertNotNull(vragenlijst.getMomentVanInvoer());
    }

    @Test
    public void testAddVraag() {
        Vraag vraag1 = new Vraag("Wat is Java?", "technisch");
        Vraag vraag2 = new Vraag("Wat is een class?", "technisch");

        vragenlijst.addVraag(vraag1);
        vragenlijst.addVraag(vraag2);

        List<Vraag> vragen = vragenlijst.getVragen();
        assertEquals(2, vragen.size());
        assertTrue(vragen.contains(vraag1));
        assertTrue(vragen.contains(vraag2));
    }

    @Test
    public void testSetNaam() {
        vragenlijst.setNaam("Updated Name");
        assertEquals("Updated Name", vragenlijst.getName());
    }

    @Test
    public void testSetMomentVanInvoer() {
        LocalDateTime oldMoment = LocalDateTime.parse(vragenlijst.getMomentVanInvoer());

        // Wait a bit to simulate a change in LocalDateTime
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        vragenlijst.setMomentVanInvoer();
        LocalDateTime newMoment = LocalDateTime.parse(vragenlijst.getMomentVanInvoer());

        assertNotEquals(oldMoment, newMoment);
    }

    @Test
    public void testSaveVragenlijst() {
        vragenlijst.saveVragenlijst();

        // Check if vragenlijst is in alleVragenlijsten
        assertTrue(Vragenlijst.getAlleVragenlijsten().contains(vragenlijst));
    }

    @Test
    public void testGetVragenlijstById() {
        vragenlijst.saveVragenlijst();
        String id = vragenlijst.getId();

        Vragenlijst foundVragenlijst = Vragenlijst.getVragenlijstById(id);
        assertEquals(vragenlijst, foundVragenlijst);
    }

    @Test
    public void testGetInvulDatum() {
        LocalDateTime momentVanInvoer = vragenlijst.getInvulDatum();
        assertNotNull(momentVanInvoer);
    }
}
