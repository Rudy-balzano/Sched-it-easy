package core;

/**
 * Classe représentant un Admin pour notre application.
 * @author emilie jean
 * @version 1.0
 */
public class Admin {

    /**
     * le username de l'admin. Il permet de l'identifier.
     * @see Admin#getUsername()
     * @see Admin#setUsername(String)
     */
    private String username;
    /**
     * le mot de passe de l'admin.
     * @see Admin#getPassword()
     * @see Admin#setPassword(String)
     */
    private String password;
    /**
     * le Prénom de l'admin.
     * @see Admin#getFirstName()
     * @see Admin#setFirstName(String)
     */
    private String firstName;
    /**
     * le nom de famille de l'admin.
     * @see Admin#getLastName()
     * @see Admin#setLastName(String)
     */
    private String lastName;

    /**
     * Constructeur Admin
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     *
     * @see Admin#username
     * @see Admin#password
     * @see Admin#firstName
     * @see Admin#lastName
     */
    public Admin (String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Constructeur Admin nul.
     */
    public Admin () {
        this.firstName = null;
        this.lastName = null;
        this.username = null;
        this.password = null;
    }

    /**
     * Retourne le username de l'admin
     * @return le username de l'admin
     */
    public String getUsername() { return username; }

    /**
     * Met à jour l'username de l'admin
     * @param username
     *
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retourne un mot de passe
     * @return le mot de passe de l'admin
     */
    public String getPassword() {
        return password;
    }

    /**
     *Met à jour le mot de passe de l'admin
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retourne le prénom de l'admin
     * @return le prénom de l'admin
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Met à jour le prénom de l'admin
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retourne le nom de famille de l'admin
     * @return le nom de famille de l'admin
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Met à jour le nom de famille de l'admin
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
