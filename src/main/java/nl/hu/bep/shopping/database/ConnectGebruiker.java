package nl.hu.bep.shopping.database;

import nl.hu.bep.shopping.model.Gebruiker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectGebruiker {

    public static void main(String[] args) {
    }

    public static void saveKlantToDatabase(int klantennr, String naam, String mailadres, String telefoonnummer, String wachtwoord) {
        ConnectGebruiker dbConnect = new ConnectGebruiker();
        Connection connection = dbConnect.connect();
        if (connection != null) {
            System.out.println("Connected to the PostgreSQL server successfully.");
            dbConnect.insertOrUpdateGebruiker(connection, klantennr, naam, mailadres, telefoonnummer, wachtwoord);
        } else {
            System.out.println("Failed to make connection!");
        }
    }

    public static void saveAlleGebruikersToDatabase(ArrayList<Gebruiker> gebruikers) {
        for (Gebruiker gebruiker : Gebruiker.getAlleGebruikers())   {
            saveKlantToDatabase(gebruiker.getKlantenNr(), gebruiker.getNaam(), gebruiker.getMailAdres(), gebruiker.getTelefoonnummer(), gebruiker.getWachtwoord());
        }
    }

    public static Connection connect() {
        Connection conn = null;
        String url = "jdbc:postgresql://localhost:5432/Jumbo_vragenlijst_DB";
        String user = "postgres";
        String password = "fj6YDB5D";

        try {
            Class.forName("org.postgresql.Driver");  // Explicitly load the driver
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to PostgreSQL server established.");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        }

        return conn;
    }

    public void insertOrUpdateGebruiker(Connection conn, int klantennr, String naam, String mailadres, String telefoonnummer, String wachtwoord) {
        // Check if Gebruiker with given klantennr already exists
        Gebruiker existingGebruiker = getGebruikerByKlantennr(conn, klantennr);

        if (existingGebruiker != null) {
            // Update existing Gebruiker
            updateGebruiker(conn, klantennr, naam, mailadres, telefoonnummer, wachtwoord);
        } else {
            // Insert new Gebruiker
            insertGebruiker(conn, klantennr, naam, mailadres, telefoonnummer, wachtwoord);
        }
    }

    private Gebruiker getGebruikerByKlantennr(Connection conn, int klantennr) {
        String sql = "SELECT klantennr, naam, mailadres, telefoonnummer, wachtwoord FROM gebruiker WHERE klantennr = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, klantennr);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String naam = rs.getString("naam");
                String mailadres = rs.getString("mailadres");
                String telefoonnummer = rs.getString("telefoonnummer");
                String wachtwoord = rs.getString("wachtwoord");

                return new Gebruiker(naam, mailadres, telefoonnummer, wachtwoord, klantennr);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return null;
    }

    private void updateGebruiker(Connection conn, int klantennr, String naam, String mailadres, String telefoonnummer, String wachtwoord) {
        System.out.println("updating gebruiker.");
        String sql = "UPDATE gebruiker SET naam = ?, mailadres = ?, telefoonnummer = ?, wachtwoord = ? WHERE klantennr = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, naam);
            stmt.setString(2, mailadres);
            stmt.setString(3, telefoonnummer);
            stmt.setString(4, wachtwoord);
            stmt.setInt(5, klantennr);

            System.out.println("updating gebruiker..");

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Gebruiker updated successfully.");
            } else {
                System.out.println("Failed to update Gebruiker with klantennr: " + klantennr);
            }
            System.out.println("updating gebruiker...");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        System.out.println("updating gebruiker done!");
    }

    private void insertGebruiker(Connection conn, int klantennr, String naam, String mailadres, String telefoonnummer, String wachtwoord) {
        String sql = "INSERT INTO gebruiker (klantennr, naam, mailadres, telefoonnummer, wachtwoord) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, klantennr);
            stmt.setString(2, naam);
            stmt.setString(3, mailadres);
            stmt.setString(4, telefoonnummer);
            stmt.setString(5, wachtwoord);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Gebruiker inserted successfully.");
            } else {
                System.out.println("Failed to insert Gebruiker with klantennr: " + klantennr);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public static ArrayList<Gebruiker> getAllGebruikers() {
        ArrayList<Gebruiker> gebruikers = new ArrayList<>();
        String sql = "SELECT klantennr, naam, mailadres, telefoonnummer, wachtwoord FROM gebruiker";

        try (Connection conn = connect();
             Statement stmt = conn != null ? conn.createStatement() : null;
             ResultSet rs = stmt != null ? stmt.executeQuery(sql) : null) {

            if (rs != null) {
                while (rs.next()) {
                    int klantennr = rs.getInt("klantennr");
                    String naam = rs.getString("naam");
                    String mailadres = rs.getString("mailadres");
                    String telefoonnummer = rs.getString("telefoonnummer");
                    String wachtwoord = rs.getString("wachtwoord");

                    Gebruiker gebruiker = new Gebruiker(naam, mailadres, telefoonnummer, wachtwoord, klantennr);
                    gebruikers.add(gebruiker);
                }
            } else {
                System.out.println("ResultSet is null.");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return gebruikers;
    }
}
