package persist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import core.User;

public interface UserDAO {
    public Map<String,Boolean> findAllNames();
    public User findByUsername(String username);
    public boolean insertWaitingUser(String username, String first, String last, String mdp);
    public boolean makeManager(String username);
    public boolean deleteUser(String username);
}