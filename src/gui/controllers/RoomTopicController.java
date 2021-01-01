package gui.controllers;

import core.Room;
import core.RoomTopicFacade;
import core.Equipment;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Window;
import javafx.util.Callback;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;

public class RoomTopicController {
    @FXML
    private TextField nameAddRoom;
    @FXML
    private TextField capacityAddRoom;
    @FXML
    private Button addRoomButton;
    @FXML
    private TextField nameUpdateRoom;
    @FXML
    private TextField capacityUpdateRoom;
    @FXML
    private Button loadButton;
    @FXML
    private Button updateRoomButton;
    @FXML
    private Button deleteRoomButton;
    @FXML
    private TextField nameDeleteRoom;
    @FXML
    private TextField nameAddTopic;
    @FXML
    private TextField descriptionAddTopic;
    @FXML
    private Button addTopicButton;
    @FXML
    private TextField nameUpdateTopic;
    @FXML
    private TextField descriptionUpdateTopic;
    @FXML
    private Button updateTopicButton;
    @FXML
    private TextField nameDeleteTopic;
    @FXML
    private Button deleteTopicButton;
    @FXML
    private ListView<HBoxCell> listViewEquipments;
    @FXML
    private TableView<Pair<String, Integer>> tableViewEquipmentAdded;
    @FXML
    private TableColumn<Pair<String, Integer>,String> equipmentColumn;
    @FXML
    private TableColumn<Pair<String, Integer>,Integer> quantityColumn;
    @FXML
    private ListView<HBoxCell> listViewEquipments2;
    @FXML
    private TableView<Pair<String, Integer>> tableViewEquipmentAdded2;
    @FXML
    private TableColumn<Pair<String, Integer>,String> equipmentColumn2;
    @FXML
    private TableColumn<Pair<String, Integer>,Integer> quantityColumn2;


    RoomTopicFacade roomTopicFacade = new RoomTopicFacade();

    ObservableList<Pair<String,Integer>> listTabView = FXCollections.observableArrayList();



    private class HBoxCell extends HBox {

        Label label = new Label();
        Button infoButton = new Button("description");
        Button addButton = new Button("Add");

        HBoxCell(String labelText, TableView tableView) {

            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            listTabView = tableView.getItems();

            addButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    // TODO : verifier si l'equipement est deja dans la tableView est incrementer la quantité si c'est le cas

                    Pair<String, Integer> pair = new MutablePair<>(labelText, 1);

                    boolean pairAlreadyExist = false;

                    for (int i=0 ; i<listTabView.size(); i++){
                        if (listTabView.get(i).getKey().equals(pair.getKey())){
                            int val = listTabView.get(i).getValue()+1;
                            listTabView.get(i).setValue(val);
                            pairAlreadyExist = true;
//                            tableViewEquipmentAdded.setItems(listTabView);
                        }
                    }

                    if (!pairAlreadyExist){
                        tableViewEquipmentAdded.getItems().add(pair);
                    }

                    System.out.println("equipment added : "+ labelText);

                }
            });

            this.getChildren().addAll(label, infoButton, addButton);

        }


    }


    @FXML
    private void initialize(){

        loadAllEquipmentFromDB(listViewEquipments, tableViewEquipmentAdded);

        tableViewEquipmentAdded.setPlaceholder(new Label("No equipments selected"));

//        equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("K"));

        equipmentColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<String, Integer>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair<String, Integer>, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getKey());
            }
        });

