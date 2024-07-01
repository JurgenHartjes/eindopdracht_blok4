package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.model.Gebruiker;
import nl.hu.bep.shopping.model.Vraag;
import nl.hu.bep.shopping.model.Vragenlijst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/vragenlijst")
public class vragenlijstResource extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewOnlyParam = req.getParameter("viewOnly");
        boolean viewOnly = Boolean.parseBoolean(viewOnlyParam);

        String id = req.getParameter("id");
        log("Received id: " + id);

        if (id == null || id.isEmpty()) {
            resp.getWriter().println("ID parameter is missing");
            return;
        }

        ArrayList<Vragenlijst> vragenlijsten = (ArrayList<Vragenlijst>) getServletContext().getAttribute("vragenlijsten");
        Vragenlijst vragenlijst = null;

        if (vragenlijsten != null) {
            for (Vragenlijst v : vragenlijsten) {
                if (v.getId().equals(id)) {
                    vragenlijst = v;
                    break;
                }
            }
        }

        if (vragenlijst != null) {
            log("Found vragenlijst: " + vragenlijst);
            getServletContext().setAttribute("huidigeVragenlijst", vragenlijst);
            List<Vraag> questions = vragenlijst.getVragen();
            req.setAttribute("questions", questions);
            req.getRequestDispatcher("/vragenlijst.jsp").forward(req, resp);
        } else {
            resp.getWriter().println("Geen vragenlijst gevonden");
            log("No vragenlijst found for id: " + id);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Vragenlijst vragenlijst = (Vragenlijst) getServletContext().getAttribute("huidigeVragenlijst");
        log("get huidigeVragenlijst = " + vragenlijst);

        int i = 1;
        for (Vraag vraag : vragenlijst.getVragen()) {
            if (vraag.getVraagtype().equals("vijfSterren")) {
                String sterrenRating = req.getParameter("starRating" + i);
                vraag.setAntwoord(sterrenRating);
                System.out.println("Sterren antwoord voor vraag " + i + ": " + sterrenRating);
            } else {
                String antwoord = req.getParameter("vraag" + i);
                vraag.setAntwoord(antwoord);
                System.out.println("Tekst antwoord voor vraag " + i + ": " + antwoord);
            }
            i++;
        }

        Gebruiker gebruiker = (Gebruiker) req.getSession().getAttribute("huidigeGebruiker");

        if (vragenlijst != null && gebruiker != null) {
            gebruiker.addIngevuldeLijst(vragenlijst);
            resp.sendRedirect("/overzicht.jsp");
        } else {
            resp.getWriter().println("<html><body>");
            resp.getWriter().println("<h1>Error</h1>");
            resp.getWriter().println("<p>Geen vragenlijst of gebruiker gevonden</p>");
            resp.getWriter().println("</body></html>");
        }
    }

}
