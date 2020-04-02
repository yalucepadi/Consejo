package consejo1.pkg0.pkg0;

import MethodConnection.ConnectionUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.mysql.fabric.xmlrpc.base.Data;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeloRanking.Ranking;

public class VentanaController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Label panelHeader;

    @FXML
    private Label EtiquetaN;

    @FXML
    private Label EtiquetaR;

    @FXML
    private HBox contenedorN;

    @FXML
    private HBox contenedorR;

    @FXML
    private VBox vBoxPanelLateral;

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

    // @FXML
    // private TableColumn<Ranking, Integer> cId;
    @FXML
    private TableColumn<Ranking, String> cEntidades;

    @FXML
    private TableColumn<Ranking, Integer> cNMenciones;

    @FXML
    private TableColumn<Ranking, Void> cButtonInfo;

    @FXML
    JFXComboBox<String> macroRcombo;

    ObservableList<String> optionsMc
            = FXCollections.observableArrayList(
                    "centro 1",
                    "centro 2",
                    "centro 3",
                    "sur 1",
                    "sur 2",
                    "norte 1",
                    "norte 2"
            );

    @FXML
    JFXComboBox<String> grupoEtarioDn;
    ObservableList<String> optionsGeDn
            = FXCollections.observableArrayList(
                    "adolecencia",
                    "niñez",
                    "primera infacia",
                    "juventud",
                    "adultez",
                    "adultez mayor"
            );

    @FXML
    JFXComboBox<String> fuente;
    ObservableList<String> optionsF
            = FXCollections.observableArrayList(
                    "encuesta virtual",
                    "jornadas por la educacion"
            );
    @FXML
    JFXComboBox<String> grupoEtario;
    ObservableList<String> optionsGe
            = FXCollections.observableArrayList(
                    "adolecencia",
                    "niñez",
                    "primera infacia",
                    "juventud",
                    "adultez",
                    "adultez mayor"
            );
    @FXML
    JFXComboBox<String> perfilParticipantes;
    ObservableList<String> optionsPp
            = FXCollections.observableArrayList(
                    "Estudiante",
                    "Docente",
                    "Ni estudiante, ni docente",
                    "Docente y estudiante"
            );
    @FXML
    JFXComboBox<String> departamentos;
    ObservableList<String> optionsD
            = FXCollections.observableArrayList(
                    "Amazonas",
                    "Áncash",
                    "Apurímac",
                    "Arequipa",
                    "Ayacucho",
                    "Cajamarca",
                    "Cusco",
                    "Huancavelica",
                    "Huánuco",
                    "Ica",
                    "Junín",
                    "La Libertad",
                    "Lambayeque",
                    "Lima",
                    "Loreto",
                    "Madre de Dios",
                    "Moquegua",
                    "Pasco",
                    "Piura",
                    "Puno",
                    "San Martín",
                    "Tacna",
                    "Tumbes",
                    "Ucayali"
            );
    @FXML
    JFXComboBox<String> discapacidad;
    ObservableList<String> optionsDisca
            = FXCollections.observableArrayList(
                    "Si",
                    "No"
            );

    @FXML
    JFXComboBox<String> paternidad;
    ObservableList<String> optionsPat
            = FXCollections.observableArrayList(
                    "Si",
                    "No"
            );

    @FXML
    JFXComboBox<String> gEtnico;
    ObservableList<String> optionsGeT
            = FXCollections.observableArrayList(
                    "Castellano",
                    "Lengua Originaria",
                    "Idioma extranjero"
            );
    private ObservableList<Ranking> listRanking;
    private ObservableList<Ranking> listFiltro;

    PreparedStatement preparedStatement;
    java.sql.ResultSet resultSet = null;

    String macroRcomboSQL = null;
    String grupoEtarioSQL = null;
    String perfilParticipantesSQL = null;
    String departamentosSQL = null;
    String grupoEtarioDnSQL = null;
    String fuenteSQL = null;
    String discapacidadSQL = null;
    String paternidadSQL = null;
    String gEtnicoSQL = null;

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
            rankingPanel.setBackground(new Background(new BackgroundFill(Color.rgb(246, 246, 246), CornerRadii.EMPTY, Insets.EMPTY)));
        }

    }

    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

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
    private void loutOutMethod(ActionEvent event) {
        try {
             changeScreenButtonPushed(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    Connection connection;

    @FXML
    private void añadir(ActionEvent event) throws SQLException {

        try {
            ConnectionUtil connectionUtil = new ConnectionUtil();
            connection = (Connection) connectionUtil.getConnection();

            String sql = "INSERT INTO ranking (Entidades,id) VALUES (?,?)";
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, "Gente");
            preparedStatement.setInt(3, 546);
            preparedStatement.executeUpdate();
            popullateTable();

            //fetEntidadesRowList();
            //fetNMencionesRowList();
            AñadirBtn.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    String SQL = "SELECT Entidades , COUNT(Entidades) AS NMenciones FROM `ranking` GROUP by Entidades";

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
                //ranking.setId(resultSet.getInt("id"));
                listRanking.add(ranking);
                cEntidades.setCellValueFactory(new PropertyValueFactory<>("entidades"));
                cNMenciones.setCellValueFactory(new PropertyValueFactory<>("nroMenciones"));
                //  cId.setCellValueFactory(new PropertyValueFactory<>("id"));

                consejoData.setItems(listRanking);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void filtrarAndPopulateTabla() {
        macroRcomboSQL = filtro(macroRcombo);
        grupoEtarioSQL = filtro(grupoEtario);
        perfilParticipantesSQL = filtro(perfilParticipantes);
        departamentosSQL = filtro(departamentos);
        grupoEtarioDnSQL = filtro(grupoEtarioDn);
        fuenteSQL = filtro(fuente);
        discapacidadSQL = filtro(discapacidad);
        paternidadSQL = filtro(paternidad);
        gEtnicoSQL = filtro(gEtnico);

        String SQLF = ("SELECT r.Entidades, count(f.departamento & f.fuente & f.grupoEtario & f.macroregion & f.grupo_etareo_de_participante & f.perfil_de_participante & f.discapacidad  & f.paternidad & f.grupo_étnico) as NMenciones, r.id from ranking  r inner join filtros f WHERE"
                + " f.departamento= " + "'" + departamentosSQL + "'"
                + " or f.fuente=" + "'" + fuenteSQL + "'"
                + " or f.grupoEtario=" + "'" + grupoEtarioSQL + "'"
                + " or f.macroregion=" + "'" + macroRcomboSQL + "'"
                + " or f.grupo_etareo_de_participante=" + "'" + grupoEtarioDnSQL + "'"
                + " or f.perfil_de_participante=" + "'" + perfilParticipantesSQL + "'"
                + " or f.discapacidad=" + "'" + discapacidadSQL + "'"
                + " or f.paternidad=" + "'" + paternidadSQL + "'"
                + " or f.grupo_étnico=" + "'" + gEtnicoSQL + "'" + " GROUP by r.Entidades").toLowerCase();

        String sqlp = "SELECT r.Entidades, count(f.departamento & f.fuente & f.grupoEtario & f.macroregion & f.grupo_etareo_de_participante & f.perfil_de_participante & f.discapacidad  & f.paternidad & f.grupo_étnico) as NMenciones, r.id from ranking  r inner join filtros f WHERE f.departamento= 'Cajamarca' or f.fuente='encuesta virtual' or f.grupoEtario='adolecencia' or f.macroregion='norte 1' or f.grupo_etareo_de_participante='niñez' or f.perfil_de_participante='Estudiante' or f.discapacidad='No' or f.paternidad='Si' or f.grupo_étnico='Castellano'";

        System.out.println(SQLF);
        listFiltro = FXCollections.observableArrayList();
        ConnectionUtil connectionUtil = new ConnectionUtil();
        connection = (Connection) connectionUtil.getConnection();
        try {
            resultSet = (java.sql.ResultSet) connection.createStatement().executeQuery(SQLF);
            while (resultSet.next()) {
                Ranking ranking = new Ranking();
                ranking.setEntidades(resultSet.getString("Entidades"));
                ranking.setNroMenciones(resultSet.getInt("NMenciones"));
                // ranking.setId(resultSet.getInt("id"));
                listFiltro.add(ranking);
                cEntidades.setCellValueFactory(new PropertyValueFactory<>("entidades"));
                cNMenciones.setCellValueFactory(new PropertyValueFactory<>("nroMenciones"));
                // cId.setCellValueFactory(new PropertyValueFactory<>("id"));

                consejoData.setItems(listFiltro);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String filtro(JFXComboBox<String> comboBoxString) {

        String resultado = comboBoxString.getSelectionModel().getSelectedItem();

        return resultado;
    }

    //1
    @FXML
    public void selecionarMregion() {

        filtrarAndPopulateTabla();
    }

    //2
    @FXML
    public void selecionarFuente() {

        filtrarAndPopulateTabla();

    }

    //3
    @FXML
    public void selecionarGrupoEtario() {
        filtrarAndPopulateTabla();

    }

    //4
    @FXML
    public void selecionarDepartamentos() {
        filtrarAndPopulateTabla();

    }

    //5
    @FXML
    public void selecionarDiscapacidad() {

        filtrarAndPopulateTabla();

    }

    //6
    @FXML
    public void selecionarGetcnico() {
        filtrarAndPopulateTabla();

    }

    //7
    @FXML
    public void selecionarGrupoEterarioDn() {

        filtrarAndPopulateTabla();

    }

    //8
    @FXML
    public void selecionarPaternidad() {
        filtrarAndPopulateTabla();

    }

    //9
    @FXML
    public void selecionarperfilParticipantes() {

        filtrarAndPopulateTabla();

    }

    private void colocarImagenBotones() {
        URL linkguardar = getClass().getResource("/img/comment-alt.png");
        URL linkRanking = getClass().getResource("/img/chart-bar.png");
        URL linkLogout = getClass().getResource("/img/sign-out-alt.png");
        Image imagenNuevo = new Image(linkguardar.toString(), 20, 20, false, true);
        Image imagenRanking = new Image(linkRanking.toString(), 20, 20, false, true);
        Image imagenLoutOut = new Image(linkLogout.toString(), 20, 20, false, true);
        pruebaBtn.setGraphic((new ImageView(imagenNuevo)));
        rankingBtn.setGraphic((new ImageView(imagenRanking)));
        closeBtn.setGraphic((new ImageView(imagenLoutOut)));
    }

    public void iteracionRapidaDcomboBoxes(ObservableList<String> observableList, JFXComboBox<String> comboBoxString) {
        for (int i = 0; i < observableList.size(); i++) {
            comboBoxString.getItems().add(observableList.get(i));
        }

    }

    public void botonesInfo() {
        TableColumn<Ranking, Void> colBtn = new TableColumn("INFO");
        cButtonInfo = colBtn;

        Callback<TableColumn<Ranking, Void>, TableCell<Ranking, Void>> cellFactory = new Callback<TableColumn<Ranking, Void>, TableCell<Ranking, Void>>() {
            @Override
            public TableCell<Ranking, Void> call(final TableColumn<Ranking, Void> param) {
                final TableCell<Ranking, Void> cell = new TableCell<Ranking, Void>() {

                    private final Button btn = new Button("Info");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            //Data data = getTableView().getItems().get(getIndex());
                            //System.out.println("selectedData: " + data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        cButtonInfo.setCellFactory(cellFactory);

        consejoData.getColumns().add(colBtn);

    }

    public void cargaDcomboBoxes() {
        iteracionRapidaDcomboBoxes(optionsMc, macroRcombo);
        iteracionRapidaDcomboBoxes(optionsGe, grupoEtario);
        iteracionRapidaDcomboBoxes(optionsPp, perfilParticipantes);
        iteracionRapidaDcomboBoxes(optionsD, departamentos);
        iteracionRapidaDcomboBoxes(optionsGeDn, grupoEtarioDn);
        iteracionRapidaDcomboBoxes(optionsF, fuente);
        iteracionRapidaDcomboBoxes(optionsDisca, discapacidad);
        iteracionRapidaDcomboBoxes(optionsPat, paternidad);
        iteracionRapidaDcomboBoxes(optionsGeT, gEtnico);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EtiquetaN.prefWidthProperty().bind(contenedorN.widthProperty());
        EtiquetaR.prefWidthProperty().bind(contenedorR.widthProperty());
        contenedorN.prefWidthProperty().bind(vBoxPanelLateral.widthProperty());
        contenedorR.prefWidthProperty().bind(vBoxPanelLateral.widthProperty());
        colocarImagenBotones();
        cargaDcomboBoxes();
        botonesInfo();

        // selecionarMregion();
        //System.out.println(filtro(fuente));
        //macroRcombo.setVisible(true);
        //macroRcombo.setValue("norte");
        //macroRcombo.setVisibleRowCount(5);  
        popullateTable();
        //filtrarAndPopulateTabla();

        /*fetNMencionesRowList();
        fetEntidadesColumnList();
        fetEntidadesRowList();
       fetNMencionesColumnList();*/
        pruebaBtn.setDisable(true);
    }

}
