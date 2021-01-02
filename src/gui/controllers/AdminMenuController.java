package gui.controllers;

import core.SessionFacade;
import gui.Main;
import gui.roots.Roots;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class AdminMenuController {

    public void switchToRoomTopic() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.roomTopicRoot))));
    }

    public void switchToSeeAccounts() throws IOException{
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.adminUserManagementRoot))));
    }

    public void switchToHome() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.adminHomeRoot))));
    }

    public void logout() throws IOException {
        SessionFacade.logout();
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.loginRoot))));
    }
}
