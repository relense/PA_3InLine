/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jogo;

/**
 * Interface que representa a Estrategia para o tipo de pontuação de cada jogo
 *
 * @author Miguel
 */
public interface EstrategiaTipoPontuacao {

    /**
     * Método para calcular a pontuacao
     *
     * @param pecasDestruidas numero de pecas destruidas
     * @param tempo tempo de jogo
     * @return pontuacao
     */
    public int pontuacao(int pecasDestruidas, long tempo);

}
