/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.logger;

import java.util.StringTokenizer;
import model.jogo.Jogo;

/**
 *´Class que vai ser um observer das jogadas feitas no jogo
 * @author tiago
 */
public class JogadaObserver implements Observer {
    /**
     * Método que vai fazer update ao logger consoante se houver uma jogada
     * @param o
     * @param args 
     */
    @Override
    public void update(Observable o, Object args) {
        StringTokenizer st = new StringTokenizer((String) args, ":");

        if ((st.nextElement()).equals("Jogada")) {

            Jogo j = (Jogo) o;

            Logger.getInstance().addLogger("Jogo " + j.getID() + " fez uma jogada: " + st.nextToken());

        }
    }
}
