package gui.controllers;

import gui.Main;
import gui.roots.Roots;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ManagerMenuController {

    public void handleAccountValidation(ActionEvent actionEvent) throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerAccountValidationRoot))));
    }

    public void switchToManageGroups(ActionEvent actionEvent) throws IOException{
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerManageGroupRoot))));
    }

}
