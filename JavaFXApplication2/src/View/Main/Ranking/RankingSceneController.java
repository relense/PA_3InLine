/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Main.Ranking;

import View.Main.ControlledScreen;
import View.Main.JavaFXApplication2;
import View.Main.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.linhaTres.Iterator;
import model.ranking.JogoRanking;

import model.ranking.RankingField;
import model.ranking.Rankings;

/**
 * FXML Controller class
 *
 * @author tiago
 */
public class RankingSceneController implements Initializable, ControlledScreen {

    private ScreensController parent;
    private boolean clicked = false;
    ObservableList<TableContent> items;
    @FXML
    private TableColumn<TableContent, String> column1;

    @FXML
    private Button select;

    @FXML
    private ComboBox<String> tipoJogo;

    @FXML
    private TableColumn<TableContent, String> column2;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> tipoPontuacao;

    @FXML
    private BorderPane pane;

    @FXML
    private TableView<TableContent> tabel;

    @FXML
    void changeTable(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {
        if (!clicked) {
            clicked = true;
            parent.unloadScreen(JavaFXApplication2.Screen1ID);
            parent.loadScreen(JavaFXApplication2.Screen1ID, JavaFXApplication2.Screen1FILE);
            parent.setScreen(JavaFXApplication2.Screen1ID);

        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        items = FXCollections.observableArrayList();
        Iterator<RankingField> it = Rankings.getInstance().getRankingJogoNormalPontuacaoBase().getIterator();
        while (it.hasNext()) {
            RankingField aux = it.next();
            items.add(new TableContent(aux.getJogador(), aux.getPontuacao()));

        }

        tabel.setItems(items);

        column1.setCellValueFactory(new PropertyValueFactory<TableContent, String>("nome"));

        column2.setCellValueFactory(new PropertyValueFactory<TableContent, String>("pontuacao"));

        this.select.setOnMouseClicked(e -> {
            if (tipoJogo.getValue().equals("Normal") && tipoPontuacao.getValue().equals("Base")) {
                update(Rankings.getInstance().getRankingJogoNormalPontuacaoBase());

            } else if (tipoJogo.getValue().equals("Normal") && tipoPontuacao.getValue().equals("Corrida")) {
                update(Rankings.getInstance().getRankingJogoNormalPontuacaoCorrida());
            } else if (tipoJogo.getValue().equals("Rápido") && tipoPontuacao.getValue().equals("Base")) {
                update(Rankings.getInstance().getRankingJogoRapidoPontuacaoBase());
            } else {
                update(Rankings.getInstance().getRankingJogoRapidoPontuacaoCorrida());
            }

        });

        ObservableList<String> optionsTipoJogo = FXCollections.observableArrayList(
                "Normal",
                "Rápido"
        );
        tipoJogo.setItems(optionsTipoJogo);
        ObservableList<String> optionsTipoPontuacao = FXCollections.observableArrayList(
                "Base",
                "Corrida"
        );
        tipoPontuacao.setItems(optionsTipoPontuacao);
          pane.setStyle("-fx-background-image: url('" + getClass().getResource("rankingBackGround.jpg").toExternalForm() + "');-fx-background-repeat: no-repeat;-fx-background-size: cover; ");
          pane.getStylesheets().add(getClass().getResource("Ranking.css").toExternalForm());

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.parent = screenPage;
    }

    public void update(JogoRanking rank) {
        items.clear();

        Iterator<RankingField> it = rank.getIterator();
        while (it.hasNext()) {
            RankingField aux = it.next();
            items.add(new TableContent(aux.getJogador(), aux.getPontuacao()));

        }

    }

    public static class TableContent {

        private final StringProperty nome;
        private final StringProperty pontuacao;

        public TableContent(String nome, int pontuacao) {
            this.nome = new SimpleStringProperty(nome);
            this.pontuacao = new SimpleStringProperty(pontuacao + "");
        }

        public String getNome() {
            return nome.get();
        }

        public String getPontuacao() {
            return pontuacao.get();
        }

    }

}
