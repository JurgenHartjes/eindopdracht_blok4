package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.model.Gebruiker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/logMeIn")
public class inlogScherm extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("userMail");
        String password = req.getParameter("userPassword");

        boolean userFound = false;
        ArrayList<Gebruiker> users = Gebruiker.getAlleGebruikers();

        for (Gebruiker user : users) {
            if (user.getMailAdres().equals(email) && user.getWachtwoord().equals(password)) {
                userFound = true;

                // Store the user object in session
                HttpSession session = req.getSession();
                session.setAttribute("huidigeGebruiker", user);

                resp.sendRedirect("/overzicht.jsp");
                break;
            }
        }

        if (!userFound) {
            resp.sendRedirect("/inlogPagina.html?error=true");
        }
    }
}
