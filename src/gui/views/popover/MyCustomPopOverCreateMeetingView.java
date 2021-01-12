package gui.views.popover;

import com.calendarfx.model.Entry;
import com.calendarfx.view.Messages;
import com.calendarfx.view.TimeField;
import com.calendarfx.view.popover.EntryPopOverPane;
import com.calendarfx.view.popover.PopOverTitledPane;
import core.RoomTopicFacade;
import core.Topic;
import gui.Main;
import gui.controllers.CreateMeetingController;
import gui.roots.Roots;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collection;

public class MyCustomPopOverCreateMeetingView extends EntryPopOverPane {

    private Label topic = new Label("topic");

    ObservableList<Topic> listTopics = FXCollections.observableArrayList();

    private RoomTopicFacade roomTopicFacade = new RoomTopicFacade();

    private Entry entry;



    public MyCustomPopOverCreateMeetingView(Entry entry){


        this.entry = entry;
        loadTopic(listTopics);
        ChoiceBox choiceBox = new ChoiceBox(listTopics);


        PopOverTitledPane popOverTitledPane = new PopOverTitledPane("detail", this);
        GridPane gridPane1 = new GridPane();
        VBox vBox = new VBox();

        choiceBox.setOnAction(actionEvent -> {
            entry.setTitle((String)choiceBox.getSelectionModel().getSelectedItem());
        });


        gridPane1.add(topic,0,0);
        gridPane1.add(choiceBox, 1, 0);

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


        entry.intervalProperty().addListener(it -> {
            startTimeField.setValue(entry.getStartTime());
            endTimeField.setValue(entry.getEndTime());
            startDatePicker.setValue(entry.getStartDate());
            endDatePicker.setValue(entry.getEndDate());
        });



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
        vBox.setAlignment(Pos.CENTER);
        getChildren().add(vBox);

    }

    private void loadTopic (ObservableList listTopics){

        Collection<Topic> topics = roomTopicFacade.getTopics();
        Collection<String> l = new ArrayList<>();

        for (Topic t : topics){
            l.add(t.getNameTopic());
        }
        listTopics.addAll(l);

    }
}
