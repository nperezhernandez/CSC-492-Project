package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("admin") && password.equals("password")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/welcome.fxml"));
                Scene welcomeScene = new Scene(loader.load(), 800, 700);

                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(welcomeScene);
                stage.setTitle("CSUDH Course Enrollment Demand");

            } catch (Exception e) {
                messageLabel.setText("Error loading dashboard.");
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Invalid username or password.");
        }
    }
}