package core;

/**
 * Class that represent an equipment
 * @version 1.0
 */
public class Equipment {
    /**
     * Name of the equipment
     * @see Equipment#setName(String)
     * @see Equipment#getName()
     */
    private String name;
    /**
     * Description of the equipment
     * @see Equipment#getDescription()
     * @see Equipment#setDescription(String)
     */
    private String description;
    /**
     * Price of the equipment
     * @see Equipment#setPrice(int)
     * @see Equipment#getPrice()
     */
    private int price;

    /**
     * Constructor of Equipment
     * @param name
     * @param description
     * @param price
     *
     * @see Equipment#name
     * @see Equipment#description
     * @see Equipment#price
     */
    public Equipment(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Null constructor of Equipment
     */
    public Equipment() {}

    /**
     * Return the name of the Equipment
     * @return the name of the Equipment
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the Equipment
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the description of the Equipment
     * @return the description of the Equipment
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the Equipment
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return the price of the Equipment
     * @return the price of the Equipment
     */
    public int getPrice() { return price; }

    /**
     * Set the price of the Equipment
     * @param price
     */
    public void setPrice(int price) { this.price = price; }
}
