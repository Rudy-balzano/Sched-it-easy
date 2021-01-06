package gui.views.popover;

import com.calendarfx.view.popover.EntryPopOverPane;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.awt.*;

public class MyCustomPopOverContentNode extends EntryPopOverPane {

    public MyCustomPopOverContentNode(){
        super();
        GridPane box = new GridPane();
        Button button = new Button();
        button.setLabel("inviter");
    }

}
