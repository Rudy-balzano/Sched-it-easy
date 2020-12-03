package persist;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserDAO {

    public HashMap<String,String> findByUsername(String username);
}