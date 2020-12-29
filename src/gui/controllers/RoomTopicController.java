package gui.controllers;

import core.RoomTopicFacade;
import core.Equipment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Window;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import java.util.ArrayList;
import java.util.Collection;


public class RoomTopicController {
    @FXML
    private TextField nameRoom;
    @FXML
    private TextField capacity;
    @FXML
    private ChoiceBox equipment;
    @FXML
    private TextField nbr;
    @FXML
    private Button addEquipmentButton;
    @FXML
    private Button addButton;
    @FXML
    private TextField nameRoom2;
    @FXML
    private TextField capacity2;
    @FXML
    private ChoiceBox equipment2;
    @FXML
    private TextField nbr2;
    @FXML
    private Button addEquipmentButton2;
    @FXML
    private Button deleteEquipmentButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField nameRoom3;
    @FXML
    private TextField nameTopic;
    @FXML
    private TextField description;
    @FXML
    private Button addButton2;
    @FXML
    private TextField nameTopic2;
    @FXML
    private TextField description2;
    @FXML
    private Button updateButton2;
    @FXML
    private TextField nameTopic3;
    @FXML
    private Button deleteButton2;

    @FXML
    private void initialize(){
        RoomTopicFacade roomTopicFacade = new RoomTopicFacade();

        Collection<Equipment> equipments;
    }
/*
    public void addRoom(ActionEvent actionEvent) {
        Window owner = addButton.getScene().getWindow();

        if (nameRoom.getText().isEmpty()) {
            System.out.println("Please enter a name of room");
        }
        if (capacity.getText().isEmpty()) {
            System.out.println("Please enter a capacity");
        }

        RoomTopicFacade roomTopic = new RoomTopicFacade();

        String name = nameRoom.getText();

        int cap = Integer.parseInt(capacity.getText());

        ArrayList<Equipment> eq = equipment.getValue();

        Boolean added = false;

        added = roomTopic.addRoom(name, cap, eq);
        if (added) {
            System.out.println("Room added !");
        } else {
            System.out.println("impossible to add the room ");
        }
    }

    public void addTopic(ActionEvent actionEvent) {
        Window owner = addButton2.getScene().getWindow();

        if (nameTopic.getText().isEmpty()) {
            System.out.println("Please enter a name of topic");
        }
        if (description.getText().isEmpty()) {
            System.out.println("Please enter a description");
        }
        RoomTopicFacade roomTopic2 = new RoomTopicFacade();
        String name = nameTopic.getText();
        String desc = description.getText();
        Boolean added = false;
        added = roomTopic2.addTopic(name,desc);
        if (added){
            System.out.println("Topic added !"); }
        else {
            System.out.println("impossible to add the topic ");
        }
    }

    public void deleteRoom(ActionEvent actionEvent) {
        Window owner = deleteButton.getScene().getWindow();

        if (nameRoom3.getText().isEmpty()) {
            System.out.println("Please enter a name of room");

        }
        RoomTopicFacade roomTopic3 = new RoomTopicFacade();
        String name = nameRoom3.getText();
        Boolean deleted = false;
        deleted = roomTopic3.deleteRoom(name);
        if (deleted){
            System.out.println("Room deleted !"); }
        else {
            System.out.println("impossible to delete the room ");
        }
    }

    public void deleteTopic(ActionEvent actionEvent) {
        Window owner = deleteButton2.getScene().getWindow();

        if (nameTopic3.getText().isEmpty()) {
            System.out.println("Please enter a name of topic");
        }

        RoomTopicFacade roomTopic4 = new RoomTopicFacade();
        String name = nameTopic3.getText();
        Boolean deleted = false;
        deleted = roomTopic4.deleteTopic(name);
        if (deleted){
            System.out.println("Topic deleted !"); }
        else {
            System.out.println("impossible to delete the topic ");
        }
    }
    public void updateRoom(ActionEvent actionEvent) {
        Window owner = updateButton.getScene().getWindow();

        if (nameRoom2.getText().isEmpty()) {
            System.out.println("Please enter a name of room");
        }
        RoomTopicFacade roomTopic5 = new RoomTopicFacade();
        String name = nameRoom2.getText();
        int cap = Integer.parseInt(capacity2.getText());
        Equipment[] eq = equipment2.getValue();
        Boolean updated = false;
        updated = roomTopic5.updateRoom(name, cap, eq);
        if (updated){
            System.out.println("Room updated !"); }
        else {
            System.out.println("impossible to update the room ");
        }
    }
    public void updateTopic(ActionEvent actionEvent) {
        Window owner = updateButton2.getScene().getWindow();

        if (nameTopic2.getText().isEmpty()) {
            System.out.println("Please enter a name of topic");
        }
        RoomTopicFacade roomTopic6 = new RoomTopicFacade();
        String name = nameTopic2.getText();
        String desc = description2.getText();
        Boolean updated = false;
        updated = roomTopic6.updateTopic(name, desc); //equipment
        if (updated){
            System.out.println("topic updated !"); }
        else {
            System.out.println("impossible to update the topic ");
        }
    }
    public ArrayList<Equipment> addEquipment(ActionEvent actionEvent){
        ArrayList<Equipment> eqList = null;
        int number = Integer.parseInt(nbr.getText());
        Equipment eq = equipment.getValue();
        //dès qu'on appuie sur le bouton on rajoute l'equipement (autant de fois que nbr ) dans eqList
        return eqList;
    }
    public ArrayList<Equipment> addEquipment2(){
        ArrayList<Equipment> eqList = null;
        int number = Integer.parseInt(nbr2.getText());
        Equipment eq = equipment2.getValue();
        //dès qu'on appuie sur le bouton on rajoute l'equipement (autant de fois que nbr2 ) dans eqList
        return eqList;
    }
    public ArrayList<Equipment> deleteEquipment(){
        ArrayList<Equipment> eqList = null;
        int number = Integer.parseInt(nbr2.getText());
        Equipment eq = equipment2.getValue();
        //dès qu'on appuie sur le bouton on supprime l'equipement (autant de fois que nbr2 ) de eqList
        return eqList;
    }
*/
}

// Gérer l'erreur si il rentre un faux nom de room topic pour update et delete !
//Gérer aussi si il rentre pas un int pour capacity !!