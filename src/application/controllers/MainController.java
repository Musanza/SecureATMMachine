package application.controllers;

import application.models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public Users users = new Users();
    @FXML
    private Text txtConnection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (users.isDbConnected()){
            txtConnection.setText("Insert your Card to Begin");
        } else {
            txtConnection.setText("Service not Available. Try Later");
        }
    }

    public void onClose(ActionEvent actionEvent) {
    }

    public void openScene(MouseEvent mouseEvent) {
        try {
            ((Node) mouseEvent.getSource()).getScene().getWindow().hide();
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
