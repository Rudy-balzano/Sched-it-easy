package gui.controllers;

import core.SessionFacade;
import gui.Main;
import gui.roots.Roots;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Class controller for the user header
 * @version 1.0
 */
public class UserHeaderController {
    /**
     * Function used to go to the create meeting page
     * @param actionEvent
     * @throws IOException
     */
    public void handleReservation(ActionEvent actionEvent) throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.createMeetingRoot))));

    }

    /**
     * Function used to go to the Account informations page
     * @throws IOException
     */
    public void switchToSeeAccount() throws IOException{
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.userProfileRoot))));
    }

    /**
     * Function used to go to the Home page
     * @throws IOException
     */
    public void switchToHome() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.userHomeRoot))));
    }

    /**
     * Function used to logout the user
     * @throws IOException
     */
    public void logout() throws IOException {
        SessionFacade.logout();
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.loginRoot))));
    }

    /**
     * Function used to go to the user notification page
     * @throws IOException
     */
    public void switchToSeeNotification() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.userInvitationRoot))));
    }
}
