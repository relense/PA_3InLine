/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.excepcoes;

/**
 * Classe excepcao para quando algo está cheio
 *
 * @author tiago
 */
public class FullException extends RuntimeException {

    /**
     * Metodo construtor
     */
    public FullException() {
        super("Capacidade cheia.");
    }
}
