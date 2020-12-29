package gui.controllers;

import gui.Main;
import gui.roots.Roots;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

import core.SessionFacade;
import core.User;

public class HomeController {

    User u = SessionFacade.getConnectedUser();

    public void handleReservation(ActionEvent actionEvent) throws IOException {
        if (u.getIsManager()) {
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.createMeetingRoot))));
        }
        else {
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.askCreateMeetingRoot))));
        }
    }
}
