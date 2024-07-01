package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.model.Gebruiker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-gegevens")
public class updateGegevens extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verkrijg de huidige gebruiker uit de sessie
        Gebruiker currentGebruiker = (Gebruiker) request.getSession().getAttribute("huidigeGebruiker");

        if (currentGebruiker == null) {
            response.sendRedirect("/inlogPagina.html");
            return;
        }

        // Verkrijg de nieuwe gegevens vanuit het formulier
        String naam = request.getParameter("naam");
        String email = request.getParameter("email");
        String telefoonnummer = request.getParameter("telefoonnummer");
        String wachtwoord = request.getParameter("wachtwoord");

        // Update de gegevens van de gebruiker
        currentGebruiker.setNaam(naam);
        currentGebruiker.setMailAdres(email);
        currentGebruiker.setTelefoonnummer(telefoonnummer);
        currentGebruiker.setWachtwoord(wachtwoord);

        // Je zou hier ook validatie kunnen toevoegen voordat je de gegevens bijwerkt

        // Redirect naar de profielpagina of een andere pagina
        response.sendRedirect("/mijn-profiel.jsp");
    }
}
