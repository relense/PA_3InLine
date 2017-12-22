package model.jogo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import model.peca.PecaGenerator;
import model.ranking.JogoRanking;

/**
 * Classe que representa um jogo rapido e que extende da class jogo
 *
 * @author Miguel
 */
public class JogoRapido extends Jogo {

    /**
     * Métod construtor
     *
     * @param pecaFactory construcao das pecas
     * @param estrategiaTipoPontuacao tipo de estrategia
     * @param rank rank
     */
    public JogoRapido(String tema, PecaGenerator pecaFactory, EstrategiaTipoPontuacao estrategiaTipoPontuacao, JogoRanking rank) {
        super(tema, pecaFactory, estrategiaTipoPontuacao, rank);
        this.jogadas = 0;

    }

    /**
     * Método booleano que indica se já acabou o jogo
     *
     * @return true se a liha estiver cheia ou já tiverem sido feitas 20 jogadas
     * return false se não aconteceu nenhuma
     */
    @Override
    public boolean acabouJogo() {
        if (this.tabuleiro.linhaJogoCheia() || this.jogadas == 20) {
            this.end = System.currentTimeMillis();
            this.notifyObservers("Fim");
            return true;
        } else
            return false;
    }

}
