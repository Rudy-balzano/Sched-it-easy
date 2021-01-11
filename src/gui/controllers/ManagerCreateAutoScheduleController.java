package gui.controllers;

import core.*;
import gui.Main;
import gui.roots.Roots;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import org.apache.commons.lang3.tuple.Pair;
import persist.FactoryDAOImpl;
import persist.TopicDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 * Class controler to manage the creation of an autoSchedule
 * @version 1.0
 */
public class ManagerCreateAutoScheduleController {
    /**
     * reservationFacade
     */
    ReservationFacade facade = new ReservationFacade();
    /**
     * RoomTopicFacade
     */
    RoomTopicFacade topicFacade = new RoomTopicFacade();

    @FXML
    DatePicker dateBegin;
    @FXML
    DatePicker dateEnd;

    @FXML
    ListView listViewAddedTopics;
    @FXML
    ListView listViewTopics;

    /**
     * HashMap of every topics
     */
    public static HashMap<String,Integer> matieres = new HashMap<>();

    private class HBoxCell extends HBox {
        Label label = new Label();
        TextField hours = new TextField();
        Button button = new Button("Add");

        HBoxCell(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            this.getChildren().addAll(label, hours, button);

            button.setOnAction(actionEvent -> {
                addToTopics(label.getText(),hours.getText());
                refresh();
            });
        }
    }


    private class HBoxCell1 extends HBox {
        Label label = new Label();

        HBoxCell1(String labelText) {
            super();

            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            this.getChildren().addAll(label);

        }
    }

    /**
     * Function used to refresh
     */
    private void refresh(){

        ObservableList<HBoxCell1> listAddedTopics = FXCollections.observableArrayList();
        for (String k : matieres.keySet()){
            String str = k + " : " + matieres.get(k)+"h";
            HBoxCell1 hbc = new HBoxCell1(str);
            listAddedTopics.add(hbc);
        }

        listViewAddedTopics.setItems(listAddedTopics);

    }

    /**
     * Function used to add an hour to a topic
     * @param topic
     * @param hours
     */
    private void addToTopics(String topic, String hours){
        int hour = Integer.parseInt(hours);
        matieres.put(topic,hour);
    }

    /**
     * Funciton used to initialize
     * @throws Exception
     */
    public void initialize() throws Exception {

        Collection<Topic> topics = topicFacade.getTopics();

        ObservableList<HBoxCell> listTopics = FXCollections.observableArrayList();

        for (Topic t : topics) {
            HBoxCell hbc = new HBoxCell(t.getNameTopic());
            listTopics.add(hbc);
        }



        listViewTopics.setItems(listTopics);

        listViewAddedTopics.setPlaceholder(new Label("No topics selected"));

    }

    public static Collection<Meeting> autosched = new ArrayList<>();

    /**
     * Function used to handleAutoSchedule
     * @throws Exception
     */
    @FXML
    public void handleAutoSchedule()  throws  Exception{

        LocalDate dB = dateBegin.getValue();
        LocalDate dE = dateEnd.getValue();

        if (dB != null && dE != null) {
            autosched = facade.autoSchedule(matieres, dB, dE);
            Main.scheditWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource(Roots.managerAutoScheduleRoot))));
        }
    }
}


