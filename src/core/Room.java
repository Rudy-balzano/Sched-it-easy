package core;

import javafx.util.Pair;

import java.util.ArrayList;

public class Room {
    private String nameRoom;
    private int capacity;
    private ArrayList<Pair<String,Integer>> equipments;

    public Room( String nameRoom, int capacity, ArrayList<Pair<String,Integer>> equipments) {

        this.nameRoom = nameRoom;
        this.capacity = capacity;
        this.equipments = equipments;
    }

    public Room() {
    };

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity =  capacity;
    }

    public ArrayList<Pair<String,Integer>> getEquipment() {
        return equipments;
    }

    public void setEquipment(ArrayList<Pair<String,Integer>> equipments) {
        this.equipments = equipments;
    }
}

