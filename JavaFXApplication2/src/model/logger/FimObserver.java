/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.logger;

import model.jogo.Jogo;

/**
 *Classe que vai observar o fim do jogo e guardar
 * @author tiago
 */
public class FimObserver implements Observer {
    /**
     * Método que vai fazer update ao logger quando acabar o jogo
     * @param o
     * @param args 
     */
    @Override
    public void update(Observable o, Object args) {
        if (((String) args).equals("Fim")) {

            Jogo j = (Jogo) o;

            Logger.getInstance().addLogger("Jogo " + j.getID() + " acabou");

        }
    }
}
