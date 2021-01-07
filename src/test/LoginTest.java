package test;
import core.SessionFacade;
import core.User;
import junit.framework.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Class used to do a JUnit Test on the Login function
 * @author emilie jean
 * @version 1.0
 * @see SessionFacade
 */
public class LoginTest extends TestCase {
    /**
     * SessionFacade
     */
    SessionFacade sf;

    /**
     * Before each test, create a new SessionFacade
     */
    @BeforeEach
    public void initSession() {
       System.out.println("Appel avant chaque test");
       sf = new SessionFacade();

    }

    /**
     * Tests if with a specific username and a specific password, we can connect
     */
    @Test
    public void testLogin() {
        //Arrange
        String username = "manager";
        String password = "mdp";
        SessionFacade sf = new SessionFacade();
        int connected = sf.login(username, password);
        assertEquals(0, connected);
    }

    /**
     * After each test, put the SessionFacade to null
     */
    @AfterEach
    public void undoSession() {
        System.out.println("Appel après chaque test");
        sf = null;
    }

    /**
     * Main to test
     * @param args
     */
    public static void main(String args[]) {

        junit.textui.TestRunner.run(new TestSuite(LoginTest.class));
    }
}



