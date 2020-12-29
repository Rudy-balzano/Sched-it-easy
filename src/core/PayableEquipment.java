package core;

public class PayableEquipment extends Equipment{

    private int prix;

    public PayableEquipment(String name, String description, int prix) {
        super(name, description);
        this.prix = prix;
    }

    public  PayableEquipment(){
        super(null, null);
    }
}
