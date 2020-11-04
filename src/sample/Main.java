package sample;

import imgur.Uploader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pubsub.Consumer;
import pubsub.Publisher;
import sql.DbManager;

import java.io.File;

public class Main extends Application {

    //@../../../../Desktop/avatar/null1.png

    public static DbManager DB_MANAGER;
    public static Publisher PRODUCER;

    public static String CURRENT_USERNAME;

    public static Parent ROOT;
    public static Stage STAGE;
    public static VBox BOX;
    public static ScrollPane SCROLL_PANE;

    @Override
    public void start(Stage PRIMARY_STAGE) throws Exception{

        ROOT = FXMLLoader.load(getClass().getResource("sample_login.fxml"));
        PRIMARY_STAGE.setTitle("SmartRedis ▪ Made by @antoinisfrench");
        PRIMARY_STAGE.setScene(new Scene(ROOT, 414, 736));
        PRIMARY_STAGE.show();

        STAGE = PRIMARY_STAGE;

    }


    public static void main(String[] args) {

        DB_MANAGER = new DbManager();
        PRODUCER = new Publisher();

        Thread THREAD = new Consumer("TO_OTHER");
        THREAD.start();

        //Test pour l´integration avec Imgur.
        //System.out.println(Uploader.UPLOAD(new File("C:/Users/Antoine/Desktop/MONTAGE FORTNITE/MINIATURE_MONTAGE_FORTNITE.png")));

        launch(args);

    }
}
