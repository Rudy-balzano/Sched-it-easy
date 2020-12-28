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

    @FXML
    public void handleLogin(ActionEvent actionEvent) {
        Window owner = loginButton.getScene().getWindow();

        if (username.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your username");
            return;
        }
        if (password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        SessionFacade session = new SessionFacade();

        String userN = username.getText();
        String pass = password.getText();

        int check = -1;

        check = session.login(userN, pass);

        if (check == 0){
            try {
                switchToHomeView();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("L'utilisateur " + session.getUserFirstName() + " " + session.getUserLastName() + " est connecté!");
        }
        else if (check == 1){
            System.out.println("L'admin " +session.getAdminFirstName() + " " + session.getAdminLastName() + " est connceté!");
        }
        username.setText("");
        password.setText("");

    }

    @FXML
    public void switchToRegisterView (ActionEvent actionEvent) throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.registerRoot))));
        /*
        Parent registerRoot = FXMLLoader.load(getClass().getResource(Roots.registerRoot));
        Scene registerView = new Scene(registerRoot);
        Main.scheditWindow.setScene(registerView);

         */

    }

    private void switchToHomeView () throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.homeRoot))));
        /*
        Parent homeRoot = FXMLLoader.load(getClass().getResource(Roots.homeRoot));
        Scene homeView = new Scene(homeRoot);
        Main.scheditWindow.setScene(homeView);
         */

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
