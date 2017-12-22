/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jogoFactory;

import model.jogo.Jogo;
import model.jogo.JogoNormal;
import model.jogo.JogoRapido;
import model.jogo.PontuacaoBase;
import model.jogo.PontuacaoCorrida;
import model.peca.PecaGenerator;
import model.ranking.Rankings;

/**
 * Classe que representa a fabrica do jogo
 *
 * @author Miguel
 */
public class FactoryJogo {

    /**
     * Método que inicializa o jogo com base no tipo de jogo
     *
     * @param tipoJogo tipo de jogo
     * @param fabricaPecas tipo de pecas
     * @return jogo
     */
    public Jogo obterJogo(String tema, TipoJogo tipoJogo, PecaGenerator fabricaPecas) {
        Jogo jogo = null;
        if (tipoJogo == TipoJogo.JOGO_NORMAL_BASE){
            jogo = new JogoNormal(tema, fabricaPecas, new PontuacaoBase(),
                    Rankings.getInstance().getRankingJogoNormalPontuacaoBase());
       
        }else if (tipoJogo == TipoJogo.JOGO_NORMAL_CORRIDA)
            jogo = new JogoNormal(tema, fabricaPecas, new PontuacaoCorrida(),
                    Rankings.getInstance().getRankingJogoNormalPontuacaoCorrida());
        else if (tipoJogo == TipoJogo.JOGO_RAPIDO_BASE)
            jogo = new JogoRapido(tema, fabricaPecas, new PontuacaoBase(),
                    Rankings.getInstance().getRankingJogoRapidoPontuacaoCorrida());
        else
            jogo = new JogoRapido(tema, fabricaPecas, new PontuacaoCorrida(),
                    Rankings.getInstance().getRankingJogoRapidoPontuacaoCorrida());

        return jogo;
    }

}
