package Data;

import Domain.Login;
import Interfaces.IConnectionDB;

import java.sql.*;

public class ConnectionDB implements IConnectionDB {

    static Connection connection = null;
    private static ConnectionDB instance = new ConnectionDB();

    private ConnectionDB () {
        if (connection == null)
            connect();
    }

    public static void connect() {
        System.out.println("Attempting to connect to database.");
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://credatabase.crp07nas4ydg.us-east-1.rds.amazonaws.com:5432/creDataBase",
                    "postgres",
                    "postgres"
            );

            System.out.println("Connected to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static ConnectionDB getInstance() {
        return instance;
    }

}
