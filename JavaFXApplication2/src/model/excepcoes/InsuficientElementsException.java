/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.excepcoes;

/**
 * Classe que representa uma excepção caso não haja elementos suficientes
 *
 * @author Miguel
 */
public class InsuficientElementsException extends RuntimeException {

    /**
     * Metodo construtor
     */
    public InsuficientElementsException() {
        super("Elementos insuficientes");
    }

}
