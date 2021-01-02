package gui.controllers;

import core.AdminAccountManagementFacade;
import core.User;
import gui.Main;
import gui.roots.Roots;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;

public class AdminUsersManagementController {
    @FXML
    private ListView<HBoxCell> listViewU;
    @FXML
    private ListView<HBoxCell> listViewM;

    final static AdminAccountManagementFacade facade = new AdminAccountManagementFacade();

    private void displayPopupUserInfo(User usr){
        //Popup that displays information about the selected user
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("User information");

        Label fname = new Label("First name : " + usr.getFirstName());
        Label lname = new Label("Last name : " + usr.getLastName());
        Label isManager = new Label("Is Manager : " + usr.getIsManager().toString());

        Button b = new Button("Close");
        b.setOnAction(actionEvent -> popup.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(fname,lname,isManager,b);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout,200,150);
        popup.setScene(scene);
        popup.showAndWait();

    }

    private class HBoxCell extends HBox {
        //HboxCell allows us to store several things in the same row of the listView

        Label label = new Label();
        Button infoButton = new Button("Details");
        Button delButton = new Button("Delete");

        HBoxCell(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            //The infoButton is used to display some more information about the selected user
            infoButton.setOnAction(e -> {
                System.out.println("Selected item: " + label.getText());
                String username = label.getText().split(" ")[2];
                User usr = facade.seeInfos(username);
                displayPopupUserInfo(usr);
            });

            //The del button is used to delete a user from the DB
            delButton.setOnAction(e -> {
                System.out.println("Selected item: " + label.getText().split(" ")[2]);
                String username = label.getText().split(" ")[2];
                facade.deleteUser(username);
                reload();

            });

            this.getChildren().addAll(label, infoButton,delButton);
        }
    }

    public void reload(){
        //This method refreshes the listViews' content

        //Load users data from persistent layer to display it on the view
        Collection<String> regUsers = facade.getAllRegUserNames();
        Collection<String> managUsers = facade.getAllManagersNames();

        //Load data into the right format
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

        //Update listViews
        listViewU.setItems(itemsU);
        listViewM.setItems(itemsM);
    }

    @FXML
    public void initialize(){
        //Set up the view's components, called when we load the view
        reload();
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
