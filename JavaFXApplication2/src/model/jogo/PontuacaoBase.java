
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jogo;

import model.jogo.EstrategiaTipoPontuacao;
import java.io.Serializable;

/**
 * Class que respresenta o tipo de pontuacao base
 *
 * @author Miguel
 */
public class PontuacaoBase implements EstrategiaTipoPontuacao, Serializable {

    /**
     * Método que calcula a pontuacao Cada peça destruida equivale a 20 pontos
     *
     * @param pecasDestruidas pecasDestruidas
     * @param tempo tepo de jogo
     * @return pontuacao
     */
    @Override
    public int pontuacao(int pecasDestruidas, long tempo) {

        return pecasDestruidas * 20 ;

    }

}
