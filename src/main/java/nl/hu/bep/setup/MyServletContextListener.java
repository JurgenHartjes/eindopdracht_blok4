package nl.hu.bep.setup;

import nl.hu.bep.shopping.database.Connect;
import nl.hu.bep.shopping.model.Gebruiker;
import nl.hu.bep.shopping.model.JavaMailSender;
import nl.hu.bep.shopping.model.Vraag;
import nl.hu.bep.shopping.model.Vragenlijst;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Haal alle gebruikers op uit de database en sla ze op in de lijst van alle gebruikers
        Connect connect = new Connect();
        ArrayList<Gebruiker> gebruikers = connect.getAllGebruikers();
        Gebruiker.setAlleGebruikers(gebruikers);

//        Gebruiker gebruiker = new Gebruiker("Jurgen Hartjes", "jurgen.hartjes@student.hu.nl", "0612345678", "ww", 12345);
//        Gebruiker gebruiker2 = new Gebruiker("Jurgen Hartjes", "test", "0612345678", "test", 1234567);

        Vragenlijst vragenlijst = new Vragenlijst("klanttevredenheid");
        vragenlijst.addVraag(new Vraag("Wat vond U van de klantvriendelijkheid van onze medewerkers?", "vijfSterren"));
        vragenlijst.addVraag(new Vraag("Hoe beoordeelt u de netheid van de winkel?", "vijfSterren"));
        vragenlijst.addVraag(new Vraag("Wat vond u van de sfeer in de winkel?", "openVraag"));
        vragenlijst.addVraag(new Vraag("Hoe waarschijnlijk is het dat u ons aanbeveelt aan anderen?", "vijfSterren"));
        vragenlijst.addVraag(new Vraag("Hoe waarschijnlijk is het dat u nogmaals komt winkelen bij deze Jumbo?", "vijfSterren"));
        vragenlijst.saveVragenlijst();

        Vragenlijst vragenlijst2 = new Vragenlijst("klanttevredenheid2");
        vragenlijst2.addVraag(new Vraag("Wat vond U van de klantvriendelijkheid van onze medewerkers?", "vijfSterren"));
        vragenlijst2.addVraag(new Vraag("Hoe beoordeelt u de netheid van de winkel?", "vijfSterren"));
        vragenlijst2.addVraag(new Vraag("Wat vond u van de prijs-kwaliteitverhouding?", "vijfSterren"));
        vragenlijst2.addVraag(new Vraag("Hoe gemakkelijk vond u het om producten te vinden?", "vijfSterren"));
        vragenlijst2.addVraag(new Vraag("Hoe beoordeelt u de wachttijd bij de kassa?", "vijfSterren"));
        vragenlijst2.addVraag(new Vraag("Hoe tevreden bent u met het assortiment?", "openVraag"));
        vragenlijst2.addVraag(new Vraag("Hoe beoordeelt u de versheid van onze producten?", "openVraag"));
        vragenlijst2.addVraag(new Vraag("Wat vond u van de sfeer in de winkel?", "openVraag"));
        vragenlijst2.addVraag(new Vraag("Hoe waarschijnlijk is het dat u ons aanbeveelt aan anderen?", "vijfSterren"));
        vragenlijst2.addVraag(new Vraag("Hoe waarschijnlijk is het dat u nogmaals komt winkelen bij deze Jumbo?", "vijfSterren"));
        vragenlijst2.saveVragenlijst();

        ArrayList<Vragenlijst> lijst = vragenlijst.getAlleVragenlijsten();
        sce.getServletContext().setAttribute("vragenlijsten", lijst);
//        sce.getServletContext().setAttribute("huidigeGebruiker", gebruiker);

        // Send email
        String sender = "jurgen.hartjes@student.hu.nl";
        String password = "fj6YDB5D";
        String receiver = "jurgenhartjes6@gmail.com";
        String subject = "Server Started";
        String body = "The server has started and initialized the context.";

        //JavaMailSender.main(null);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("terminating application");
    }
}
