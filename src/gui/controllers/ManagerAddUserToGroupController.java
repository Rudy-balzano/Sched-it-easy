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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class controller to manage the add of an user to a group
 * @version 1.0
 */
public class ManagerAddUserToGroupController {
    /**
     * currentGroup
     */
    Group currentGroup = ManagerManageGroupController.currentGroup;
    /**
     * managerFacade
     */
    private static final ManagerFacade facade = new ManagerFacade();
    /**
     * Every Users
     */
    Collection<String> users = facade.getAllUsers();
    /**
     * Selected Users
     */
    Collection<String> selectedUsers = new ArrayList<>();


    @FXML
    private ListView<HBoxCell> listViewUsers;

    @FXML
    private TextField searchButton;

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


    /**
     * Function used to initialize the ListView
     */
    @FXML
    public void initialize(){

        ObservableList<HBoxCell> itemsU = FXCollections.observableArrayList();
        for (String name : users){
            HBoxCell hbc = new HBoxCell(name);
            itemsU.add(hbc);
        }

        listViewUsers.setItems(itemsU);

        searchButton.setOnKeyPressed(keyEvent -> handleSearch());
    }

    /**
     * Function used to search an user
     */
    @FXML
    public void handleSearch(){
        ArrayList<String> searched = new ArrayList<>();
        Pattern pattern = Pattern.compile(searchButton.getText());

        for (String i : users){
            Matcher matcher = pattern.matcher(i);
            if(matcher.find()){
                searched.add(i);
            }
        }

        ObservableList<HBoxCell> itemsU = FXCollections.observableArrayList();


        for (String name : searched){
            HBoxCell hbc = new HBoxCell(name);
            itemsU.add(hbc);
        }

        listViewUsers.setItems(itemsU);

    }


    /**
     * Function used to add an user to a group
     * @throws IOException
     */
    @FXML
    public void handleAddUsers() throws IOException{
        for (String i : selectedUsers){
            String username = i.split(" ")[2];
            facade.addToGroup(currentGroup.getNameGroup(), username);
        }

        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerInfoGroupRoot))));
    }

    /**
     * Function used to cancel
     * @throws IOException
     */
    @FXML
    public void cancel() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerInfoGroupRoot))));
    }
}
