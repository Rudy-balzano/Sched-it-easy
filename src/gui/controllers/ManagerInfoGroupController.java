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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.IOException;

/**
 * Class controller for ManagerInfoGroup
 * @author emilie
 * @version 1.0
 */
public class ManagerInfoGroupController {

    /**
     * managerFacade
     */
    private static final ManagerFacade facade = new ManagerFacade();

    /**
     * current group
     */
    Group currentGroup = ManagerManageGroupController.currentGroup;


    @FXML
    private ListView<HBoxCell> listViewGroupMembers;

    /**
     * Class HBoxCell for the ListView
     */
    private class HBoxCell extends HBox {
        Label label = new Label();
        Button deleteButton = new Button("Delete from group");

        HBoxCell(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            this.getChildren().addAll(label, deleteButton);

            deleteButton.setOnAction(actionEvent -> {
                facade.deleteFromGroup(currentGroup.getNameGroup(),label.getText());
                refresh();
            });

        }
    }

    /**
     * Function used to refresh the ListView
     */
    private void refresh(){

        ObservableList<HBoxCell> itemsU = FXCollections.observableArrayList();

        Group refreshedGroup = facade.findGroupByName(currentGroup.getNameGroup());

        for (String name : refreshedGroup.getUsers()){
            HBoxCell hbc = new HBoxCell(name);
            itemsU.add(hbc);
        }

        listViewGroupMembers.setItems(itemsU);
    }

    /**
     * Function used to initialize the listView
     */
    @FXML
    public void initialize(){

        currentGroup = facade.findGroupByName(currentGroup.getNameGroup());

        ObservableList<HBoxCell> itemsU = FXCollections.observableArrayList();
        for (String name : currentGroup.getUsers()){
            HBoxCell hbc = new HBoxCell(name);
            itemsU.add(hbc);
        }

        listViewGroupMembers.setItems(itemsU);
    }

    /**
     * Function used to handle cancel button
     * @throws IOException
     */
    @FXML
    public void cancel() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerManageGroupRoot))));
    }

    /**
     * Function used to go to the add user to a group page
     * @throws IOException
     */
    @FXML
    public void switchToAddNewUser() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerAddUserToGroupRoot))));
    }
}
