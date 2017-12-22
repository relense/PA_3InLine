/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jogo;

import model.jogo.EstrategiaTipoPontuacao;
import java.io.Serializable;

/**
 * Class que representa o tipo de pontuacao de corrida
 *
 * @author Miguel
 */
public class PontuacaoCorrida implements EstrategiaTipoPontuacao, Serializable {

    /**
     * Método int que calcula a pontuacao A pontuacao começa a 1000 e a cada 10
     * segundos desconta 1 ponto e por cada peça destruida soma 10 pontos
     *
     * @param pecasDestruidas pecas destruidas
     * @param tempo tempo de jogo
     * @return pontuacao
     */
    @Override
    public int pontuacao(int pecasDestruidas, long tempo) {

        int pontuacao = 1000;
        //dividimos por 10000 pois é para converter para segundos e depois converter para a pontuacao desncontada por cada 10segundos
        long pontuacaoDescontada = (tempo / 10000);
        pontuacao += (pecasDestruidas * 10);

        return (int) (pontuacao - pontuacaoDescontada);

    }

}
