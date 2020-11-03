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

/**
 * This file is part of Aster.
 *
 * Aster is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Aster is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Aster. If not, see <http://www.gnu.org/licenses/>.
 */

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

        System.out.println(Uploader.UPLOAD(new File("C:/Users/Antoine/Desktop/MONTAGE FORTNITE/MINIATURE_MONTAGE_FORTNITE.png")));

        launch(args);

    }
}
