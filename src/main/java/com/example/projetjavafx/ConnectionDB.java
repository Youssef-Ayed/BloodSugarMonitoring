package com.example.projetjavafx;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private Connection cnx;

    String url = "jdbc:mysql://localhost:3306/javafx";

    String user = "root";
    String pwd = "root";
    public static ConnectionDB ct;

    private ConnectionDB() {
        try {
            cnx = DriverManager.getConnection(url,user,pwd);
            System.out.println("Cnx BD etablie avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static ConnectionDB getInstance(){
        if(ct ==null)
            ct= new ConnectionDB();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
}