//        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("V"));

        quantityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<String, Integer>, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Pair<String, Integer>, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getValue());
            }
        });
        /*
        quantityColumn.setOnEditStart(
                new EventHandler<TableColumn.CellEditEvent<Pair<String, Integer>, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Pair<String, Integer>, Integer> pairIntegerCellEditEvent) {
                        (pairIntegerCellEditEvent.getTableView().getItems().get(pairIntegerCellEditEvent.getTablePosition().getRow())).setValue(pairIntegerCellEditEvent.getNewValue());
                    }
                }
        );

         */

        tableViewEquipmentAdded.setItems(listTabView);


    }

    private void loadAllEquipmentFromDB (ListView listEqu, TableView tableView){

        Collection<Equipment> equipments = roomTopicFacade.getEquipments();

        ObservableList<HBoxCell> listEquipment = FXCollections.observableArrayList();

        for (Equipment equipment : equipments ){
            HBoxCell hbc = new HBoxCell(equipment.getName(), tableView);
            listEquipment.add(hbc);
        }

        listEqu.setItems(listEquipment);
    }

    public void addRoom(ActionEvent actionEvent) {
        Window owner = addRoomButton.getScene().getWindow();

        if (nameAddRoom.getText().isEmpty()) {
            System.out.println("Please enter a name of room");
        }
        if (capacityAddRoom.getText().isEmpty()) {
            System.out.println("Please enter a capacity");
        }


        String name = nameAddRoom.getText();
        int cap = Integer.parseInt(capacityAddRoom.getText());
        ArrayList<Pair<String, Integer>> equipments = new ArrayList<>();

        equipments.addAll(listTabView);

        Boolean added = false;

        added = roomTopicFacade.addRoom(name, cap, equipments);
        if (added) {
            System.out.println("Room added !");
        } else {
            System.out.println("impossible to add the room ");
        }
    }

    public void addTopic(ActionEvent actionEvent) {
        Window owner = addTopicButton.getScene().getWindow();

        if (nameAddTopic.getText().isEmpty()) {
            System.out.println("Please enter a name of topic");
        }
        if (descriptionAddTopic.getText().isEmpty()) {
            System.out.println("Please enter a description");
        }

        String name = nameAddTopic.getText();
        String desc = descriptionAddTopic.getText();
        Boolean added = false;
        added = roomTopicFacade.addTopic(name,desc);
        if (added){
            System.out.println("Topic added !"); }
        else {
            System.out.println("impossible to add the topic ");
        }
    }

    public void deleteRoom(ActionEvent actionEvent) {
        Window owner = deleteRoomButton.getScene().getWindow();

        if (nameDeleteRoom.getText().isEmpty()) {
            System.out.println("Please enter a name of room");

        }
        RoomTopicFacade roomTopic3 = new RoomTopicFacade();
        String name = nameDeleteRoom.getText();
        Boolean deleted = false;
        deleted = roomTopic3.deleteRoom(name);
        if (deleted){
            System.out.println("Room deleted !"); }
        else {
            System.out.println("impossible to delete the room ");
        }
    }

    public void deleteTopic(ActionEvent actionEvent) {
        Window owner = deleteTopicButton.getScene().getWindow();

        if (nameDeleteTopic.getText().isEmpty()) {
            System.out.println("Please enter a name of topic");
        }

        RoomTopicFacade roomTopic4 = new RoomTopicFacade();
        String name = nameDeleteTopic.getText();
        Boolean deleted = false;
        deleted = roomTopic4.deleteTopic(name);
        if (deleted){
            System.out.println("Topic deleted !"); }
        else {
            System.out.println("impossible to delete the topic ");
        }
    }
    public void updateRoom(ActionEvent actionEvent) {

        Window owner = updateRoomButton.getScene().getWindow();

        if (nameDeleteTopic.getText().isEmpty()) {
            System.out.println("Please enter a name of topic");
        }

        String name = nameUpdateRoom.getText();
        int cap = Integer.parseInt(capacityUpdateRoom.getText());
        //       Equipment[] eq = equipment2.getValue();
        Boolean updated = false;
  //      updated = roomTopic5.updateRoom(name, cap, eq);
        if (updated){
            System.out.println("Room updated !"); }
        else {
            System.out.println("impossible to update the room ");
        }
    }

    public void loadRoom(ActionEvent actionEvent) {

        Window owner = loadButton.getScene().getWindow();

        if (nameUpdateRoom.getText().isEmpty()) {
            System.out.println("Please enter a name of room");
        }

        String name = nameUpdateRoom.getText();

        Room room = roomTopicFacade.displayRoomByName(name);

        capacityUpdateRoom.setText(""+room.getCapacity());

        loadAllEquipmentFromDB(listViewEquipments2, tableViewEquipmentAdded2);

        ObservableList<Pair<String,Integer>> listTabViewLoaded = FXCollections.observableArrayList();

        listTabViewLoaded.addAll(roomTopicFacade.getEquipmentsForRoom(name));

        equipmentColumn2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<String, Integer>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair<String, Integer>, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getKey());
            }
        });


        quantityColumn2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<String, Integer>, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Pair<String, Integer>, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getValue());
            }
        });

//        listTabView = tableViewEquipmentAdded2.getItems();

        tableViewEquipmentAdded2.setItems(listTabViewLoaded);

    }

    public void updateTopic(ActionEvent actionEvent) {
        Window owner = updateTopicButton.getScene().getWindow();

        if (nameUpdateTopic.getText().isEmpty()) {
            System.out.println("Please enter a name of topic");
        }
        RoomTopicFacade roomTopic6 = new RoomTopicFacade();
        String name = nameUpdateTopic.getText();
        String desc = descriptionUpdateTopic.getText();
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
//        int number = Integer.parseInt(nbr.getText());
//        Equipment eq = equipment.getValue();
        //dès qu'on appuie sur le bouton on rajoute l'equipement (autant de fois que nbr ) dans eqList
        return eqList;
    }
    public ArrayList<Equipment> addEquipment2(){
        ArrayList<Equipment> eqList = null;
//        int number = Integer.parseInt(nbr2.getText());
//        Equipment eq = equipment2.getValue();
        //dès qu'on appuie sur le bouton on rajoute l'equipement (autant de fois que nbr2 ) dans eqList
        return eqList;
    }
    public ArrayList<Equipment> deleteEquipment(){
        ArrayList<Equipment> eqList = null;
        return eqList;
    }

}

// Gérer l'erreur si il rentre un faux nom de room topic pour update et delete !
//Gérer aussi si il rentre pas un int pour capacity !!