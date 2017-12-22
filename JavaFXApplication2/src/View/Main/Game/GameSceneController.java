/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Main.Game;

import View.Main.ControlledScreen;
import View.Main.FactoryPokemon.FactoryPokemon;
import View.Main.Halloween.FactoryHalloween;
import View.Main.JavaFXApplication2;
import View.Main.ScreensController;
import View.Main.Smileys.FactorySmileys;
import View.Main.SouthPark.FactorySouthPark;
import View.Main.War.FactoryWar;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import mode.tema.TemaFactory;
import model.jogador.Jogador;
import model.jogador.Jogadores;
import model.jogo.Jogo;
import model.jogo.LinhaJogo;
import model.jogoFactory.FactoryJogo;
import model.jogoFactory.TipoJogo;

import model.peca.Peca;
import model.peca.PecaFactory;
import model.peca.PecaGenerator;
import model.undo.JogoCareTaker;

/**
 * FXML Controller class
 *
 * @author tiago
 */
public class GameSceneController implements Initializable, ControlledScreen {

    private Jogo jogo;
    private Jogador jogador;
    private ScreensController parent;
    private boolean clicked = false;
    private PecaFactory factory;
    private JogoCareTaker jogoTaker = new JogoCareTaker();
    private TemaFactory tema;

    @FXML
    private VBox VBoxProximasPecas;

    @FXML
    private HBox VBoxStats;

    @FXML
    private HBox hBoxLinha7;

    @FXML
    private ComboBox<String> comboTipoJogo;

    @FXML
    private TextField textNomeJogador;

    @FXML
    private Text playerName;

    @FXML
    private VBox VboxInicio;

    @FXML
    private VBox vBoxUndo;

    @FXML
    private StackPane StackPaneCriarJogo;

    @FXML
    private ComboBox<String> comboTema;

    @FXML
    private Button homeButton;

    @FXML
    private Text score;

    @FXML
    private VBox VboxTabuleiro;

    @FXML
    private VBox vBoxFimJogo;

    @FXML
    private Button BotaoValidar;

    @FXML
    private HBox hBoxLinha3;

    @FXML
    private HBox hBoxLinha4;

    @FXML
    private HBox hBoxLinha5;

    @FXML
    private HBox hBoxLinha6;

    @FXML
    private Text time;

    @FXML
    private ComboBox<String> comboTipoPontuação;

    @FXML
    private HBox hBoxLinha1;

    @FXML
    private Button undoButton;

    @FXML
    private HBox hBoxLinha2;

    @FXML
    private Text txt2;

    @FXML
    private Text txt1;

    @FXML
    void botaoValidarClick(ActionEvent event) {

        System.out.println("nome jogador a jogar " + this.textNomeJogador.getText());
        
        tema = new TemaFactory();
         factory = tema.getTema(comboTema.getValue());

        this.jogador = Jogadores.getInstance().getJogador(this.textNomeJogador.getText());

        System.out.println(jogador.obterEstatisticas(TipoJogo.JOGO_NORMAL_BASE).getNumeroJogos());
        if (comboTipoJogo.getValue().equals("Normal") && comboTipoPontuação.getValue().equals("Base")) {
            this.jogo = new FactoryJogo().obterJogo(factory.getTema(), TipoJogo.JOGO_NORMAL_BASE, new PecaGenerator());
        } else if (comboTipoJogo.getValue().equals("Normal") && comboTipoPontuação.getValue().equals("Corrida")) {
            this.jogo = new FactoryJogo().obterJogo(factory.getTema(), TipoJogo.JOGO_NORMAL_CORRIDA, new PecaGenerator());
        } else if (comboTipoJogo.getValue().equals("Rápido") && comboTipoPontuação.getValue().equals("Base")) {
            this.jogo = new FactoryJogo().obterJogo(factory.getTema(), TipoJogo.JOGO_RAPIDO_BASE, new PecaGenerator());
        } else {
            this.jogo = new FactoryJogo().obterJogo(factory.getTema(), TipoJogo.JOGO_RAPIDO_CORRIDA, new PecaGenerator());
        }
        this.parent.setCSS(factory.getCSS());
        this.VboxInicio.setVisible(false);
        this.VboxTabuleiro.setVisible(true);
        this.vBoxUndo.setVisible(true);
        this.VBoxStats.setVisible(true);
        this.VBoxProximasPecas.setVisible(true);

        gerarTabuleiro();
        this.undoButton.setOnMouseClicked(e -> {
            this.jogoTaker.restoreState(jogo);
            this.gerarTabuleiro();

        });
         this.jogo.start();
        Task task = new Task<Void>() {
            @Override
            public Void call() {
               long tempoInicial = System.currentTimeMillis();
                while (true) {
                Text text= getTimeText();
                text.setText(""+(System.currentTimeMillis()-tempoInicial)/1000 +"s");
                score.setText(""+jogo.obterPontuacao());
                }

            }
        };

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();

    }
    
