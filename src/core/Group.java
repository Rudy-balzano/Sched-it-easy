package core;

import java.util.ArrayList;
import java.util.Collection;

public class Group {
    private String nameGroup;
    private Collection<String> users;

    public Group(String nameGroup, Collection<String> users){
        this.nameGroup = nameGroup;
        this.users = users;
    }

    public Group(){
        this.nameGroup = null;
        this.users = new ArrayList<>();
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public Collection<String> getUsers() {
        return users;
    }

    public void setUsers(Collection<String> users) {
        this.users = users;
    }
}
