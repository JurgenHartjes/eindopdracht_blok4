package nl.hu.bep.shopping.webservices;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.bep.shopping.model.Gebruiker;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createAccount")
public class createAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String userName = request.getParameter("userName");
        String userMail = request.getParameter("userMail");
        String userPhone = request.getParameter("userPhone");
        String userPassword = request.getParameter("userPassword");
        String userConfirmPassword = request.getParameter("userConfirmPassword");
        int userCustomerNumber = Integer.parseInt(request.getParameter("userCustomerNumber"));

        // Validate form parameters
        if (userName == null || userMail == null || userPhone == null || userPassword == null || userConfirmPassword == null
                || userName.isEmpty() || userMail.isEmpty() || userPhone.isEmpty() || userPassword.isEmpty() || userConfirmPassword.isEmpty()) {
            // Handle invalid input
            response.sendRedirect("createAccount.html");
            return;
        }

        // Check if passwords match
        if (!userPassword.equals(userConfirmPassword)) {
            // Handle passwords not matching
            response.sendRedirect("createAccount.html");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File(getClass().getClassLoader().getResource("user.json").getFile());
        boolean userFound = false;

        List<Gebruiker> users = mapper.readValue(jsonFile, new TypeReference<List<Gebruiker>>() {});
        for (Gebruiker user : users) {
            if (user.getMailAdres().equals(userMail)) {
                userFound = true;
                response.sendRedirect("accountExists.html");
                break;
            }
        }

        if (!userFound) {

            Gebruiker newUser = new Gebruiker(userName, userMail, userPhone, userPassword, userCustomerNumber);
            users.add(newUser);

            // Write the updated list back to the JSON file
            mapper.writeValue(jsonFile, users);

            request.getSession().setAttribute("accountCreated", true);
            response.sendRedirect("accountCreated.html");
        }
    }
}
