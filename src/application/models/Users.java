package application.models;

import application.database.DbConnect;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
    public static String Name, sQuestion, Phone;
    Connection con;

    public Users(){
        con = DbConnect.Connector();
        if (con == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
    }

    public boolean isDbConnected() {
        try {
            return !con.isClosed();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLogin(String pass) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM users_tbl WHERE pin = ?";

        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, pass);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                String phone = resultSet.getString("phone");
                Phone = phone;
                String name = resultSet.getString("name");
                Name = name;
                String question = resultSet.getString("question");
                sQuestion = question;

                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }

    public boolean isLoginSecurity(String secQueston, String secAnswer) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM users_tbl WHERE question = ? AND answer = ?";

        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, secQueston);
            preparedStatement.setString(2, secAnswer);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                String name = resultSet.getString("name");
                Name = name;

                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
}