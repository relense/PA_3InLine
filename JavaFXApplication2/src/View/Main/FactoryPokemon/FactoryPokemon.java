/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Main.FactoryPokemon;

import View.Main.MusicPlayer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.peca.PecaFactory;

/**
 *
 * @author tiago
 */
public class FactoryPokemon implements PecaFactory {
    @Override
    public ImageView getPeca(String peca) {
        switch (peca) {
            case "1":
                ImageView img = new ImageView(new Image(getClass().getResourceAsStream("Imagem1.gif")));
                return img;
            case "2":
                ImageView img2 = new ImageView(new Image(getClass().getResourceAsStream("Imagem2.gif")));
                return img2;
            case "3":
                ImageView img3 = new ImageView(new Image(getClass().getResourceAsStream("Imagem3.gif")));
                return img3;
            case "4":
                ImageView img4 = new ImageView(new Image(getClass().getResourceAsStream("Imagem4.gif")));
                return img4;
            case "5":
                ImageView img5 = new ImageView(new Image(getClass().getResourceAsStream("Imagem5.gif")));
                
                return img5;
            default:
                System.out.println("dasd");
                return  new ImageView(new Image(getClass().getResourceAsStream("invisible.png")));
                
        }

    }

    @Override
    public String getTema() {
        return "Pokemon";

    }
    @Override
    public String getCSS(){
        return "FactoryPokemon/pokemon.css";
    }

    @Override
    public MusicPlayer getSomDestruicao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
