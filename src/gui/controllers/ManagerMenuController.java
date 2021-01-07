package gui.controllers;

import core.SessionFacade;
import gui.Main;
import gui.roots.Roots;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Class controller for the manager header
 * @author pierre rudy baptiste emilie
 * @version 1.0
 */
public class ManagerMenuController {
    /**
     * Function used to go to the account validation page
     * @throws IOException
     */
    public void switchToAccountValidation() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerAccountValidationRoot))));
    }

    /**
     * Function used to go to the group page
     * @throws IOException
     */
    public void switchToManageGroups() throws IOException{
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerManageGroupRoot))));
    }

    /**
     * Function used to go to the meeting validation page
     * @throws IOException
     */
    public void switchToMeetingValidation() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerMeetingValidationRoot))));
    }

    /**
     * Function used to go to the see account page
     * @throws IOException
     */
    public void switchToSeeAccount() throws IOException{
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerProfileRoot))));
    }

    /**
     * Function used to go to the create meeting page
     * @throws IOException
     */
    public void handleReservation() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.createMeetingRoot))));
    }

    /**
     * Function used to logout the manager
     * @throws IOException
     */
    public void logout() throws IOException {
        SessionFacade.logout();
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.loginRoot))));
    }

    /**
     * Function used to go to the notification page
     * @throws IOException
     */
    public void switchToInvitation() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerInvitationRoot))));
    }

    /**
     * Function used to go to the create auto schedule page
     * @throws IOException
     */
    public void switchToCreateAutoSchedule() throws IOException{
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerCreateAutoScheduleRoot))));
    }
}
