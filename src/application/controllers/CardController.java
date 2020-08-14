package application.controllers;

import application.models.Users;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CardController implements Initializable {

    public Users users = new Users();
    @FXML
    private PasswordField ftPin;
    @FXML
    private Text txtPinError;
    @FXML
    private VBox optionBox;
    @FXML
    private Text txtSuccess;
    @FXML
    private VBox cardBox;

    Text txtPhone;
    boolean isOperatorPressed;
    public static final String ACCOUNT_SID =
            "ACabc45a5cecffc1f58f99966620545814";
    public static final String AUTH_TOKEN =
            "7cd1d707aa7d7b7c689f2d653621b6f4";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (users.isDbConnected()){
            txtPinError.setText("");
        } else {
            txtPinError.setText("Service not Available. Try Later");
        }
    }

    public void Login(javafx.event.ActionEvent actionEvent){
        try {
            if (users.isLogin(ftPin.getText())){
                txtSuccess.setText("Login Successfull. You can proceed");
                ftPin.setText("");
                txtPinError.setText("");
                optionBox.toFront();
            } else {
                txtPinError.setText("Pin not Recognized. Try again");
            }
        } catch (SQLException e){
            txtPinError.setText("Pin not Recognized. Try again");
            e.printStackTrace();
        }
    }

    public void toQuestion(javafx.event.ActionEvent actionEvent) {
        try {
            // Close previous window
            ((Node)actionEvent.getSource()).getScene().getWindow().hide();

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("../ui/question.fxml").openStream());
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setResizable(false);
            QuestionController questionController = (QuestionController)loader.getController();
            questionController.getQuestion(ftPin.getText());
            questionController.getName(ftPin.getText());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void toOpt(ActionEvent actionEvent) {
        String otpString = generateOTP();
        System.out.println(otpString);

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message
                .creator(new PhoneNumber(String.valueOf(Users.Phone)), // to
                        new PhoneNumber("+14784002980"), // from
                        "Enter OTP " +otpString)
                .create();
        try {
            // Close previous window
            ((Node)actionEvent.getSource()).getScene().getWindow().hide();

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("../ui/opt.fxml").openStream());
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setResizable(false);
            OptController optController = (OptController)loader.getController();
            optController.getOTP(otpString);
            optController.getName(ftPin.getText());
            optController.getPhone(ftPin.getText());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String generateOTP(){
        int randomPin = (int)(Math.random()*9000)+1000;
        String otp = String.valueOf(randomPin);
        return otp;
    }

    public void sendBack(MouseEvent mouseEvent) {
        optionBox.toBack();
        ftPin.setText("");
        txtSuccess.setText("");
        txtPinError.setText("");
    }

    public void onNumberClick(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button){
            Button btn = (Button)actionEvent.getSource();
            if (isOperatorPressed){
                ftPin.setText(btn.getText().trim());
            } else {
                ftPin.setText(ftPin.getText().trim() + btn.getText().trim());
            }
            isOperatorPressed = false;
        }
    }

    public void onDelete(ActionEvent actionEvent) {
        if (ftPin.getText().length() > 0){
            ftPin.setText(ftPin.getText(0, ftPin.getText().length() -1));
        }
    }

    public void onBack(ActionEvent actionEvent) {
        try {
            // Close previous window
            ((Node)actionEvent.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("../ui/main.fxml"));
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
