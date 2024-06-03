package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.model.Gebruiker;
import nl.hu.bep.shopping.model.Vraag;
import nl.hu.bep.shopping.model.Vragenlijst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/vragenlijst")
public final class vragenlijstResource extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> questions = new ArrayList<>();

        Vragenlijst vragenlijst = (Vragenlijst) getServletContext().getAttribute("huidigeVragenlijst");       //haalt de vragenlijst op

        if (vragenlijst != null) {      //zeker weten dat er een vragenlijst is gevonden om errors te voorkomen

            for (Vraag vraag : vragenlijst.getVragen()) {       //loopt door alle vragen heen

                questions.add(vraag.getVraag());        //haalt voor elke Vraag de vraag op, zodat deze kan worden weergegeven

            }

        } else  {   System.out.println("geen vragenlijst gevonden"   );    }

        req.setAttribute("questions", questions);       //zet de variabele "questions"
        req.getRequestDispatcher("/vragenlijst.jsp").forward(req, resp);        //zorgt dat het bestand vragenlijst.jsp wordt geopend

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        Vragenlijst vragenlijst = (Vragenlijst) getServletContext().getAttribute("huidigeVragenlijst");       //haalt de huidige vragenlijst op

        if (vragenlijst != null) {      //zeker weten dat er een vragenlijst is gevonden om errors te voorkomen

            resp.getWriter().println("<html><body>");       //beginnen met het opbouwen van een "ingeleverd" HTML
            resp.getWriter().println("<h1>Form Submitted</h1>");

            int questionIndex = 1;      //houdt het vragennummer bij

            for (Vraag vraag : vragenlijst.getVragen()) {       //loopt door alle vragen uit de vragenlijst

                String parameterName = "vraag" + questionIndex;
                String answer = req.getParameter(parameterName);        //haalt het antwoord op
                resp.getWriter().println("<p>" + questionIndex + ". " + vraag.getVraag() + ": " + answer + "</p>");     //geeft voor elke vraag het ingevulde antwoord weer


                vraag.setAntwoord(answer);

                questionIndex++;        //zorgt dat het vraagnummer wordt aangepast voor de volgende vraag
            }

            Gebruiker gebruiker = (Gebruiker) getServletContext().getAttribute("huidigeGebruiker");       //haalt de huidige gebruiker op
            vragenlijst.setMomentVanInvoer();

            if (gebruiker != null) {

                gebruiker.ingevuldeVragenlijstToevoegen(vragenlijst);
                File file = new File("src/main/java/nl/hu/bep/shopping/filledForms/user" + gebruiker.getKlantenNr() + ".obj"); //geeft het pad naar de bestemde locatie van het bestand aan

                boolean result;

                try {

                    result = file.createNewFile();  //maakt een nieuwe bestand aan

                    if(result)  {       //als het bestand nog niet bestond geeft het aan dat deze is aangemaakt

                        System.out.println("file created "+file.getCanonicalPath());

                    }
                    else {

                        System.out.println("File already exist at location: "+file.getCanonicalPath());     //anders geeft het aan dat deze al bestond

                    }

                }

                catch (IOException e) {
                    e.printStackTrace();
                }

                try (OutputStream os = Files.newOutputStream(Path.of(file.toURI()));

                     ObjectOutputStream oos = new ObjectOutputStream(os)) {
                     oos.writeObject(gebruiker);        //Schrijft alle informatie over de gebruiker naar een bestand

                } catch (IOException e) {

                    e.printStackTrace();

                }


            } else {

                System.out.println("Geen gebruiker gevonden");

            }

            resp.getWriter().println("</body></html>");     //beindigd het HTML bestand

        } else {

            resp.getWriter().println("<html><body>");       //voor het geval de vragenlijst niet gevonden wordt
            resp.getWriter().println("<h1>Error</h1>");
            resp.getWriter().println("<p>Geen vragenlijst gevonden</p>");
            resp.getWriter().println("</body></html>");

        }

    }

}
