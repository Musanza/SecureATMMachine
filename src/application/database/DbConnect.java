package application.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {

    public static Connection Connector(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:atmDb.sqlite");
            return con;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
