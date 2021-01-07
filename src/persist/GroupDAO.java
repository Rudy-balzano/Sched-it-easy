package persist;

import core.Group;
import core.User;

import java.util.ArrayList;
import java.util.Collection;

public interface GroupDAO {

    ArrayList<String> findAll();

    Group findByName(String name);

    boolean insert(String name);

    boolean delete(String name);

    boolean addMember(String username, String groupName);

    boolean deleteMember(String username, String groupName);


}
