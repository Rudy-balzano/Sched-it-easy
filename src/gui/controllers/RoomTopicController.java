package gui.controllers;

import core.Room;
import core.RoomTopicFacade;
import core.Equipment;
import core.Topic;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class controller for room and topic
 */
public class RoomTopicController implements AlertShower {
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
    @FXML
    private ListView<HBoxCell> listViewDeleteRooms;
    @FXML
    private ListView<HBoxCell> listViewDeleteTopics;

    /**
     * roomtopicfacade
     */
    private static final RoomTopicFacade roomTopicFacade = new RoomTopicFacade();
    /**
     * Tab View in which we display rooms that we have added
     */
    private static ObservableList<Pair<String,Integer>> listTabViewAddRoom = FXCollections.observableArrayList();
    /**
     *Tab View in which we display rooms that we have updated
     */
    private static ObservableList<Pair<String,Integer>> listTabViewUpdateRoom = FXCollections.observableArrayList();

    /**
     * Class HBoxCell to handle the ListView
     */
    private class HBoxCell extends HBox {

        Label label = new Label();
        Button infoButton = new Button("description");
        Button addButton = new Button("Add");
        Label deletedRoom = new Label();
        Label deletedTopic = new Label();
        Button deleteR = new Button("Delete");
        Button deleteT = new Button("Delete");

