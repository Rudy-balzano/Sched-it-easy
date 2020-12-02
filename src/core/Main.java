package core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Main App
        primaryStage.setTitle("Sched'it easy");
        primaryStage.centerOnScreen();

        //Login
        Parent loginRoot = FXMLLoader.load(getClass().getResource("../gui/views/loginView.fxml"));
//        Scene loginView = new LoginView(loginRoot, 300, 275);

        primaryStage.setScene(new Scene(loginRoot));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
