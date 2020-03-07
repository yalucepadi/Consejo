package consejo1.pkg0.pkg0;

import MethodConnection.ConnectionUtil;
import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import modeloRanking.Ranking;

public class VentanaController implements Initializable {

    @FXML
    private Label myMessage;

    @FXML
    private JFXButton pruebaBtn;

    @FXML
    private JFXButton rankingBtn;

    @FXML
    private JFXButton AñadirBtn;

    @FXML
    private JFXButton closeBtn;

    @FXML
    private Pane rankingPanel;

    @FXML
    private Pane necesidadesPanel;

    @FXML
    private TableView<Ranking> consejoData;

    @FXML
    private TableColumn colum1;

    @FXML
    private TableColumn colum2;

    ObservableList<Ranking> rankings;

    private int posicionEnTabla;

    

    public VentanaController() {
        // connection = (Connection)ConnectionUtil.conDB();

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == pruebaBtn) {
            necesidadesPanel.toFront();
            necesidadesPanel.setBackground(new Background(new BackgroundFill(Color.rgb(52, 122, 176), CornerRadii.EMPTY, Insets.EMPTY)));

        }
        if (event.getSource() == rankingBtn) {

            rankingPanel.toFront();
            rankingPanel.setBackground(new Background(new BackgroundFill(Color.rgb(132, 85, 164), CornerRadii.EMPTY, Insets.EMPTY)));
        }

    }

    @FXML
    private void close(ActionEvent event) {
        if (event.getSource() == closeBtn) {
            System.exit(0);
        }
    }

    @FXML
    private void añadir(ActionEvent event) throws SQLException{
    
        
        ConnectionUtil connectionUtil= new ConnectionUtil();
    Connection connection= (Connection)connectionUtil.getConnection();
        
    String sql="INSERT INTO RANKING VALUES (1,'Familia',12)"; 
        Statement statement=(Statement)connection.createStatement();
        statement.executeUpdate(sql);
        /*try {
            System.out.println("precionado");
            Ranking ranking = new Ranking();
            Ranking ranking1 = new Ranking("famlia", 12);
            ranking.setEntidades("Familia");
            ranking.setNroMenciones(12);
            rankings.add(ranking1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
*/
    }

    private void colocarImagenBotones() {
        URL linkguardar = getClass().getResource("/img/comment-alt.png");
        URL linkRanking = getClass().getResource("/img/chart-bar.png");
        URL linkClose = getClass().getResource("/img/window-close.png");
        Image imagenNuevo = new Image(linkguardar.toString(), 20, 20, false, true);
        Image imagenRanking = new Image(linkRanking.toString(), 20, 20, false, true);
        Image imagenClose = new Image(linkClose.toString(), 20, 20, false, true);
        pruebaBtn.setGraphic((new ImageView(imagenNuevo)));
        rankingBtn.setGraphic((new ImageView(imagenRanking)));
        closeBtn.setGraphic((new ImageView(imagenClose)));
    }

    private void inicializarTabla() {
        try {
            colum1.setCellValueFactory(new PropertyValueFactory<Ranking, String>("Entidades"));
            colum2.setCellValueFactory(new PropertyValueFactory<Ranking, Integer>("Nª De menciones"));
            rankings = FXCollections.observableArrayList();
            consejoData.setItems(rankings);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colocarImagenBotones();
        this.inicializarTabla();
        pruebaBtn.setDisable(false);
    }

}
