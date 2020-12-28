package core;

public class Equipment {

    private String nameEquipment;
    private String description;


    public Equipment(String nameEquipment, String description) {
        this.nameEquipment = nameEquipment;
        this.description = description;
    }
    public String getNameEquipment() {
        return nameEquipment;
    }

    public void setNameEquipment(String nameEquipment) {
        this.nameEquipment = nameEquipment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
