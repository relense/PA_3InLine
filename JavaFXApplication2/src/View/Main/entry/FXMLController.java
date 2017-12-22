package View.Main.entry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import View.Main.ControlledScreen;
import View.Main.JavaFXApplication2;
import View.Main.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author tiago
 */
public class FXMLController implements Initializable, ControlledScreen {

    public boolean clicked = false;

    @FXML
    VBox vbox;
 
    ScreensController parent;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ImageView img = new ImageView(new Image(getClass().getResourceAsStream("ips.png")));
        img.setOnMouseClicked(e -> {
            if (!clicked) {
                clicked = true;
                parent.unloadScreen(JavaFXApplication2.Screen1ID);
                parent.loadScreen(JavaFXApplication2.Screen1ID, JavaFXApplication2.Screen1FILE);
                parent.setScreen(JavaFXApplication2.Screen1ID);
            }

        });
        
        Text text = new Text("IPS PRODUCTIONS");
        text.setFill(Color.DARKGRAY);
        text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 30));
       
        vbox.getChildren().addAll(img, text);
        

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.parent = screenPage;
    }

}
