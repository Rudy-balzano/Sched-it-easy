package persist;

import java.util.Map;

import core.User;

public interface UserDAO {
    Map<String,Boolean> findAllNames();

    User findByUsername(String username);

    boolean insertWaitingUser(String username, String first, String last, String mdp);

    boolean makeManager(String username);

    boolean deleteUser(String username);
}