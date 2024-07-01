package nl.hu.bep.setup;

import nl.hu.bep.shopping.database.ConnectGebruiker;
import nl.hu.bep.shopping.model.EmailService;
import nl.hu.bep.shopping.model.Gebruiker;
import nl.hu.bep.shopping.model.Vraag;
import nl.hu.bep.shopping.model.Vragenlijst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize context: load users, load surveys, etc.
        loadAndInitializeData(sce);

        // Example: Sending an email during initialization
        String recipientEmail = "jurgenextra@gmail.com";
        //EmailService.sendEmail(recipientEmail);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Save all relevant data when the application is terminated
        saveAllData();
        System.out.println("Application terminated.");
    }

    private void loadAndInitializeData(ServletContextEvent sce) {
        // Load all users from database
        ArrayList<Gebruiker> gebruikers = ConnectGebruiker.getAllGebruikers();
        Gebruiker.setAlleGebruikers(gebruikers);

        // Create and save example surveys
        createAndSaveExampleSurveys();

        // Store surveys in ServletContext
        ArrayList<Vragenlijst> vragenlijsten = Vragenlijst.getAlleVragenlijsten();
        sce.getServletContext().setAttribute("vragenlijsten", vragenlijsten);
    }

    private void saveAllData() {
        // Save all users to database
        ArrayList<Gebruiker> gebruikers = Gebruiker.getAlleGebruikers();
        ConnectGebruiker.saveAlleGebruikersToDatabase(gebruikers);

        // Optionally, save other data if needed
    }

    private void createAndSaveExampleSurveys() {
        // Create example surveys
        Vragenlijst vragenlijst = new Vragenlijst("klanttevredenheid");
        vragenlijst.addVraag(new Vraag("Wat vond U van de klantvriendelijkheid van onze medewerkers?", "vijfSterren"));
        // Add more questions...
        vragenlijst.saveVragenlijst();

        Vragenlijst vragenlijst2 = new Vragenlijst("klanttevredenheid2");
        vragenlijst2.addVraag(new Vraag("Wat vond U van de klantvriendelijkheid van onze medewerkers?", "vijfSterren"));
        // Add more questions...
        vragenlijst2.saveVragenlijst();
    }
}

