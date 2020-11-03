package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class ControllerLogin {

    @FXML
    private Button BUTTON;

    @FXML
    private Button BUTTON_LOG;

    @FXML
    private TextField USERNAME;

    @FXML
    private TextField PASSWORD;

    @FXML
    private Circle CIRCLE;

    @FXML
    public void CLOSE_APPLICATION(ActionEvent e){

        Platform.exit();
        System.exit(0);

    }

    @FXML
    public void CONNECT_USER_ACCOUNT(ActionEvent e){

        if(USERNAME.getText().isEmpty() || PASSWORD.getText().isEmpty()) {

            CIRCLE.setFill(Color.rgb(240, 71, 71));

            return;

        }

        if(Main.DB_MANAGER.USER_HAS_ACCOUNT(USERNAME.getText(), PASSWORD.getText())) {

            try {

                Main.ROOT = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Main.STAGE.setTitle("SmartRedis ▪ Made by @antoinisfrench");
                Main.STAGE.setScene(new Scene(Main.ROOT, 414, 736));
                Main.STAGE.show();

            } catch (IOException el) {
                el.printStackTrace();
            }


            Main.CURRENT_USERNAME = USERNAME.getText();

        } else {

            CIRCLE.setFill(Color.rgb(240, 71, 71));

        }

        USERNAME.setText("");
        PASSWORD.setText("");

    }

}
