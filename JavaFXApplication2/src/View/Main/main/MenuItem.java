/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Main.main;

import View.Main.MusicPlayer;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author tiago
 */
public class MenuItem extends StackPane {

    public MenuItem(String name) {
       // Media media = new Media("menu_move_1.mp3");
        //MediaPlayer mp= new MediaPlayer(media);
         MusicPlayer p = new MusicPlayer();
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[]{
            new Stop(0, Color.ROSYBROWN),
            new Stop(0.3, Color.BLACK),
            new Stop(0.7, Color.BLACK),
            new Stop(1, Color.ROSYBROWN)

        });

        Rectangle rectangle = new Rectangle(400, 60);
        rectangle.setOpacity(0.6);

        Text text = new Text(name);
        text.setFill(Color.DARKGREY);
        text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 44));

        getChildren().addAll(rectangle, text);

        setOnMouseEntered(event -> {
            rectangle.setFill(gradient);
            text.setFill(Color.WHITE);
          // mp.play();
           p.startPlaying("menu_move_1.mp3");
            
        });
        setOnMouseExited(event -> {
            rectangle.setFill(Color.BLACK);
            text.setFill(Color.DARKGREY);
            p.stop();
          //  mp.stop();
        });
        setOnMousePressed(event -> {
            rectangle.setFill(Color.ROSYBROWN);
            p.startPlaying("menu_select_1.mp3");
        });
        setOnMouseReleased(event -> {
            rectangle.setFill(gradient);

        });
    }
}
