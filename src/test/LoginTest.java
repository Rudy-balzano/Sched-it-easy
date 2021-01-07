package test;
import core.SessionFacade;
import core.User;
import junit.framework.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


public class LoginTest extends TestCase {

    SessionFacade sf;

    @BeforeEach
    public void initSession() {
       System.out.println("Appel avant chaque test");
       sf = new SessionFacade();

    }

    @Test
    public void testLogin() {
        //Arrange
        String username = "manager";
        String password = "mdp";
        SessionFacade sf = new SessionFacade();
        int connected = sf.login(username, password);
        assertEquals(0, connected);
    }

    @AfterEach
    public void undoSession() {
        System.out.println("Appel après chaque test");
        sf = null;
    }

    public static void main(String args[]) {
        junit.textui.TestRunner.run(new TestSuite(LoginTest.class));
    }
}



