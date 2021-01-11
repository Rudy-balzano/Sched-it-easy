package gui.controllers;

import core.ManagerFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.Collection;

/**
 * Class controller for account validation
 * @version 1.0
 */
public class AccountValidationController {
    /**
     * manager facade
     */
    private static final ManagerFacade facade = new ManagerFacade();
    /**
     * ListView with every users that are waiting to be accepted
     */
    @FXML
    private ListView<HBoxCell> listViewWaitingUsers;

    /**
     * class  HBoxCell to handle our ListView
     */
    private class HBoxCell extends HBox {
        /**
         * Label
         */
        Label label = new Label();
        /**
         * Button validate
         */
        Button button = new Button("Validate account");
        /**
         * Button decline
         */
        Button button2 = new Button("Decline account");

        /**
         * function to handle when clicking on buttons validate and decline
         * @param labelText
         */
        HBoxCell(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            this.getChildren().addAll(label, button);

            button.setOnAction(actionEvent -> {
                String username = label.getText().split(" ")[0];
                facade.validateAccount(username);
                refresh();
            });
            button2.setOnAction(actionEvent -> {
                String username = label.getText().split(" ")[0];
                facade.declineAccount(username);
                refresh();
            });
        }
    }

    /**
     * Function to refresh the listView
     */
    private void refresh(){
        Collection<String> waitingUsers = facade.getAllWaitingUsers();

        ObservableList<HBoxCell> itemsU = FXCollections.observableArrayList();

        for (String name : waitingUsers){
            HBoxCell hbc = new HBoxCell(name);
            itemsU.add(hbc);
        }

        listViewWaitingUsers.setItems(itemsU);
    }

    /**
     * Function used to initalize the listView
     */
    @FXML
    public void initialize(){

        Collection<String> waitingUsers = facade.getAllWaitingUsers();

        ObservableList<HBoxCell> itemsU = FXCollections.observableArrayList();
        for (String name : waitingUsers){
            HBoxCell hbc = new HBoxCell(name);
            itemsU.add(hbc);
        }

        listViewWaitingUsers.setItems(itemsU);
    }



}