    private Text getTimeText(){
        return time;
    }

    @FXML
    void novoJogo(ActionEvent event) {
        if (!clicked) {
            clicked = true;
            parent.unloadScreen(JavaFXApplication2.PlayID);
            parent.loadScreen(JavaFXApplication2.PlayID, JavaFXApplication2.PlayFILE);
            parent.setScreen(JavaFXApplication2.PlayID);

        }
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
        this.VBoxStats.setId("hbox");
        this.score.setId("txt");
        this.time.setId("txt");
        this.playerName.setId("txt");
        this.txt1.setId("txt");
        this.txt2.setId("txt");
        this.VBoxStats.setStyle("-fx-background-color:white; -fx-opacity:0.4");

        ObservableList<String> optionsTipoJogo = FXCollections.observableArrayList(
                "Normal",
                "Rápido"
        );
        comboTipoJogo.setItems(optionsTipoJogo);
        ObservableList<String> optionsTipoPontuacao = FXCollections.observableArrayList(
                "Base",
                "Corrida"
        );
        comboTipoPontuação.setItems(optionsTipoPontuacao);

        ObservableList<String> optionsTema = FXCollections.observableArrayList(
                "War",
                "Halloween",
                "Smileys",
                "Pokemon",
                "SouthPark"
        );
        comboTema.setItems(optionsTema);

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        parent = screenPage;
    }

    //Gerar peças para o tabuleiro
    public void gerarTabuleiro() {
        if (!this.jogo.acabouJogo()) {
            for (int i = 0; i < 7; i++) {

                final HBox hbox = obterHbox(i);

                updateHbox(i, hbox);
               
            }
            final StackPane esquerda = new StackPane();
            esquerda.getChildren().add(factory.getPeca(this.jogo.getPecaGestor().getNextPecaLeft().toString()));

            final StackPane direita = new StackPane();
            direita.getChildren().add(factory.getPeca(this.jogo.getPecaGestor().getNextPecaRight().toString()));
            HBox h = new HBox();
            this.VBoxProximasPecas.getChildren().clear();
            h.getChildren().addAll(esquerda, direita);
            VBox helper = new VBox();

            helper.setStyle("-fx-background-color:white; -fx-opacity:0.4");
            Text txt = new Text("Proximas peças");
            helper.getChildren().addAll(txt, h);
            this.VBoxProximasPecas.getChildren().addAll(helper);
            this.playerName.setText(this.jogador.getNome());
            this.score.setText(this.jogo.obterPontuacao() + "");

        } else {

            this.jogador.addHistorico(jogo);
            this.jogo.inserirNoRanking(jogador);
            this.VboxTabuleiro.setVisible(false);
            this.vBoxUndo.setVisible(false);
            this.VBoxStats.setVisible(false);
            this.VBoxProximasPecas.setVisible(false);
            this.vBoxFimJogo.setVisible(true);
            this.parent.setCSS("Game/game.css");

        }
    }

    private HBox obterHbox(int i) {
        switch (i) {
            case 0:
                return hBoxLinha1;

            case 1:
                return hBoxLinha2;

            case 2:
                return hBoxLinha3;

            case 3:
                return hBoxLinha4;

            case 4:
                return hBoxLinha5;

            case 5:
                return hBoxLinha6;

            case 6:
                return hBoxLinha7;

            default:
                return null;
        }

    }

