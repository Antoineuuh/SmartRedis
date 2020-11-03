package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Controller {

    @FXML
    private TextField MSG_INPUT;

    @FXML
    private VBox BOX;

    @FXML
    private ScrollPane SCROLL_PANE;

    public void ADD_MSG_TO_PANE_HAS_SENDER(ActionEvent e){

        if(MSG_INPUT.getText().isEmpty()) {
            return;
        }

        Group GROUP = new Group();

        //LOGO
        ImageView LOGO = new ImageView("/avatar/male3.png");
        LOGO.setFitHeight(50);
        LOGO.setFitWidth(50);
        LOGO.setLayoutX(-163);
        LOGO.setLayoutY(-132);

        GROUP.getChildren().add(LOGO);

        //LABEL
        Label LABEL = new Label(MSG_INPUT.getText());
        LABEL.setFont(new Font("Arial", 15));
        LABEL.setLineSpacing(5.0);
        LABEL.setPadding(new Insets(15));
        LABEL.setStyle("-fx-text-fill:  #3b414a; -fx-background-color: #FFFFFF; -fx-background-radius: 0 18 18 18;");
        LABEL.setWrapText(true);
        LABEL.setMaxWidth(270.0);
        LABEL.setMaxHeight(270.0);
        LABEL.setLayoutX(-162);
        LABEL.setLayoutY(-73);

        GROUP.getChildren().add(LABEL);

        //ADD GROUP TO SCROLLPANE.
        BOX.getChildren().add(GROUP);

        //Autoscroll
        Animation ANIMATION = new Timeline(
                new KeyFrame(Duration.seconds(0.25),
                        new KeyValue(SCROLL_PANE.vvalueProperty(), 1)));

        ANIMATION.play();

        Main.PRODUCER.PUBLISH("TO_OTHER", Main.CURRENT_USERNAME + "@" + MSG_INPUT.getText());
        Main.BOX = BOX;
        Main.SCROLL_PANE = SCROLL_PANE;

        MSG_INPUT.setText("");
    }

    public static void ADD_MSG_TO_PANE_HAS_RECEIVER(String MSG_INPUT) {

        Group GROUP = new Group();

        //LOGO
        ImageView LOGO = new ImageView("/avatar/male2.png");
        LOGO.setFitHeight(50);
        LOGO.setFitWidth(50);
        LOGO.setLayoutX(-163);
        LOGO.setLayoutY(-132);

        GROUP.getChildren().add(LOGO);

        //LABEL
        Label LABEL = new Label(MSG_INPUT);
        LABEL.setFont(new Font("Arial", 15));
        LABEL.setLineSpacing(5.0);
        LABEL.setPadding(new Insets(15));
        LABEL.setStyle("-fx-text-fill: #FFFFFF; -fx-background-color: #3b414a; -fx-background-radius: 0 18 18 18;");
        LABEL.setWrapText(true);
        LABEL.setMaxWidth(270.0);
        LABEL.setMaxHeight(270.0);
        LABEL.setLayoutX(-162);
        LABEL.setLayoutY(-73);

        GROUP.getChildren().add(LABEL);

        //ADD GROUP TO SCROLLPANE.
        Main.BOX.getChildren().add(GROUP);

        //Autoscroll
        Animation ANIMATION = new Timeline(
                new KeyFrame(Duration.seconds(0.25),
                        new KeyValue(Main.SCROLL_PANE.vvalueProperty(), 1)));

        ANIMATION.play();

    }

}
