/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.logger;

import model.jogo.Jogo;

/**
 *Classe que vai observar o inicio do jogo e guardar
 * @author tiago
 */
public class InicioObserver implements Observer {
    /**
     * Método que vai fazer update ao logger quando iniciar o jogo
     * @param o
     * @param args 
     */
    @Override
    public void update(Observable o, Object args) {
        System.out.println(args);
        if (((String) args).equals("Inicio")) {

            Jogo j = (Jogo) o;

            Logger.getInstance().addLogger("Jogo " + j.getID() + " começou");

        }
    }

}
