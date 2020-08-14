package application.controllers;

import application.models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionController implements Initializable {

    public Users users = new Users();
    @FXML
    private Text txtUsername;

    @FXML
    private Text txtQuestion;

    @FXML
    private TextField tfAnswer;

    @FXML
    private Text txtWrongAnswer;

    @FXML
    private Text txtName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getQuestion (String userQuestion){
        txtQuestion.setText(String.valueOf(Users.sQuestion));
    }
    public void getName(String name){
        txtName.setText(String.valueOf(Users.Name));
    }


    public void questionLogin(ActionEvent actionEvent) {
        try {
            if (users.isLoginSecurity(txtQuestion.getText(), tfAnswer.getText())){
                txtWrongAnswer.setText("Correct Answer");

                ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                Stage primaryStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader();
                Pane root = fxmlLoader.load(getClass().getResource("../ui/home.fxml").openStream());
                primaryStage.initStyle(StageStyle.DECORATED);
                primaryStage.setResizable(false);
                HomeController homeController = (HomeController) fxmlLoader.getController();
                homeController.getName(txtQuestion.getText());

                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            } else {
                txtWrongAnswer.setText("Wrong answer. Try again.");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
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