    private void updateHbox(int i, HBox hbox) {

        hbox.getChildren().clear();

        Iterator it = jogo.interatorLinha(i);
        Rectangle before = null, after = null;
        int j = 0;
        while (it.hasNext()) {

            Peca peca = (Peca) it.next();

            StackPane stack = new StackPane();
            if (peca != null) {
                ImageView imagem = factory.getPeca(peca.toString());

                stack.getChildren().add(imagem);
            } else {

                Rectangle rec = new Rectangle(50.0, 50.0);
                rec.setFill(Color.TRANSPARENT);

                if (j <= 3 && peca == null) {
                    before = rec;
                }

                if (after == null && j >= 4) {
                    after = rec;
                }

                stack.getChildren().add(rec);
            }

            stack.setMinSize(50.0, 50.0);
            hbox.getChildren().add(stack);
            hbox.setAlignment(Pos.CENTER);
            j++;
        }
        final ImageView esquerda = (factory.getPeca(this.jogo.getPecaGestor().getPecaLeft().toString()));

        final ImageView direita = (factory.getPeca(this.jogo.getPecaGestor().getPecaRight().toString()));

        hbox.setOnMouseEntered(e -> {
            hbox.getChildren().add(0, esquerda);
            hbox.getChildren().add(direita);
        });
        hbox.setOnMouseExited(e -> {
            hbox.getChildren().remove(esquerda);
            hbox.getChildren().remove(direita);
        });

        final Rectangle aux = before;
        final Rectangle aux2 = after;

        hbox.setOnMouseClicked(e -> {
            hbox.setOnMouseExited(g -> {
            });

            clearBoxes();

            movimentoEsquerda(aux, esquerda, i).play();
            Timeline time = movimentoDireita(aux2, direita, i);

            time.setOnFinished(a -> {
                this.jogoTaker.saveState(jogo);
                this.jogo.jogada(i + 1);
                gerarTabuleiro();

            });

            time.play();

        });

    }

    private Timeline movimentoEsquerda(Rectangle rectangle, ImageView pane, int i) {

        System.out.println(rectangle.getX());

        LinhaJogo ln = jogo.getLinha(i);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(
                pane.translateXProperty(),
                rectangle.getWidth() * (5 - ln.getNumeroPecasEsquerda())
        );

        KeyFrame fram = new KeyFrame(Duration.seconds(1), keyValue);

        timeline.getKeyFrames().add(fram);

        return timeline;
    }

    private Timeline movimentoDireita(Rectangle rectangle, ImageView pane, int i) {

        System.out.println(rectangle.getX());

        LinhaJogo ln = jogo.getLinha(i);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(
                pane.translateXProperty(),
                -(rectangle.getWidth() * (5 - ln.getNumeroPecasDireita()))
        );

        KeyFrame fram = new KeyFrame(Duration.seconds(1), keyValue);

        timeline.getKeyFrames().add(fram);

        return timeline;
    }

    private void clearBoxes() {
        this.hBoxLinha1.setOnMouseClicked(e -> {
        });
        this.hBoxLinha2.setOnMouseClicked(e -> {
        });
        this.hBoxLinha3.setOnMouseClicked(e -> {
        });
        this.hBoxLinha4.setOnMouseClicked(e -> {
        });
        this.hBoxLinha5.setOnMouseClicked(e -> {
        });
        this.hBoxLinha6.setOnMouseClicked(e -> {
        });
        this.hBoxLinha7.setOnMouseClicked(e -> {
        });

        this.hBoxLinha1.setOnMouseEntered(e -> {
        });
        this.hBoxLinha2.setOnMouseEntered(e -> {
        });
        this.hBoxLinha3.setOnMouseEntered(e -> {
        });
        this.hBoxLinha4.setOnMouseEntered(e -> {
        });
        this.hBoxLinha5.setOnMouseEntered(e -> {
        });
        this.hBoxLinha6.setOnMouseEntered(e -> {
        });
        this.hBoxLinha7.setOnMouseEntered(e -> {
        });

        this.hBoxLinha1.setOnMouseReleased(e -> {
        });
        this.hBoxLinha2.setOnMouseReleased(e -> {
        });
        this.hBoxLinha3.setOnMouseReleased(e -> {
        });
        this.hBoxLinha4.setOnMouseReleased(e -> {
        });
        this.hBoxLinha5.setOnMouseReleased(e -> {
        });
        this.hBoxLinha6.setOnMouseReleased(e -> {
        });
        this.hBoxLinha7.setOnMouseReleased(e -> {
        });

    }
}
