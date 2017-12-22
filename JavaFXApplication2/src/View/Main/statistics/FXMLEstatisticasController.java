/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Main.statistics;

import View.Main.ControlledScreen;
import View.Main.JavaFXApplication2;
import View.Main.ScreensController;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.jogador.Jogador;
import model.jogador.Jogadores;
import model.jogoFactory.TipoJogo;
import model.linhaTres.Iterator;
import model.ranking.JogoRanking;
import model.ranking.RankingField;

/**
 * FXML Controller class
 *
 * @author tiago
 */
public class FXMLEstatisticasController implements Initializable, ControlledScreen {

    HashMap<String, Integer> numeroJogosPorJogador;
    HashMap<String, Double> numeroTempoMedioPorJogador;
    HashMap<String, Integer> numeroPontuacaoMaximaPorJogador;
    HashMap<String, Integer> numeroEscolhaTemasPorJogador;

    private ScreensController parent;
    private String selected = "line";
    private boolean clicked = false;

    @FXML
    private GridPane gridPane;
    @FXML
    private ComboBox<String> tipoJogo;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> tipoPontuacao;
    @FXML
    private BorderPane pane;
    

    @FXML
    void barChartAction(ActionEvent event) {
        this.selected = "bar";
        this.updateViewContent();
    }

    @FXML
    void lineChartAction(ActionEvent event) {
        this.selected = "line";
        this.updateViewContent();
    }

    @FXML
    void areaChartAction(ActionEvent event) {
        this.selected = "area";
        this.updateViewContent();
    }

    @FXML
    void pieChartAction(ActionEvent event) {
        this.selected = "pie";
        this.updateViewContent();
    }

    @FXML
    void changeContent(ActionEvent event) {
        updateContent();

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
        pane.getStylesheets().add(getClass().getResource("statistis.css").toExternalForm());

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
        getInfo(TipoJogo.JOGO_NORMAL_BASE);
        
        createLine();
    }

    private void getInfo(TipoJogo tipoJogo) {
        JogoRanking ranking = tipoJogo.getRanking();

        Iterator<RankingField> it = ranking.getIterator();
        numeroJogosPorJogador = new HashMap();

        while (it.hasNext()) {
            String nomee = it.next().getJogador();

            Jogador aux = Jogadores.getInstance().getJogador(nomee);

            numeroJogosPorJogador.put(aux.getNome(), aux.obterEstatisticas(tipoJogo).getNumeroJogos());
            System.out.println("testtt" + aux.obterEstatisticas(tipoJogo).getNumeroJogos() + aux.getNome());
        }
        it = ranking.getIterator();
        numeroTempoMedioPorJogador = new HashMap();

        while (it.hasNext()) {
            Jogador aux = Jogadores.getInstance().getJogador(it.next().getJogador());

            numeroTempoMedioPorJogador.put(aux.getNome(), aux.obterEstatisticas(tipoJogo).getTempoMedio());
        }
        it = ranking.getIterator();
        numeroPontuacaoMaximaPorJogador = new HashMap();

        while (it.hasNext()) {
            Jogador aux = Jogadores.getInstance().getJogador(it.next().getJogador());

            numeroPontuacaoMaximaPorJogador.put(aux.getNome(), aux.obterEstatisticas(tipoJogo).getPontuacaoMaxima());
        }

        it = ranking.getIterator();
        numeroEscolhaTemasPorJogador = new HashMap();

        while (it.hasNext()) {
            HashMap<String, Integer> aux = Jogadores.getInstance().getJogador(it.next().getJogador()).obterEstatisticas(tipoJogo).getTemaEscolhido();

            java.util.Iterator<String> it2 = aux.keySet().iterator();

            while (it2.hasNext()) {
                String auxString = it2.next();
                if (!numeroEscolhaTemasPorJogador.containsKey(auxString)) {
                    numeroEscolhaTemasPorJogador.put(auxString, 0);
                }
                numeroEscolhaTemasPorJogador.replace(auxString,
                        numeroEscolhaTemasPorJogador.get(auxString) + aux.get(auxString)
                );

            }

        }

    }

