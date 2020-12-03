package gui.controllers;

import core.SessionFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    private SessionFacade session = new SessionFacade();

    public void handleLogin(javafx.event.ActionEvent actionEvent) {
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

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}
