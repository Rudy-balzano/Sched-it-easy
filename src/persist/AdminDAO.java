package persist;

import core.Admin;

public interface AdminDAO {
    public Admin findByUsername(String username);
    public boolean insertAdmin(String username, String first, String last, String mdp);
}