    private Series getNumeroJogosSeries(HashMap map) {

        Series series = new Series();
        java.util.Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String aux = it.next();

            series.getData().add(new Data(aux, map.get(aux)));
        }
        return series;

    }

    private LineChart getLinheChart(String labelx, String labely) {

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(labelx);

        final LineChart<String, Number> lineChart
                = new LineChart<String, Number>(xAxis, yAxis);

        lineChart.setTitle(labely);

        return lineChart;
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

        this.parent = screenPage;
    }

    private void createLine() {

        LineChart line = this.getLinheChart("Jogadores", "Numero de Jogos top 10");
        line.getData().add(this.getNumeroJogosSeries(numeroJogosPorJogador));
        gridPane.add(line, 0, 0);
        LineChart line1 = this.getLinheChart("Jogadores", "Pontuaçao maxima top 10");
        line1.getData().add(this.getNumeroJogosSeries(this.numeroPontuacaoMaximaPorJogador));
        gridPane.add(line1, 1, 0);
        LineChart line2 = this.getLinheChart("Jogadores", "Tempo medio top 10");
        line2.getData().add(this.getNumeroJogosSeries(this.numeroTempoMedioPorJogador));
        gridPane.add(line2, 1, 1);
        LineChart line3 = this.getLinheChart("Temas", "Temas mais usados do top 10");
        line3.getData().add(this.getNumeroJogosSeries(this.numeroEscolhaTemasPorJogador));
        gridPane.add(line3, 0, 1);

    }

    private void createBar() {
        BarChart line = this.getBarChart("Jogadores", "Numero de Jogos top 10");
        line.getData().add(this.getNumeroJogosSeries(numeroJogosPorJogador));
        gridPane.add(line, 0, 0);
        BarChart line1 = this.getBarChart("Jogadores", "Pontuaçao maxima top 10");
        line1.getData().add(this.getNumeroJogosSeries(this.numeroPontuacaoMaximaPorJogador));
        gridPane.add(line1, 1, 0);
        BarChart line2 = this.getBarChart("Jogadores", "Tempo medio top 10");
        line2.getData().add(this.getNumeroJogosSeries(this.numeroTempoMedioPorJogador));
        gridPane.add(line2, 1, 1);
        BarChart line3 = this.getBarChart("Temas", "Temas mais usados do top 10");
        line3.getData().add(this.getNumeroJogosSeries(this.numeroEscolhaTemasPorJogador));
        gridPane.add(line3, 0, 1);
    }

    private BarChart getBarChart(String labelx, String labely) {

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(labelx);

        final BarChart<String, Number> lineChart
                = new BarChart<String, Number>(xAxis, yAxis);

        lineChart.setTitle(labely);

        return lineChart;
    }

    private void createArea() {
        AreaChart line = this.getAreaChart("Jogadores", "Numero de Jogos top 10");
        line.getData().add(this.getNumeroJogosSeries(numeroJogosPorJogador));
        gridPane.add(line, 0, 0);
        AreaChart line1 = this.getAreaChart("Jogadores", "Pontuaçao maxima top 10");
        line1.getData().add(this.getNumeroJogosSeries(this.numeroPontuacaoMaximaPorJogador));
        gridPane.add(line1, 1, 0);
        AreaChart line2 = this.getAreaChart("Jogadores", "Tempo medio top 10");
        line2.getData().add(this.getNumeroJogosSeries(this.numeroTempoMedioPorJogador));
        gridPane.add(line2, 1, 1);
        AreaChart line3 = this.getAreaChart("Temas", "Temas mais usados do top 10");
        line3.getData().add(this.getNumeroJogosSeries(this.numeroEscolhaTemasPorJogador));
        gridPane.add(line3, 0, 1);
    }

    private AreaChart getAreaChart(String labelx, String labely) {

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(labelx);

        final AreaChart<String, Number> lineChart
                = new AreaChart<String, Number>(xAxis, yAxis);

        lineChart.setTitle(labely);

        return lineChart;
    }

    private void createPie() {
        PieChart line = this.getPieChart("Jogadores", "Numero de Jogos top 10", this.getNumeroJogosPieChart(numeroJogosPorJogador));

        gridPane.add(line, 0, 0);
        PieChart line1 = this.getPieChart("Jogadores", "Pontuaçao maxima top 10", this.getNumeroJogosPieChart(this.numeroPontuacaoMaximaPorJogador));
        gridPane.add(line1, 1, 0);

        PieChart line2 = this.getPieChart("Jogadores", "Tempo medio top 10", this.getNumeroJogosPieChart(this.numeroTempoMedioPorJogador));

        gridPane.add(line2, 1, 1);
        PieChart line3 = this.getPieChart("Temas", "Temas mais usados pelo top 10", this.getNumeroJogosPieChart(this.numeroEscolhaTemasPorJogador));
        gridPane.add(line3, 0, 1);
    }

    private ObservableList<PieChart.Data> getNumeroJogosPieChart(HashMap map) {

        ObservableList<PieChart.Data> series = FXCollections.observableArrayList();
        java.util.Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String aux = it.next();
            String intt = map.get(aux) + "";

            series.add(new PieChart.Data(aux, Double.parseDouble(intt)));
        }
        return series;

    }

    private PieChart getPieChart(String labelx, String labely, ObservableList<PieChart.Data> list) {

        final PieChart lineChart
                = new PieChart(list);

        lineChart.setTitle(labely);

        return lineChart;
    }

    private void updateContent() {
        this.gridPane.getChildren().clear();

        if (tipoJogo.getValue().equals("Normal") && tipoPontuacao.getValue().equals("Base")) {
            this.getInfo(TipoJogo.JOGO_NORMAL_BASE);

        } else if (tipoJogo.getValue().equals("Normal") && tipoPontuacao.getValue().equals("Corrida")) {
            this.getInfo(TipoJogo.JOGO_NORMAL_CORRIDA);
        } else if (tipoJogo.getValue().equals("Rápido") && tipoPontuacao.getValue().equals("Base")) {
            this.getInfo(TipoJogo.JOGO_RAPIDO_BASE);
        } else {
            this.getInfo(TipoJogo.JOGO_RAPIDO_CORRIDA);

        }
        this.updateViewContent();

    }

    private void updateViewContent() {
        this.gridPane.getChildren().clear();

        if (this.selected.equals("line")) {
            this.createLine();
        } else if (this.selected.equals("bar")) {
            this.createBar();
        } else if (this.selected.equals("area")) {
            this.createArea();
        } else {
            this.createPie();
        }
    }

}
