/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ranking;

import java.io.Serializable;
import model.jogador.Jogador;

/**
 *
 * @author tiago
 */
public class RankingField implements Serializable{
    private String jogador;
    private int pontuacao;

    public RankingField(String jogador, int pontuacao) {
        this.jogador = jogador;
        this.pontuacao = pontuacao;
    }

    public String getJogador() {
        return jogador;
    }

    public int getPontuacao() {
        return pontuacao;
    }
    
    @Override
    public boolean equals(Object e){
        
        return this.jogador.equals(((RankingField)e).jogador);
    }
                

    @Override
    public String toString() {
        return "RankingField{" + "jogador=" + jogador + ", pontuacao=" + pontuacao + '}';
    }
    
    
    
    
}
