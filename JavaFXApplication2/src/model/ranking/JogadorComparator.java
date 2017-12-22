package model.ranking;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Classe que vai comparar os diferentes jogadores e as suas pontuações para um
 * ranking especifico
 *
 * @author tiago
 */
public class JogadorComparator extends StrategyComparator {

    @Override
    public int compareTo(Object e1, Object e2) {

        RankingField j1 = (RankingField) e1;
        RankingField j2 = (RankingField) e2;
        return j2.getPontuacao() - j1.getPontuacao();

    }

}
