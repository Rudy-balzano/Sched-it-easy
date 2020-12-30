package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage scheditWindow;

    @Override
    public void start(Stage primaryStage) throws Exception {

        scheditWindow = primaryStage;

        // Main App
        scheditWindow.setTitle("Sched'it easy");
        scheditWindow.centerOnScreen();

        //Login
        Parent loginRoot = FXMLLoader.load(getClass().getResource("views/LoginView.fxml"));
        Scene loginView = new Scene(loginRoot);

        primaryStage.setScene(loginView);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
