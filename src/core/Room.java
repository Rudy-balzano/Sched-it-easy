package core;

public class Room {
    private String nameRoom;
    private int capacity;
    private Equipment[] equipment;

    public Room( String nameRoom, int capacity, Equipment[] equipment) {

        this.nameRoom = nameRoom;
        this.capacity = capacity;
        this.equipment = equipment;
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

    public Equipment[] getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment[] equipment) {
        this.equipment = equipment;
    }
}

