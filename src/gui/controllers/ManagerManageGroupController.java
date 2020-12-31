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
import java.util.Collection;

public class ManagerManageGroupController {

    private static final ManagerFacade facade = new ManagerFacade();

    private final Collection<String> groups = facade.getAllGroups();

    public static Group currentGroup;

    @FXML
    private ListView<HBoxCell> listViewGroups;


    private class HBoxCell extends HBox {
        Label label = new Label();
        Button infoButton = new Button("Info");
        Button deleteButton = new Button("Delete group");

        HBoxCell(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            this.getChildren().addAll(label, infoButton, deleteButton);

            deleteButton.setOnAction(actionEvent -> {
                facade.deleteGroup(label.getText());
                refresh();
            });

            infoButton.setOnAction(actionEvent -> {
                currentGroup = facade.findGroupByName(label.getText());
                try {
                    Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerInfoGroupRoot))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void refresh(){

        Collection<String> groupes = facade.getAllGroups();

        ObservableList<HBoxCell> itemsG = FXCollections.observableArrayList();

        for (String name : groupes){
            HBoxCell hbc = new HBoxCell(name);
            itemsG.add(hbc);
        }

        listViewGroups.setItems(itemsG);
    }


    @FXML
    public void initialize(){

        ObservableList<HBoxCell> itemsG = FXCollections.observableArrayList();
        for (String name : groups){
            HBoxCell hbc = new HBoxCell(name);
            itemsG.add(hbc);
        }

        listViewGroups.setItems(itemsG);
    }

    @FXML
    public void switchToAddGroup() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerAddGroupRoot))));
    }
}
