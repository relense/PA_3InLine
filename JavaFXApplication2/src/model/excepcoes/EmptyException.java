/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.excepcoes;

/**
 * Classe excepção para quando algo está vazio
 *
 * @author Tiago
 */
public class EmptyException extends RuntimeException {

    /**
     * Metodo construtor
     */
    public EmptyException() {
        super("Empty Deque");
    }

}
