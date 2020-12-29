package core;

public class RoomEquipment extends Equipment{

    private int quantity;

    public RoomEquipment(String name, String description, int quantity) {
        super(name, description);
        this.quantity = quantity;
    }

    public RoomEquipment(){
        super(null, null);
    }

}
