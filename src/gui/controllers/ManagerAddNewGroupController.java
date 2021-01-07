package gui.controllers;

import core.ManagerFacade;
import gui.Main;
import gui.roots.Roots;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Class Controller to Add New Group for a manager
 */
public class ManagerAddNewGroupController {

    @FXML
    private TextField groupName;
    /**
     * managerFacade
     */
    private static final ManagerFacade facade = new ManagerFacade();

    /**
     * Function used to create a group
     * @throws IOException
     */
    @FXML
    public void handleCreateGroup() throws IOException{


        if(facade.addGroup(groupName.getText())){
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerManageGroupRoot))));
        }

    }

    /**
     * Function used to handle the cancel button
     * @throws IOException
     */
    @FXML
    public void cancel() throws IOException {
        Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerManageGroupRoot))));
    }
}
