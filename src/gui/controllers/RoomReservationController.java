package gui.controllers;

import core.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


import java.util.Collection;

public class RoomReservationController {
    @FXML
    private ListView<EquipmentHBoxCell> listViewEquipment;

    @FXML
    private ListView<RoomHBoxCell> listViewRooms;

    Meeting meeting = CreateMeetingController.meeting;

    ObservableList<RoomHBoxCell> listViewRoom = FXCollections.observableArrayList();

    private static final RoomTopicFacade roomTopicFacade = new RoomTopicFacade();
    private static final ReservationFacade reservationFacade = new ReservationFacade();



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

    private void loadEquipment(){
        Collection<Equipment> equipments = roomTopicFacade.getEquipments();

        ObservableList<EquipmentHBoxCell> listEquipment = FXCollections.observableArrayList();

        for (Equipment equipment : equipments ){
            EquipmentHBoxCell hbc = new EquipmentHBoxCell(equipment.getName());
            listEquipment.add(hbc);
        }

        listViewEquipment.setItems(listEquipment);
    }

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

    public void rentEquipments(ActionEvent actionEvent) {
    }

    public void handleCancel(ActionEvent actionEvent) {
    }


}
