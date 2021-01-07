package gui.controllers;

import core.SessionFacade;
import gui.Main;
import gui.roots.Roots;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Class controller for the admin header
 * @author emilie jean
 * @version 1.0
 */
public class AdminMenuController {

    /**
     *Function used to go to the room topic page
     * @throws IOException
     */
    public void switchToRoomTopic() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.roomTopicRoot))));
    }

    /**
     * Function used to go to the see accounts page
     * @throws IOException
     */
    public void switchToSeeAccounts() throws IOException{
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.adminUserManagementRoot))));
    }

    /**
     *Function used to go to the home page
     * @throws IOException
     */
    public void switchToHome() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.adminHomeRoot))));
    }

    /**
     * Function used to logout the admin
     * @throws IOException
     */
    public void logout() throws IOException {
        SessionFacade.logout();
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.loginRoot))));
    }
}
