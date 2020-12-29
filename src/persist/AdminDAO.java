package persist;

import core.Admin;

public interface AdminDAO {
    Admin findByUsername(String username);

    boolean insertAdmin(String username, String first, String last, String mdp);
}
