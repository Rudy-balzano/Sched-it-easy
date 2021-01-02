package gui.controllers;

import core.SessionFacade;
import gui.Main;
import gui.roots.Roots;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ManagerMenuController {

    public void switchToAccountValidation() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerAccountValidationRoot))));
    }

    public void switchToManageGroups() throws IOException{
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerManageGroupRoot))));
    }

    public void switchToMeetingValidation() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerMeetingValidationRoot))));
    }

    public void handleReservation() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.createMeetingRoot))));
    }

    public void logout() throws IOException {
        SessionFacade.logout();
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.loginRoot))));
    }
}
