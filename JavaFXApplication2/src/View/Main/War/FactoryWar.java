/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Main.War;

import View.Main.MusicPlayer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.peca.PecaFactory;

/**
 *
 * @author Miguel
 */
public class FactoryWar implements PecaFactory {

    private static final String nome = "War";

    @Override
    public ImageView getPeca(String peca) {
        switch (peca) {
            case "1":
                ImageView img = new ImageView(new Image(getClass().getResourceAsStream("war1.png")));
                return img;
            case "2":
                ImageView img2 = new ImageView(new Image(getClass().getResourceAsStream("war2.png")));
                return img2;
            case "3":
                ImageView img3 = new ImageView(new Image(getClass().getResourceAsStream("war3.png")));
                return img3;
            case "4":
                ImageView img4 = new ImageView(new Image(getClass().getResourceAsStream("war4.png")));
                return img4;
            case "5":
                ImageView img5 = new ImageView(new Image(getClass().getResourceAsStream("war5.png")));
                return img5;
            default:
                return null;
        }

    }

    @Override
    public String getTema() {
        return nome;

    }

    @Override
    public MusicPlayer getSomDestruicao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCSS() {
    return "War/war.css";
    }

}

    
