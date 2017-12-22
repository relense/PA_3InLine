/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.peca;

import View.Main.MusicPlayer;
import javafx.scene.image.ImageView;

/**
 *
 * @author Miguel
 */
public interface PecaFactory {
     
    public ImageView getPeca(String peca);
    
    public String getTema();
    
    public MusicPlayer getSomDestruicao();

    public String getCSS();
    
    
}
