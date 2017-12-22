/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Main.Creditos;

import View.Main.ControlledScreen;
import View.Main.JavaFXApplication2;
import View.Main.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Miguel
 */
public class FXMLCreditsController implements Initializable, ControlledScreen {

    private ScreensController parent;
    private boolean clicked = false;

    @FXML
    private Button backButton;

    @FXML
    private Label labelCredits;

    @FXML
    private TextArea textArea;
    
    @FXML
    private  BorderPane pane;
    @FXML
    void onClickAction(ActionEvent event) {

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
     pane.getStylesheets().add(getClass().getResource("credits.css").toExternalForm());

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

        this.parent = screenPage;
    }

}
