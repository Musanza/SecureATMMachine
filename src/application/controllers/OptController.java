package application.controllers;

import application.models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class OptController implements Initializable {

    public Users users = new Users();
    @FXML
    private PasswordField ftOTP;

    @FXML
    private Text txtConnection;

    boolean isOperatorPressed;

    @FXML
    private Text txtOTP;

    @FXML
    private Text txtPhone;

    @FXML
    private Text txtName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getOTP(String otp){
        txtOTP.setText(otp);
        txtOTP.setVisible(false);
    }
    public void getPhone(String phone){
        txtPhone.setText(String.valueOf(Users.Phone));
        txtPhone.setVisible(false);
    }
    public void getName(String name){
        txtName.setText(String.valueOf(Users.Name));
    }

    public void onNumberClick(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button){
            Button btn = (Button)actionEvent.getSource();
            if (isOperatorPressed){
                ftOTP.setText(btn.getText().trim());
            } else {
                ftOTP.setText(ftOTP.getText().trim() + btn.getText().trim());
            }
            isOperatorPressed = false;
        }
    }

    public void onDelete(ActionEvent actionEvent) {
        if (ftOTP.getText().length() > 0){
            ftOTP.setText(ftOTP.getText(0, ftOTP.getText().length() -1));
        }
    }

    public void onOTPSuccess(ActionEvent actionEvent) {
        try {
            if (ftOTP.getText().equals(txtOTP.getText())){
                ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                Stage primaryStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader();
                Pane root = fxmlLoader.load(getClass().getResource("../ui/home.fxml").openStream());
                primaryStage.initStyle(StageStyle.DECORATED);
                primaryStage.setResizable(false);

                HomeController homeController = (HomeController) fxmlLoader.getController();
                homeController.getName(ftOTP.getText());

                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            } else {
                txtConnection.setText("Wrong OTP. Try again");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
