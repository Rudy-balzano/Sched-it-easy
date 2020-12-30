package gui.controllers;

import gui.Main;
import gui.roots.Roots;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;


public class UserHomeController {


    public void handleReservation(ActionEvent actionEvent) throws IOException {
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.askCreateMeetingRoot))));

    }
}
