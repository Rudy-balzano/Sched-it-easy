package gui.controllers;

import gui.Main;
import gui.roots.Roots;
import javafx.event.ActionEvent;
import core.SessionFacade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import org.junit.Test;

import java.io.IOException;

/**
 * Class Controller Login
 * @author baptiste rudy pierre emilie
 * @version 1.0
 */
public class LoginController {
    /**
     * Textfield for username
     */
    @FXML
    private TextField username;
    /**
     * Textfield for password
     */
    @FXML
    private TextField password;
    /**
     * Button for login
     */
    @FXML
    private Button loginButton;
    /**
     * Button for register
     */
    @FXML
    private Button registerButton;

    /**
     * Function used to login a user or an admin
     * @param actionEvent
     */
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
            System.out.println("L'utilisateur " + session.getUserFirstName() + " " + session.getUserLastName() + " est connecté!");
            this.showAlert(Alert.AlertType.CONFIRMATION,owner,"Success","Meeting successfully inserted !");
            SessionFacade.setConnectedAdmin();
        }
        else if (check == 1){
            System.out.println("L'admin " +session.getAdminFirstName() + " " + session.getAdminLastName() + " est connceté!");
            this.showAlert(Alert.AlertType.CONFIRMATION,owner,"Success","Meeting successfully inserted !");
            SessionFacade.setConnectedUser();
        }
        try {
            switchToHomeView();
        } catch (IOException e) {
            e.printStackTrace();
        }
        username.setText("");
        password.setText("");

    }

    /**
     * function used to go to register page
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void switchToRegisterView (ActionEvent actionEvent) throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.registerRoot))));
        /*
        Parent registerRoot = FXMLLoader.load(getClass().getResource(Roots.registerRoot));
        Scene registerView = new Scene(registerRoot);
        Main.scheditWindow.setScene(registerView);

         */

    }

    /**
     * Function used to go to home page of user or manager
     * @throws IOException
     */
    private void switchToHomeView () throws IOException {
        if (SessionFacade.getConnectedUser() != null) {
            if(SessionFacade.getConnectedUser().getIsManager()) {
                Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerHomeRoot))));
            }
            else {
                Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.userHomeRoot))));
            }
        }
        else if (SessionFacade.getConnectedAdmin() != null){
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.adminHomeRoot))));
        }

    }


    /**
     * Function to show an alert
     * @param alertType
     * @param owner
     * @param title
     * @param message
     */
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}
