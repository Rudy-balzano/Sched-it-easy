package core;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class that represent a group of user.
 * @author emilie jean
 * @version 1.0
 */
public class Group {
    /**
     * Name of the group
     * @see Group#setNameGroup(String)
     * @see Group#getNameGroup()
     */
    private String nameGroup;
    /**
     * All the users that are in this group
     * @see Group#setUsers(Collection)
     * @see Group#getUsers()
     */
    private Collection<String> users;

    /**
     * Constructor of group
     * @param nameGroup
     * @param users
     * @see Group#nameGroup
     * @see Group#users
     */
    public Group(String nameGroup, Collection<String> users){
        this.nameGroup = nameGroup;
        this.users = users;
    }

    /**
     * Constructor of group where everything is initialized to null
     */
    public Group(){
        this.nameGroup = null;
        this.users = new ArrayList<>();
    }

    /**
     * Return the name of the group
     * @return the name of the group
     */
    public String getNameGroup() {
        return nameGroup;
    }

    /**
     * Set the name of the group
     * @param nameGroup
     */
    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    /**
     * Return all the users belonging to this group
     * @return all the users belonging to this group
     */
    public Collection<String> getUsers() {
        return users;
    }

    /**
     * set all the users belonging to this group
     * @param users
     */
    public void setUsers(Collection<String> users) {
        this.users = users;
    }
}
