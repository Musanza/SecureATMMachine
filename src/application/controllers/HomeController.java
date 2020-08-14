package application.controllers;

import application.models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public Users users = new Users();

    @FXML
    private Text txtName;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getName (String userName){
        txtName.setText(String.valueOf(Users.Name));
    }

    public void onBack(ActionEvent actionEvent) {
        try {
            // Close previous window
            ((Node)actionEvent.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("../ui/card.fxml"));
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setResizable(false);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
