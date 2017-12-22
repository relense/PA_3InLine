/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jogoFactory;

import model.ranking.JogoRanking;
import model.ranking.Rankings;

/**
 * Class enum para os diferentes tipos de jogos possiveis
 *
 * @author Miguel
 */
public enum TipoJogo {

    JOGO_NORMAL_BASE, JOGO_NORMAL_CORRIDA, JOGO_RAPIDO_BASE, JOGO_RAPIDO_CORRIDA;

    public JogoRanking getRanking() {

        switch (this) {
            case JOGO_NORMAL_BASE:
                return Rankings.getInstance().getRankingJogoNormalPontuacaoBase();

            case JOGO_NORMAL_CORRIDA:
                return Rankings.getInstance().getRankingJogoNormalPontuacaoCorrida();

            case JOGO_RAPIDO_BASE:
                return Rankings.getInstance().getRankingJogoRapidoPontuacaoBase();

            case JOGO_RAPIDO_CORRIDA:
                return Rankings.getInstance().getRankingJogoRapidoPontuacaoCorrida();

            default:
                return null;

        }
    }
}
