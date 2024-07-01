package nl.hu.bep.shopping;

import nl.hu.bep.shopping.model.Vragenlijst;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VragenlijstTest {

    @Test
    public void testVragenlijstConstruction() {
        Vragenlijst vragenlijst = new Vragenlijst("Test Vragenlijst");
        assertEquals("Test Vragenlijst", vragenlijst.getName());
        assertEquals(0, vragenlijst.getVragen().size());
    }
    
}
