package gui.controllers;

import core.SessionFacade;
import gui.Main;
import gui.roots.Roots;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class UserHeaderController {
    public void handleReservation(ActionEvent actionEvent) throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.createMeetingRoot))));

    }

    public void switchToSeeAccount() throws IOException{
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.userProfileRoot))));
    }

    public void switchToHome() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.userHomeRoot))));
    }

    public void logout() throws IOException {
        SessionFacade.logout();
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.loginRoot))));
    }
}
