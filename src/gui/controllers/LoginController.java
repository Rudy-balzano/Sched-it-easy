package gui.controllers;

import gui.Main;
import gui.roots.Roots;
import javafx.event.ActionEvent;
import core.SessionFacade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    private SessionFacade session = new SessionFacade();

    @FXML
    public void handleLogin(ActionEvent actionEvent) {
        Window owner = loginButton.getScene().getWindow();

        if (username.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }
        String userN = username.getText();
        String pass = password.getText();

        boolean check = false;

        check = session.login(userN, pass);

        if (check){
            System.out.println("L'utilisateur " + session.getFirstName() + " " + session.getLastName() + " est connecté!");
        }
        username.setText("");
        password.setText("");

    }

    @FXML
    public void switchToRegisterView (ActionEvent actionEvent) throws IOException {
        Parent registerRoot = FXMLLoader.load(getClass().getResource(Roots.registerRoot));
        Scene registerView = new Scene(registerRoot);
        Main.scheditWindow.setScene(registerView);

    }


    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}
