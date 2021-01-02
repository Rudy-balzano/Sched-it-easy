package gui.controllers;

import core.Group;
import core.ManagerFacade;
import gui.Main;
import gui.roots.Roots;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ManagerAddUserTGroupController {

    Group currentGroup = ManagerManageGroupController.currentGroup;

    private static final ManagerFacade facade = new ManagerFacade();

    Collection<String> users = facade.getAllUsers();

    Collection<String> selectedUsers = new ArrayList<>();

    @FXML
    private ListView<HBoxCell> listViewUsers;

    private class HBoxCell extends HBox {
        Label label = new Label();
        CheckBox addUser = new CheckBox();

        HBoxCell(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            this.getChildren().addAll(label, addUser);

            addUser.setOnAction(actionEvent -> {
                if (addUser.isSelected()){
                    selectedUsers.add(label.getText());
                }
                else {
                    selectedUsers.remove(label.getText());
                }
            });

        }
    }



    @FXML
    public void initialize(){

        ObservableList<HBoxCell> itemsU = FXCollections.observableArrayList();
        for (String name : users){
            HBoxCell hbc = new HBoxCell(name);
            itemsU.add(hbc);
        }

        listViewUsers.setItems(itemsU);
    }


    @FXML
    public void handleSearchByLast(){

    }

    @FXML
    public void handleAddUsers() throws IOException{
        for (String i : selectedUsers){
            String username = i.split(" ")[2];
            facade.addToGroup(currentGroup.getNameGroup(), username);
        }

        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerInfoGroupRoot))));
    }

    @FXML
    public void cancel() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerInfoGroupRoot))));
    }
}
