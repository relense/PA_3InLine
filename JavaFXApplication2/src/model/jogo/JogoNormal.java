package model.jogo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
;

import model.peca.PecaGenerator;
import model.ranking.JogoRanking;
import model.ranking.RankingField;

/**
 * Class que vai representar um jogo Normal e que extende da class Jogo
 *
 * @author Miguel
 */
public class JogoNormal extends Jogo {

    /**
     * Método construtor
     *
     * @param pecaFactory construcao das pecas
     * @param estrategiaTipoPontuacao tipo de estrategia
     * @param rank rank
     */
    public JogoNormal(String tema,PecaGenerator pecaFactory, EstrategiaTipoPontuacao estrategiaTipoPontuacao, JogoRanking rank) {
        super(tema, pecaFactory, estrategiaTipoPontuacao, rank);
    }

    /**
     * Métdo booleano que indica quando acabou o jogo
     *
     * @return true se linha estiver cheia, false se não estiver cheia
     */
    @Override
    public boolean acabouJogo() {

        if (this.tabuleiro.linhaJogoCheia()) {
            this.end = System.currentTimeMillis();
            this.notifyObservers("Fim");
            return true;
            
        } else
            return false;
    }

}
