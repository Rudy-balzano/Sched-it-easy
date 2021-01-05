package persist;

import java.util.Collection;
import java.util.HashMap;

import core.Invitation;
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

    boolean modifyUser(String username,String firstName,String lastName);


    boolean declineWaitingInvitation(String username, int id);
    boolean acceptWaitingInvitation(String username, int id);



}