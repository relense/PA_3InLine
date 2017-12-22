/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Main.main;

import View.Main.ControlledScreen;
import View.Main.JavaFXApplication2;
import View.Main.MusicPlayer;
import View.Main.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author tiago
 */
public class FXMLMainController implements Initializable, ControlledScreen {

    ImageView soundImg;
    ImageView musicImg;
    private static boolean firsAppear = true;
    private static boolean soundOn = true;
    private static boolean musicOn = true;
    private ScreensController parent;
    boolean clicked = false;
    
    @FXML
    private BorderPane pane;

    private MusicPlayer player;
    private MusicPlayer player2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("oi");
        player = new MusicPlayer();
        player2 = new MusicPlayer();
      
        musicIcon();

        MenuItem exit = new MenuItem("Exit");

        exit.setOnMouseClicked(event -> {

            System.exit(0);
        });

        MenuItem play = new MenuItem("Play");

        play.setOnMouseClicked(event -> {
            if (!clicked) {
                this.goToGameScreen();
                clicked = true;
            }

        });
        MenuItem ranking = new MenuItem("Rankings");

        ranking.setOnMouseClicked(event -> {
            if (!clicked) {
                this.goToScreen3();
                clicked = true;
            }

        });
         MenuItem statistics = new MenuItem("Estatisticas");

        statistics.setOnMouseClicked(event -> {
            if (!clicked) {
                this.goToScreen4();
                clicked = true;
            }

        });
        
          MenuItem historico = new MenuItem("Historico");

        historico.setOnMouseClicked(event -> {
            if (!clicked) {
                this.goToScreen5();
                clicked = true;
            }

        });
           MenuItem creditos = new MenuItem("Creditos");

        creditos.setOnMouseClicked(event -> {
            if (!clicked) {
                this.goToScreen6();
                clicked = true;
            }

        });
        
        MenuBox menu = new MenuBox(play, ranking, historico, statistics,creditos, exit
        );
        menu.setAlignment(Pos.CENTER);
        menu.setPadding(new Insets(0, 20, 0, 100));
        pane.setStyle("-fx-background-image: url('" + getClass().getResource("test.gif").toExternalForm() + "');-fx-background-repeat: no-repeat;-fx-background-size: cover; ");

        HBox hbox = new HBox();
        hbox.getChildren().addAll( musicImg);

        hbox.setAlignment(Pos.CENTER_LEFT);

        hbox.setPadding(new Insets(0, 20, 20, 100));
        pane.setBottom(hbox);
        pane.setLeft(menu);
        if (firsAppear) {
            firsAppear = false;
        } else {
            if (soundOn) {
               // player2.startPlaying("waterfall_in_forest.mp3");
            }
            if (musicOn) {
                player.startPlaying("got.mp3");
            }
        }
    }

    public BorderPane getPane() {
        return pane;
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.parent = screenPage;
    }

    public void goToScreen2() {
        clean();
        parent.unloadScreen(JavaFXApplication2.Screen2ID);
        parent.loadScreen(JavaFXApplication2.Screen2ID, JavaFXApplication2.Screen2FILE);

        this.parent.setScreen(JavaFXApplication2.Screen2ID);
    }

    public void goToGameScreen() {
        clean();
        parent.unloadScreen(JavaFXApplication2.PlayID);
        parent.loadScreen(JavaFXApplication2.PlayID, JavaFXApplication2.PlayFILE);

        this.parent.setScreen(JavaFXApplication2.PlayID);
    }

    public void goToScreen3() {
        clean();
        parent.unloadScreen(JavaFXApplication2.RankingScreenID);
        parent.loadScreen(JavaFXApplication2.RankingScreenID, JavaFXApplication2.RankinScreenFILE);

        this.parent.setScreen(JavaFXApplication2.RankingScreenID);
    }
      public void goToScreen4() {
        clean();
        parent.unloadScreen(JavaFXApplication2.StatisticsID);
        parent.loadScreen(JavaFXApplication2.StatisticsID, JavaFXApplication2.StatisticsFILE);

        this.parent.setScreen(JavaFXApplication2.StatisticsID);
    }
      
       public void goToScreen5() {
        clean();
        parent.unloadScreen(JavaFXApplication2.HistoricoID);
        parent.loadScreen(JavaFXApplication2.HistoricoID, JavaFXApplication2.HistoricoFILE);

        this.parent.setScreen(JavaFXApplication2.HistoricoID);
    }

    private void clean() {

        player2.stop();
        player.stop();

    }

   

       

    private void musicIcon() {
        if (musicOn) {
            musicImg = new ImageView(new Image(getClass().getResourceAsStream("music-on.png")));

        } else {
            musicImg=new ImageView(new Image(getClass().getResourceAsStream("music-off.png")));

        }
        musicImg.setOnMouseClicked(e -> {
            if (musicOn) {
                musicOn = false;
                player.stop();
                musicImg.setImage(new Image(getClass().getResourceAsStream("music-off.png")));

            } else {
                musicOn = true;
                player.startPlaying("got.mp3");
                musicImg.setImage(new Image(getClass().getResourceAsStream("music-on.png")));

            }

        });
    }

    private void goToScreen6() {
     clean();
        parent.unloadScreen(JavaFXApplication2.CreditosID);
        parent.loadScreen(JavaFXApplication2.CreditosID, JavaFXApplication2.CreditosFILE);

        this.parent.setScreen(JavaFXApplication2.CreditosID);
    
    }

}
