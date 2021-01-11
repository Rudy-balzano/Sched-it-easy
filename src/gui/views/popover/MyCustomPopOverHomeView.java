package gui.views.popover;

import com.calendarfx.model.Entry;
import com.calendarfx.view.Messages;
import com.calendarfx.view.TimeField;
import com.calendarfx.view.popover.*;
import core.*;
import gui.Main;
import gui.roots.Roots;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;


public class MyCustomPopOverHomeView extends EntryPopOverPane {

    private static final ReservationFacade reservationFacade = new ReservationFacade();


    private Label topic = new Label("topic");
    private Label description = new Label("description");
    private Button button = new Button("Invite People");

    private Entry entry;
    public static int idMeeting;

    private final InvitationFacade facade = new InvitationFacade();

    public MyCustomPopOverHomeView(Entry entry){

        this.entry = entry;
        idMeeting = Integer.parseInt(entry.getId());

        String userCreator = reservationFacade.findMeetingById(idMeeting).getClientMeeting();

        button.setOnAction(actionEvent -> {
            facade.setMeetingId(Integer.parseInt(entry.getId()));
            try {
                Main.scheditWindow.setScene(new Scene(load(getClass().getResource(Roots.seeInvitedPeopleRoot))));
            } catch (IOException ex){
                ex.printStackTrace();
            }

        });

        PopOverTitledPane popOverTitledPane = new PopOverTitledPane("detail", this);
        GridPane gridPane1 = new GridPane();
        VBox vBox = new VBox();

        TextField titleField = new TextField(entry.getTitle());
        TextField descriptionField = new TextField(entry.getLocation());
        titleField.setEditable(false);
        descriptionField.setEditable(false);

        gridPane1.add(topic,0,0);
        gridPane1.add(titleField, 1, 0);
        gridPane1.add(description,0,1);
        gridPane1.add(descriptionField, 1, 1);

        getStyleClass().add("popover-header");

        Label startDateLabel = new Label(Messages.getString("EntryDetailsView.FROM"));
        Label endDateLabel = new Label(Messages.getString("EntryDetailsView.TO"));

        TimeField startTimeField = new TimeField();
        startTimeField.setValue(entry.getStartTime());
        startTimeField.disableProperty().bind(entry.getCalendar().readOnlyProperty());

        TimeField endTimeField = new TimeField();
        endTimeField.setValue(entry.getEndTime());
        endTimeField.disableProperty().bind(entry.getCalendar().readOnlyProperty());

        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setValue(entry.getStartDate());
        startDatePicker.disableProperty().bind(entry.getCalendar().readOnlyProperty());

        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setValue(entry.getEndDate());
        endDatePicker.disableProperty().bind(entry.getCalendar().readOnlyProperty());

        HBox startDateBox = new HBox(10);
        HBox endDateBox = new HBox(10);

        startDateBox.setAlignment(Pos.CENTER_LEFT);
        endDateBox.setAlignment(Pos.CENTER_LEFT);

        startDateBox.getChildren().addAll(startDateLabel, startDatePicker, startTimeField);
        endDateBox.getChildren().addAll(endDateLabel, endDatePicker, endTimeField);

        GridPane box = new GridPane();
        box.getStyleClass().add("content");
        box.add(startDateLabel, 0, 0);
        box.add(startDateBox, 1, 0);
        box.add(endDateLabel, 0, 1);
        box.add(endDateBox, 1, 1);

        popOverTitledPane.setContent(box);

        vBox.getChildren().add(gridPane1);
        vBox.getChildren().add(popOverTitledPane);

        if (SessionFacade.getConnectedUser().getUserName().equals(userCreator)){
            vBox.getChildren().addAll(button);
        }
        getChildren().add(vBox);

    }
}