        /**
         * Function used to handle the ListView and buttons
         * @param labelText
         * @param tableView
         * @param listTabView
         */
        HBoxCell(String labelText, TableView tableView, ObservableList<Pair<String,Integer>> listTabView) {

            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            addButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    Pair<String, Integer> pair = new MutablePair<>(labelText, 1);

                    boolean pairAlreadyExist = false;

                    for (int i=0 ; i<listTabView.size(); i++){
                        if (listTabView.get(i).getKey().equals(pair.getKey())){
                            int val = listTabView.get(i).getValue()+1;
                            listTabView.get(i).setValue(val);
                            pairAlreadyExist = true;
                            tableView.setItems(listTabView);
                            tableView.refresh();
                        }
                    }

                    if (!pairAlreadyExist){
                        tableView.getItems().add(pair);
                    }

                    System.out.println("equipment added : "+ labelText);

                }
            });



            this.getChildren().addAll(label, infoButton, addButton);
            infoButton.setOnAction(actionEvent -> {
                String name = labelText;
               displayPopupEquipmentInfo(roomTopicFacade.displayEquipmentByName(name));
            });
        }

        HBoxCell(String nameRoom){
            super();

            deletedRoom.setText(nameRoom);
            deletedRoom.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(deletedRoom, Priority.ALWAYS);

            deletedTopic.setText(nameRoom);
            deletedTopic.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(deletedTopic, Priority.ALWAYS);

            this.getChildren().addAll(deletedTopic, deleteT);

            deleteT.setOnAction(actionEvent -> {
                roomTopicFacade.deleteTopic(deletedTopic.getText());
                refresh();
            });
        }


    }

    /**
     * Function used to refresh the listView
     */
    private void refresh(){
        Collection<String> rooms = roomTopicFacade.getRooms();
        Collection<Topic> topics = roomTopicFacade.getTopics();

        ObservableList<HBoxCell> itemsR = FXCollections.observableArrayList();
        ObservableList<HBoxCell> itemsT = FXCollections.observableArrayList();

        for (String name : rooms){
            HBoxCell hbc = new HBoxCell(name);
            itemsR.add(hbc);
        }
        for (Topic t : topics){
            HBoxCell hbc2 = new HBoxCell(t.getNameTopic());
            itemsR.add(hbc2);
        }


        listViewDeleteRooms.setItems(itemsR);
        listViewDeleteTopics.setItems(itemsT);
    }

    /**
     * Function used to display a popup with all the informations about equipment
     * @param e
     */
    private static void displayPopupEquipmentInfo(Equipment e){
        //Popup that displays information about the selected equipment
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Equipment information");

        Label name = new Label("Name : " + e.getName());
        Label description = new Label("this equipment is : " + e.getDescription());
        Label price = new Label("price : " + e.getPrice());

        Button b = new Button("Close");
        b.setOnAction(actionEvent -> popup.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(name,description,price);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout,200,150);
        popup.setScene(scene);
        popup.showAndWait();

    }

    /**
     * Function used to initalize the listView
     */
    @FXML
    private void initialize(){

        loadAllEquipmentFromDB(listViewEquipments, tableViewEquipmentAdded, listTabViewAddRoom);


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

        tableViewEquipmentAdded.setItems(listTabViewAddRoom);

        Collection<String> rooms = roomTopicFacade.getRooms();

        ObservableList<HBoxCell> itemsR = FXCollections.observableArrayList();
        for (String name : rooms){
            HBoxCell hbc2 = new HBoxCell(name);
            itemsR.add(hbc2);
        }

        listViewDeleteRooms.setItems(itemsR);

        Collection<Topic> topics = roomTopicFacade.getTopics();

        ObservableList<HBoxCell> itemsT = FXCollections.observableArrayList();
        for (Topic t : topics){
            HBoxCell hbc2 = new HBoxCell(t.getNameTopic());
            itemsT.add(hbc2);
        }

        listViewDeleteTopics.setItems(itemsT);


    }

    /**
     * Function used to lad everyEquipment
     * @param listEqu
     * @param tableView
     * @param listTabView
     */
    private void loadAllEquipmentFromDB (ListView listEqu, TableView tableView, ObservableList<Pair<String, Integer>> listTabView){

        Collection<Equipment> equipments = roomTopicFacade.getEquipments();

        ObservableList<HBoxCell> listEquipment = FXCollections.observableArrayList();

        for (Equipment equipment : equipments ){
            HBoxCell hbc = new HBoxCell(equipment.getName(), tableView, listTabView);
            listEquipment.add(hbc);
        }

        listEqu.setItems(listEquipment);
    }

    /**
     * Function used to add a room
     * @param actionEvent
     */
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

        equipments.addAll(listTabViewAddRoom);

        Boolean added = false;

        added = roomTopicFacade.addRoom(name, cap, equipments);
        if (added) {
            System.out.println("Room added !");

            this.showAlert(Alert.AlertType.CONFIRMATION,owner,"Success","Meeting successfully inserted !");
            nameAddRoom.setText("");
            capacityAddRoom.setText("");
            listTabViewAddRoom.removeAll();

        } else {
            System.out.println("impossible to add the room ");
            this.showAlert(Alert.AlertType.ERROR,owner,"Error","Impossible to add the room!");
        }
    }

    /**
     * Function used to add a topic
     * @param actionEvent
     */
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
            System.out.println("Topic added !");
            this.showAlert(Alert.AlertType.CONFIRMATION,owner,"Success","topic successfully inserted !");
            nameAddTopic.setText("");
            descriptionAddTopic.setText("");
        }
        else {
            System.out.println("impossible to add the topic ");
            this.showAlert(Alert.AlertType.ERROR,owner,"Error","impossible to add the topic !");
        }
    }

    /**
     * Function used to delete a room
     * @param actionEvent
     */
    public void deleteRoom(ActionEvent actionEvent) {
        Window owner = deleteRoomButton.getScene().getWindow();

        if (nameDeleteRoom.getText().isEmpty()) {
            System.out.println("Please enter a name of room");

        }

        String name = nameDeleteRoom.getText();
        Boolean deleted = false;
        deleted = roomTopicFacade.deleteRoom(name);

        if (deleted){
            System.out.println("Room deleted !");
            this.showAlert(Alert.AlertType.CONFIRMATION,owner,"Success","Room successfully deleted !");
            nameDeleteRoom.setText("");

        }
        else {
            System.out.println("impossible to delete the room ");
            this.showAlert(Alert.AlertType.ERROR,owner,"Error","impossible to delete the room !");
        }
    }

    /**
     * Function used to delete a topic
     * @param actionEvent
     */
    public void deleteTopic(ActionEvent actionEvent) {
        Window owner = deleteTopicButton.getScene().getWindow();

        if (nameDeleteTopic.getText().isEmpty()) {
            System.out.println("Please enter a name of topic");
        }

        String name = nameDeleteTopic.getText();
        Boolean deleted = false;
        deleted = roomTopicFacade.deleteTopic(name);
        if (deleted){
            System.out.println("Topic deleted !");
            this.showAlert(Alert.AlertType.CONFIRMATION,owner,"Success","Topic successfully deleted !");
            nameDeleteTopic.setText("");
        }
        else {
            System.out.println("impossible to delete the topic ");
            this.showAlert(Alert.AlertType.ERROR,owner,"Error","impossible to delete the topic !");
        }
    }

    /**
     * Function used to update infos about a room
     * @param actionEvent
     */
    public void updateRoom(ActionEvent actionEvent) {

        Window owner = updateRoomButton.getScene().getWindow();

        if (nameDeleteTopic.getText().isEmpty()) {
            System.out.println("Please enter a name of topic");
        }

        String name = nameUpdateRoom.getText();
        int cap = Integer.parseInt(capacityUpdateRoom.getText());

        ArrayList<Pair<String, Integer>> equipment = new ArrayList<>();

        equipment.addAll(listTabViewUpdateRoom);

        Boolean updated = false;
        updated = roomTopicFacade.updateRoom(name, cap, equipment);
        if (updated){
            System.out.println("Room updated !");
            this.showAlert(Alert.AlertType.CONFIRMATION,owner,"Success","Room successfully updated !");
            nameUpdateRoom.setText("");
            capacityUpdateRoom.setText("");
            listViewEquipments2.setItems(null);
            listTabViewUpdateRoom.removeAll();

        }
        else {
            System.out.println("impossible to update the room ");
            this.showAlert(Alert.AlertType.ERROR,owner,"Error","impossible to update the room !");
        }
    }

    /**
     * Function used to load a room by entering a name of room
     * @param actionEvent
     */
    public void loadRoom(ActionEvent actionEvent) {

        Window owner = loadButton.getScene().getWindow();

        if (nameUpdateRoom.getText().isEmpty()) {
            System.out.println("Please enter a name of room");
        }

        String name = nameUpdateRoom.getText();

        Room room = roomTopicFacade.displayRoomByName(name);

        capacityUpdateRoom.setText(""+room.getCapacity());

        loadAllEquipmentFromDB(listViewEquipments2, tableViewEquipmentAdded2, listTabViewUpdateRoom);


        listTabViewUpdateRoom.addAll(roomTopicFacade.getEquipmentsForRoom(name));

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


        tableViewEquipmentAdded2.setItems(listTabViewUpdateRoom);

    }

    /**
     * Function used to update infos about a topic
     * @param actionEvent
     */
    public void updateTopic(ActionEvent actionEvent) {
        Window owner = updateTopicButton.getScene().getWindow();

        if (nameUpdateTopic.getText().isEmpty()) {
            System.out.println("Please enter a name of topic");
        }

        if (descriptionUpdateTopic.getText().isEmpty()) {
            System.out.println("Please enter a descritpion for the topic");
        }

        String name = nameUpdateTopic.getText();
        String desc = descriptionUpdateTopic.getText();
        Boolean updated = false;
        updated = roomTopicFacade.updateTopic(name, desc);
        if (updated){
            System.out.println("topic updated !");
            this.showAlert(Alert.AlertType.CONFIRMATION,owner,"Success","Topic successfully updated !");
        }

        else {
            System.out.println("impossible to update the topic ");
            this.showAlert(Alert.AlertType.ERROR,owner,"Error","impossible to update the topic !");
        }
    }

}

// Gérer l'erreur si il rentre un faux nom de room topic pour update et delete !
//Gérer aussi si il rentre pas un int pour capacity !!