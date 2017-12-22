/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Main;

import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 *
 * @author tiago
 */
public class ScreensController extends BorderPane {

    private HashMap<String, Node> screens;

    public ScreensController() {
        super();
        this.screens = new HashMap();

    }

    public void addScreen(String name, Node screen) {
        this.screens.put(name, screen);
    }

    public Node getScreen(String name) {
        return this.screens.get(name);
    }

    public boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenControler = ((ControlledScreen) myLoader.getController());
            myScreenControler.setScreenParent(this);
            addScreen(name, loadScreen);

            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean setScreen(final String name) {
        if (this.screens.get(name) != null) {//screen loaded
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {//mais que uma screen

                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000), (ActionEvent t) -> {
                            getChildren().remove(0);
                            setContent(name);
                            Timeline fadeIn = new Timeline(
                                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                    new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                            fadeIn.play();
                        }, new KeyValue(opacity, 0.0)));
                fade.play();
            } else {
                setOpacity(0.0);

                setContent(name);

                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
                fadeIn.play();

            }
            return true;
        } else {
            System.out.println("Screen not loaded");
            return false;
        }
    }

    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Nao existe screen");
            return false;
        } else
            return true;
    }

    private void setContent(String name) {
        BorderPane scene = (BorderPane) this.screens.get(name);

        this.setTop(scene.getTop());
        this.setLeft(scene.getLeft());
        this.setRight(scene.getRight());
        this.setBottom(scene.getBottom());
        this.setCenter(scene.getCenter());
        this.setStyle(scene.getStyle());
        this.getStylesheets().clear();
        this.getStylesheets().addAll(scene.getStylesheets());
    }
    
    
    public void setCSS(String css){
        System.out.println("css"+css);
        this.getStylesheets().add(getClass().getResource(css).toExternalForm());

    }

}
