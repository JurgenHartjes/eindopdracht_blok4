package nl.hu.bep.setup;

import nl.hu.bep.shopping.model.Gebruiker;
import nl.hu.bep.shopping.model.Vraag;
import nl.hu.bep.shopping.model.Vragenlijst;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;



@WebListener
public class MyServletContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Gebruiker gebruiker = new Gebruiker("Jurgen Hartjes", "jurgen.hartjes@student.hu.nl", "0612345678", "ww", "12345");
        Vragenlijst vragenlijst = new Vragenlijst("klanttevredenheid");
        vragenlijst.addVraag(new Vraag("Wat vond U van de klantvriendelijkheid van onze medewerkers?"));        //een vragenlijst met vragen aanmaken
        vragenlijst.addVraag(new Vraag("Hoe beoordeelt u de netheid van de winkel?"));
        vragenlijst.addVraag(new Vraag("Wat vond u van de prijs-kwaliteitverhouding?"));
        vragenlijst.addVraag(new Vraag("Hoe gemakkelijk vond u het om producten te vinden?"));
        vragenlijst.addVraag(new Vraag("Hoe beoordeelt u de wachttijd bij de kassa?"));
        vragenlijst.addVraag(new Vraag("Hoe tevreden bent u met het assortiment?"));
        vragenlijst.addVraag(new Vraag("Hoe beoordeelt u de versheid van onze producten?"));
        vragenlijst.addVraag(new Vraag("Wat vond u van de sfeer in de winkel?"));
        vragenlijst.addVraag(new Vraag("Hoe waarschijnlijk is het dat u ons aanbeveelt aan anderen?"));

        sce.getServletContext().setAttribute("huidigeVragenlijst", vragenlijst);      //zet de vragenlijst als variabele zodat deze makkelijk teruggevonden kan worden
        sce.getServletContext().setAttribute("huidigeGebruiker", gebruiker);       //koppelt het account aan de gebruiker
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("terminating application");
    }

}
