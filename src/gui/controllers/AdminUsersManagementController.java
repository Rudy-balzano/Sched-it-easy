package gui.controllers;

import core.AdminAccountManagementFacade;
import gui.Main;
import gui.roots.Roots;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.util.Collection;

public class AdminUsersManagementController {
    @FXML
    private ListView<HBoxCell> listViewU;
    @FXML
    private ListView<HBoxCell> listViewM;
    @FXML
    private Button addAdminButton;

    public static class HBoxCell extends HBox {
        Label label = new Label();
        Button button = new Button("See information");

        HBoxCell(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            this.getChildren().addAll(label, button);
        }
    }

    @FXML
    public void initialize(){
        //TODO Link row button to the row and to a method

        AdminAccountManagementFacade facade = new AdminAccountManagementFacade();

        //Load users data from persistent layer to display it on the view
        Collection<String> regUsers = facade.getAllRegUserNames();
        Collection<String> managUsers = facade.getAllManagersNames();

        ObservableList<HBoxCell> itemsU = FXCollections.observableArrayList();
        for (String name : regUsers){
            //Consider using iterator
            HBoxCell hbc = new HBoxCell(name);
            itemsU.add(hbc);
        }
        ObservableList<HBoxCell> itemsM = FXCollections.observableArrayList();
        for (String name : managUsers){
            //Consider using iterator
            HBoxCell hbc = new HBoxCell(name);
            itemsM.add(hbc);
        }

        listViewU.setItems(itemsU);
        listViewM.setItems(itemsM);
    }

    public void switchToAdminRegisterView(){
        try {
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.adminRegisterRoot))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewAdmin(){
        //Load a view that allows an admin to register for another admin
        switchToAdminRegisterView();
    }

}
