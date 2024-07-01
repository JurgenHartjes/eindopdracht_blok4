package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.model.Gebruiker;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class logout extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("huidigeGebruiker", null);
        response.sendRedirect("/inlogPagina.html");
    }

}

