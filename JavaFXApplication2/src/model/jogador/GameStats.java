package model.jogador;


import java.io.Serializable;
import java.util.HashMap;
import model.jogo.Jogo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class que irá guardar as estatisticas dos diferentes jogos
 *
 * @author tiago
 */
public class GameStats implements Serializable {

    private int numeroJogos;
    private double tempoMedio;
    private int pontuacaoMaxima;
    private HashMap<String, Integer> temaEscolhido;

    /**
     * Método construtor
     */
    public GameStats() {
        this.numeroJogos = 0;
        this.tempoMedio = 0;
        this.pontuacaoMaxima = 0;
        this.temaEscolhido = new HashMap();
    }

    /**
     * Método adicionar vai ser o método que vai fazer as medias de tudo
     *
     * @param jogo Jogo
     */
    void adicionar(Jogo jogo) {
        this.numeroJogos++;
        tempoMedio = (numeroJogos * tempoMedio + jogo.obterDuracao()) / (numeroJogos);
        pontuacaoMaxima = Math.max(pontuacaoMaxima, jogo.obterPontuacao());
        if (temaEscolhido.containsKey(jogo.obterTemaSelecionado()))
            temaEscolhido.replace(jogo.obterTemaSelecionado(), temaEscolhido.get(jogo.obterTemaSelecionado()) + 1);
        else
            this.temaEscolhido.put(jogo.obterTemaSelecionado(), 1);
        
        System.out.println("adicionou"+numeroJogos);
    }

    /**
     * Método que devolve o numero total de jogos feitos
     *
     * @return numeroJogos
     */
    public int getNumeroJogos() {
        
        System.out.println("numero jogos"+numeroJogos);
        return numeroJogos;
        
        
    }

    /**
     * Método que devolve o tempo médio de duração de cada jogo
     *
     * @return tempoMedio
     */
    public double getTempoMedio() {
        return tempoMedio;
    }

    /**
     * Método que devolve a pontuação maxima
     *
     * @return pontuacaoMaxima
     */
    public int getPontuacaoMaxima() {
        return pontuacaoMaxima;
    }

    /**
     * Método que devolve o tema escolhido
     *
     * @return temaEscolhido
     */
    public HashMap<String, Integer> getTemaEscolhido() {
        return temaEscolhido;
    }

}
