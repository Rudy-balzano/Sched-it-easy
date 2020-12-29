package persist;

import java.util.Collection;
import java.util.Map;

import core.User;

public interface UserDAO {
    Collection<String> findAllRegUsersNames();

    Collection<String> findAllManagersNames();

    User findByUsername(String username);

    boolean insertWaitingUser(String username, String first, String last, String mdp);

    boolean makeManager(String username);

    boolean deleteUser(String username);
}