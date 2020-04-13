/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consejo1.pkg0.pkg0;

import MethodConnection.ConnectionUtil;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modeloRanking.Usuario;

/**
 *
 * @author yair
 */
public class LoginController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private ImageView logo;
    
    @FXML
    private ImageView fondoLogin;
    
    @FXML
    private JFXButton btnIngresar;
    @FXML
    private JFXButton closeLBtn;

    @FXML
    private JFXTextField userTxt;

    @FXML
    private JFXPasswordField passwordTxt;

    java.sql.ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    java.sql.ResultSet resultSetC = null;
    PreparedStatement preparedStatementC = null;

    public LoginController() {

        ConnectionUtil connectionUtil = new ConnectionUtil();
        connection = (Connection) connectionUtil.getConnection();
    }
    Connection connection;

    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ventana.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void close(ActionEvent event) {
        if (event.getSource() == closeLBtn) {
            System.exit(0);
        }
    }

    private void colocarImagenBotones() {

        URL linkClose = getClass().getResource("/img/window-close.png");
        URL linkFondoLogin = getClass().getResource("/img/login.png");

        Image imagenClose = new Image(linkClose.toString(), 20, 20, false, true);
        Image imagenFondoLogin= new Image(linkFondoLogin.toString(),706,508,false,true);
                fondoLogin.setImage(imagenFondoLogin);


        closeLBtn.setGraphic((new ImageView(imagenClose)));
    }
    String SQLu = "SELECT usuario FROM usuario WHERE usuario=(?)";
    String SQLc = "SELECT contraseña FROM usuario WHERE contraseña=(?)";

    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    @FXML
    public void logueo(ActionEvent event) {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        connection = (Connection) connectionUtil.getConnection();

        try {

            String usuario = userTxt.getText();

            String contraseña = passwordTxt.getText();
            String mD5 = getMd5(contraseña);
            System.out.println(usuario);
            System.out.println(mD5);
            preparedStatementC = (PreparedStatement) connection.prepareStatement(SQLc);
            preparedStatement = (PreparedStatement) connection.prepareStatement(SQLu);
            preparedStatement.setString(1, usuario);
            preparedStatementC.setString(1, mD5);
            resultSet = preparedStatement.executeQuery();
            resultSetC = preparedStatementC.executeQuery();
            if (!resultSet.next()) {
                infoBox("Ingrese usuario correcto", "error", null);

            } else {
                if (!resultSetC.next()) {
                    infoBox("Ingrese contraseña correcta", "error", null);
                } else {

                    infoBox("Ingreso correcto", "Satisfactoriamente", null);
                    changeScreenButtonPushed(event);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colocarImagenBotones();

    }

}
