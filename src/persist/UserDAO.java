package persist;

import java.util.Collection;
import core.Meeting;
import core.User;

public interface UserDAO {
    Collection<String> findAllRegUsersNames();

    Collection<String> findAllManagersNames();

    Collection<String> findAllWaitingUsers();

    User findByUsername(String username);

    boolean insertWaitingUser(String username, String first, String last, String mdp);

    boolean makeManager(String username);

    boolean deleteUser(String username);

    boolean validateAccount(String username);

    boolean declineWaitingUser(String username);

    Collection<Meeting> findSchedule(String username);

}