package persist;

import java.util.ArrayList;

public interface UserDAO {

    public ArrayList<String> findByUsername(String username);
}