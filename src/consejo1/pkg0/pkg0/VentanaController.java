package consejo1.pkg0.pkg0;

import MethodConnection.ConnectionUtil;
import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.SQLException;
import java.lang.*;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Callback;
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
    private TableColumn<Ranking, Integer> cId;

    @FXML
    private TableColumn<Ranking, String> cEntidades;

    @FXML
    private TableColumn<Ranking, Integer> cNMenciones;

    private ObservableList<Ranking> listRanking;
    PreparedStatement preparedStatement;
    java.sql.ResultSet resultSet = null;

    public VentanaController() {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        connection = (Connection) connectionUtil.getConnection();

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
    Connection connection;

    @FXML
    private void añadir(ActionEvent event) throws SQLException {

        try {
            ConnectionUtil connectionUtil = new ConnectionUtil();
            connection = (Connection) connectionUtil.getConnection();

            String sql = "INSERT INTO ranking (Entidades, NMenciones,id) VALUES (?,?,?)";
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, "Gente");
            preparedStatement.setInt(2, 120);
            preparedStatement.setInt(3, 74);
            preparedStatement.executeUpdate();
           popullateTable();
            //fetEntidadesRowList();
            //fetNMencionesRowList();
            AñadirBtn.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    String SQL = "SELECT Entidades, NMenciones,id FROM ranking ";

    /*public void fetNMencionesRowList() {
        rankingsData = FXCollections.observableArrayList();

        try {
            resultSet = (java.sql.ResultSet) connection.createStatement().executeQuery(SQLNM);

            while (resultSet.next()) {
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getString(i));
                }
                System.out.println("Row[1] added" + row);
                rankingsData.add(row);
            }
            //Comparator comparator;
            //FXCollections.sort(rankingsData);
            //rankingsData.sort(Integer::compareTo);
            consejoData.setItems(rankingsData);

            consejoData.sort();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //only fetch columns
    private void fetNMencionesColumnList() {

        try {
            resultSet = connection.createStatement().executeQuery(SQLNM);

            //SQL FOR SELECTING ALL OF CUSTOMER
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(resultSet.getMetaData().getColumnName(i + 1).toUpperCase());
                //col.setSortType(TableColumn.SortType.ASCENDING);

                //col.setSortType(TableColumn.SortType.DESCENDING);
//
//                 col.setCellValueFactory((CellDataFeatures<ObservableList,Integer>param) -> {
//                     
//                     return new SimpleObjectProperty<Integer>((Integer) param.getValue().get(j));
//                });
//                
//                
                
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, Integer>, ObservableValue<Integer>>() {
    @Override public ObservableValue<Integer> call(CellDataFeatures<ObservableList, Integer> c) {
        return new SimpleObjectProperty<>((Integer) c.getValue().get(j));
    }
});
                consejoData.getColumns().removeAll(col);

                consejoData.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }

    String SQL = "SELECT Entidades FROM ranking ";

    public void fetEntidadesRowList() {
        rankingsData = FXCollections.observableArrayList();

        try {
            resultSet = (java.sql.ResultSet) connection.createStatement().executeQuery(SQL);

            while (resultSet.next()) {
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getString(i));
                }
                System.out.println("Row[1] added" + row);
                rankingsData.add(row);
            }
            //Comparator comparator;
            //FXCollections.sort(rankingsData);
            //rankingsData.sort(Integer::compareTo);
            consejoData.setItems(rankingsData);

            consejoData.sort();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //only fetch columns
    private void fetEntidadesColumnList() {

        try {

            //SQL FOR SELECTING ALL OF CUSTOMER
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(resultSet.getMetaData().getColumnName(i + 1).toUpperCase());
                //col.setSortType(TableColumn.SortType.ASCENDING);

                //col.setSortType(TableColumn.SortType.DESCENDING);
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                       return new SimpleStringProperty(param.getValue().get(j).toString());

                    }

                
                consejoData.getColumns().removeAll(col);

                consejoData.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }
    
    
    
     */
    public void popullateTable() {
        listRanking = FXCollections.observableArrayList();
        ConnectionUtil connectionUtil = new ConnectionUtil();
        connection = (Connection) connectionUtil.getConnection();
        try {
            resultSet = (java.sql.ResultSet) connection.createStatement().executeQuery(SQL);
            while (resultSet.next()) {
                Ranking ranking = new Ranking();
                ranking.setEntidades(resultSet.getString("Entidades"));
                ranking.setNroMenciones(resultSet.getInt("NMenciones"));
                ranking.setId(resultSet.getInt("id"));
                listRanking.add(ranking);
                cEntidades.setCellValueFactory(new PropertyValueFactory<>("entidades"));
                cNMenciones.setCellValueFactory(new PropertyValueFactory<>("nroMenciones"));
                cId.setCellValueFactory(new PropertyValueFactory<>("id"));

                consejoData.setItems(listRanking);

            }
        } catch (Exception e) {
        }

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colocarImagenBotones();

        popullateTable();

        /*fetNMencionesRowList();
        fetEntidadesColumnList();
        fetEntidadesRowList();
       fetNMencionesColumnList();*/
        pruebaBtn.setDisable(true);
    }

}
