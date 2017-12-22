/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Main.Historico;

import View.Main.ControlledScreen;
import View.Main.JavaFXApplication2;
import View.Main.ScreensController;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.jogador.Jogadores;
import model.jogo.Jogo;
import model.ranking.RankingField;
import model.ranking.Rankings;

/**
 * FXML Controller class
 *
 * @author tiago
 */
public class FXMLHistoricoController implements Initializable, ControlledScreen {

    private ScreensController parent;
    private ObservableList<TableContent> items;
    private boolean clicked=false;
    @FXML
    private TableColumn<TableContent, String> pontuacao;

    @FXML
    private TableColumn<TableContent, String> tema;

    @FXML
    private ListView<String> listView;

    @FXML
    private TableColumn<TableContent, String> tempo;

    @FXML
    private TableColumn<TableContent, String> id;

    @FXML
    private TableView<TableContent> tabel;
    
    @FXML
    private BorderPane pane;
    
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

        ObservableList<String> list = FXCollections.observableArrayList();

        Iterator<String> it = Jogadores.getInstance().getJogadores().keySet().iterator();
        while (it.hasNext()) {
            list.add(it.next());
        }

        listView.setItems(list);
        listView.setOnMouseClicked(e -> {
            String jogador = listView.getSelectionModel().getSelectedItems().get(0);
            System.out.println("jogador" + jogador);

            adicionarTabela(jogador);

        });

        items = FXCollections.observableArrayList();

        tabel.setItems(items);

        id.setCellValueFactory(new PropertyValueFactory<TableContent, String>("ID"));

        pontuacao.setCellValueFactory(new PropertyValueFactory<TableContent, String>("pontuacao"));

        tempo.setCellValueFactory(new PropertyValueFactory<TableContent, String>("tempo"));

        tema.setCellValueFactory(new PropertyValueFactory<TableContent, String>("tema"));

        pane.getStylesheets().add(getClass().getResource("historico.css").toExternalForm());
       
        pane.getStyleClass().add("pane");
        pane.setId("pane");
        
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.parent = screenPage;
    }

    private void adicionarTabela(String jogador) {
        this.items.clear();
        model.linhaTres.Iterator<Jogo> it = Jogadores.getInstance().getJogador(jogador).getHistorico().getIterator();
        while(it.hasNext()){
            Jogo aux= it.next();
            items.add(new TableContent(aux.getID(),aux.obterPontuacao(),aux.obterDuracao(),aux.obterTemaSelecionado()));
        }
    }

    public static class TableContent {

        private final StringProperty ID;
        private final StringProperty pontuacao;
        private final StringProperty tempo;
        private final StringProperty tema;

        public TableContent(int ID, int pontuacao, double tempo, String tema) {
            this.ID = new SimpleStringProperty(ID+"");
            this.pontuacao = new SimpleStringProperty(pontuacao+"");
            this.tempo = new SimpleStringProperty(((int)tempo/1000) + "s");
            this.tema = new SimpleStringProperty(tema);
        }

        public String getID() {
            return ID.get();
        }

        public String getPontuacao() {
            return pontuacao.get();
        }
        
        
        public String getTempo() {
            return tempo.get();
        }

        public String getTema() {
            return tema.get();
        }
    }

}
