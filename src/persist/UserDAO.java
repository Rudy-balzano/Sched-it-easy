package persist;

import java.util.ArrayList;
import java.util.HashMap;
import core.User;

public interface UserDAO {

    public User findByUsername(String username);
}