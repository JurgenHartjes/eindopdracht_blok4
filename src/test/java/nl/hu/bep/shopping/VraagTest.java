package nl.hu.bep.shopping;

import nl.hu.bep.shopping.model.Vraag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VraagTest {

    @BeforeEach
    void resetIdCounter() {
        // Reset the idCounter before each test to ensure consistent test results
        // Reflectively accessing and resetting the idCounter field
        try {
            java.lang.reflect.Field field = Vraag.class.getDeclaredField("idCounter");
            field.setAccessible(true);
            field.set(null, 0);
        } catch (Exception e) {
            fail("Failed to reset idCounter");
        }
    }

    @Test
    public void testVraagConstructorAndGetters() {
        Vraag vraag = new Vraag("Wat is Java?", "technisch");
        assertEquals(0, vraag.getId());
        assertEquals("Wat is Java?", vraag.getVraag());
        assertEquals("technisch", vraag.getVraagtype());
    }

    @Test
    public void testSetVraag() {
        Vraag vraag = new Vraag("Wat is Java?", "technisch");
        vraag.setVraag("Wat is Python?");
        assertEquals("Wat is Python?", vraag.getVraag());
    }

    @Test
    public void testSetAntwoord() {
        Vraag vraag = new Vraag("Wat is Java?", "technisch");
        vraag.setAntwoord("Een programmeertaal.");
        assertEquals("Een programmeertaal.", vraag.getAntwoord());
    }

    @Test
    public void testSetVraagtype() {
        Vraag vraag = new Vraag("Wat is Java?", "technisch");
        vraag.setVraagtype("algemeen");
        assertEquals("algemeen", vraag.getVraagtype());
    }

    @Test
    public void testMultipleInstances() {
        Vraag vraag1 = new Vraag("Wat is Java?", "technisch");
        Vraag vraag2 = new Vraag("Wat is een class?", "technisch");
        assertEquals(0, vraag1.getId());
        assertEquals(1, vraag2.getId());
    }

    @Test
    public void testGetVraagTekst() {
        Vraag vraag = new Vraag("Wat is Java?", "technisch");
        assertEquals("Wat is Java?", vraag.getVraagTekst());
    }
}
