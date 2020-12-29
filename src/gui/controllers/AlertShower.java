package gui.controllers;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public interface AlertShower {
    //Interface that allow controllers to display alerts in views
    default public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
