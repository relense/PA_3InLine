/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.logger;

/**
 *Interface que vai servir de base para os observadores
 * @author tiago
 */
interface Observer {
    /**
     * Método que irá ser implementado para os diferentes observers fazerem 
     * os diferentes updates com base no que estão a observar
     * @param o o
     * @param args args
     */
    public void update(Observable o, Object args);
}
