/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ranking.Rankings;

/**
 *
 * @author tiago
 */
public class JavaFXApplication2 extends Application {

    public static String Screen1ID = "Screen1";
    public static String Screen1FILE = "main/FXMLMain.fxml";
    public static String Screen2ID = "Screen2";
    public static String Screen2FILE = "entry/FXML.fxml";
    public static String RankingScreenID = "Ranking";
    public static String RankinScreenFILE = "Ranking/RankingScene.fxml";
    public static String PlayID = "Game";
    public static String PlayFILE = "Game/gameScene.fxml";
    public static String StatisticsID = "Stats";
    public static String StatisticsFILE = "statistics/FXMLEstatisticas.fxml";
    public static String HistoricoID = "Historico";
    public static String HistoricoFILE = "Historico/FXMLHistorico.fxml";
    public static String CreditosID = "Creditos";
    public static String CreditosFILE = "Creditos/FXMLCredits.fxml";

    @Override
    public void start(Stage stage) throws Exception {

        System.out.println(Rankings.getInstance().getRankingJogoNormalPontuacaoBase().getIterator().next());

        ScreensController content = new ScreensController();
        content.loadScreen(Screen1ID, Screen1FILE);
        content.loadScreen(Screen2ID, Screen2FILE);
        //   content.loadScreen(RankingScreenID, RankinScreenFILE);
        // content.loadScreen(PlayID, PlayFILE);
        //content.loadScreen(StatisticsID, StatisticsFILE);
        //    Parent root = FXMLLoader.load(getClass().getResource("FXMLMain.fxml"));

        content.setScreen(Screen1ID);
        content.setScreen(Screen2ID);
        Scene scene = new Scene(content);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setScene(scene);

        stage.show();
        content.unloadScreen(Screen2ID);
        content.loadScreen(Screen2ID, Screen2FILE);

        Task task = new Task<Void>() {
            @Override
            public Void call() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JavaFXApplication2.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(() -> {

                    content.unloadScreen(Screen1ID);
                    content.loadScreen(Screen1ID, Screen1FILE);
                    content.setScreen(Screen1ID);

                });

                return null;
            }
        };

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
//

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
