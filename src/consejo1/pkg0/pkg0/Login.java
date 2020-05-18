/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consejo1.pkg0.pkg0;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author yair
 */
public class Login extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {   
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);

        //set mouse pressed
        root.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        //set mouse drag
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

        stage.setTitle("Consejo");
        stage.getIcons().add(new Image("img/cne.png"));
        stage.setScene(scene);
        stage.show();
    }

}
