package nl.hu.bep.shopping;

import nl.hu.bep.shopping.database.Connect;

import java.sql.Connection;

public class DatabaseTest {
    public static void main(String[] args) {
        Connect dbConnect = new Connect();
        Connection connection = dbConnect.connect();
        if (connection != null) {
            System.out.println("Connection test to PostgreSQL server was successful.");
        } else {
            System.out.println("Connection test to PostgreSQL server failed.");
        }
    }
}