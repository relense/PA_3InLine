/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Main.main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author tiago
 */
public class MenuBox extends VBox {

    public MenuBox(MenuItem... items) {

        ImageView img = new ImageView(new Image(getClass().getResourceAsStream("trest.gif")));

        getChildren().addAll(img,createSeparator());

        for (MenuItem item : items) {
            getChildren().addAll(item, createSeparator());
        }

    }

    private Line createSeparator() {
        Line line = new Line();
        line.setEndX(400);
        line.setStroke(Color.DARKGREY);
        return line;
    }
}
