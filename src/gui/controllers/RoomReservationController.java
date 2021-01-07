package gui.controllers;

import core.*;
import gui.Main;
import gui.roots.Roots;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


import java.io.IOException;
import java.util.Collection;

/**
 * Class controller for room reservation
 */
public class RoomReservationController {
    /**
     * ListView
     */
    @FXML
    private ListView<EquipmentHBoxCell> listViewEquipment;
    /**
     * ListView
     */
    @FXML
    private ListView<RoomHBoxCell> listViewRooms;

    /**
     * Meeting
     */
    Meeting meeting = CreateMeetingController.meeting;

    ObservableList<RoomHBoxCell> listViewRoom = FXCollections.observableArrayList();
    /**
     * roomTopicFacade
     */
    private static final RoomTopicFacade roomTopicFacade = new RoomTopicFacade();
    /**
     * ReservationFacade
     */
    private static final ReservationFacade reservationFacade = new ReservationFacade();


    /**
     * class EquipmentHBoxCell to handle the listView for equipment
     */
    private static class EquipmentHBoxCell extends HBox {

        Label label = new Label();
        CheckBox checkBox = new CheckBox();

        EquipmentHBoxCell(String labelText) {

            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            this.getChildren().addAll(label, checkBox);

        }

    }

    /**
     * class RoomHboxCell to handle the listView of rooms
     */
    private static class RoomHBoxCell extends HBox {

        Label label = new Label();
        Button infoButton = new Button("see information");

        RoomHBoxCell(String labelText) {

            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            this.getChildren().addAll(label, infoButton);

        }

    }

    /**
     * function used to initialize the listViews
     */
    @FXML
    private void initialize(){

        loadEquipment();
        loadAvailableRoom();
/*
        tableView.setPlaceholder(new Label("No equipments selected"));


        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Room, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNameRoom());
            }
        });


        capacityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Room, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCapacity());
            }
        });


        equipmentColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Room, String> p) {
                String res ="";
                for (int i = 0 ; i < p.getValue().getEquipment().size() ; i++){
                    res += p.getValue().getEquipment().get(i).getKey()+"  "+p.getValue().getEquipment().get(i).getKey();
                }
                return new ReadOnlyObjectWrapper(res);
            }
        });

        tableView.setItems(listTabView);
*/
    }

    /**
     * Function used to loadEquipment in the listView
     */
    private void loadEquipment(){
        Collection<Equipment> equipments = roomTopicFacade.getEquipments();

        ObservableList<EquipmentHBoxCell> listEquipment = FXCollections.observableArrayList();

        for (Equipment equipment : equipments ){
            EquipmentHBoxCell hbc = new EquipmentHBoxCell(equipment.getName());
            listEquipment.add(hbc);
        }

        listViewEquipment.setItems(listEquipment);
    }

    /**
     * Function used to load all the available rooms in the listView
     */
    private void loadAvailableRoom(){
        //TODO implementer capacity
        int capacity = 0;
        Collection<String> rooms = reservationFacade.getAvailableRooms(capacity,meeting);

        ObservableList<RoomHBoxCell> listRooms = FXCollections.observableArrayList();

        for (String room : rooms ){
            RoomHBoxCell roomHBoxCell = new RoomHBoxCell(room);
            listRooms.add(roomHBoxCell);
        }

        listViewRooms.setItems(listRooms);
    }
/*
    public void seeRoomsAvailable(ActionEvent actionEvent) {

        ArrayList<String> equipmentChecked = new ArrayList<>();
        ArrayList<Room> roomAvailable = new ArrayList<>();
        for (int i = 0 ; i < listViewEquipment.getItems().size(); i++){
            if (listViewEquipment.getItems().get(i).checkBox.isSelected()){
                equipmentChecked.add(listViewEquipment.getItems().get(i).label.getText());
            }
        }
        if (equipmentChecked.isEmpty()){
            System.out.println("aucun equipement selectionnés");
        }
        else {
            roomAvailable = reservationFacade.findRoomByEquipment(equipmentChecked);
            listViewRoom = FXCollections.observableArrayList();

            for (Room room : roomAvailable){
                RoomHBoxCell r = new RoomHBoxCell(room.getNameRoom());
                listViewRoom.add(r);
            }

            listViewRooms.setItems(listViewRoom);

        }

    }

 */

    /**
     * Function used to create a meeting
     * @param actionEvent
     */
    public void handleCreateMeeting(ActionEvent actionEvent) {
        if (listViewRooms.getSelectionModel().getSelectedItem()!=null){
            boolean res = false;
            String nameRoom = listViewRooms.getSelectionModel().getSelectedItem().label.getText();
            res = reservationFacade.createMeetingWithRoom(meeting.getId(), nameRoom);
            if (res){
                System.out.println("meeting with room added !");
            }else {
                System.out.println("not added ...");
            }
        }
    }

    /**
     * Function used to rent equipment
     * @param actionEvent
     */
    public void rentEquipments(ActionEvent actionEvent) {
    }

    /**
     * Function used to handle the cancel button
     * @throws IOException
     */
    public void handleCancel() throws IOException {
        reservationFacade.deleteMeeting(meeting.getId());

        if(SessionFacade.getConnectedUser().getIsManager()){
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerHomeRoot))));
        }
        else{
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.userHomeRoot))));

        }
    }


}
