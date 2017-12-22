/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.logger;

import model.jogo.Jogo;

/**
 * Classe observer que vai servir para guadar e observar as ações de do undo
 *
 * @author tiago
 */
public class UndoObserver implements Observer {

    /**
     * Método que vai fazer update ao logger quando for feita a operação de undo
     *
     * @param o
     * @param args
     */
    @Override
    public void update(Observable o, Object args) {
        if (((String) args).equals("Undo")) {

            Jogo j = (Jogo) o;

            Logger.getInstance().addLogger("Jogo " + j.getID() + " realizou undo");

        }
    }

}
