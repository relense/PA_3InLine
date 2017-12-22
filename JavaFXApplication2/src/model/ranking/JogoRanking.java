/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ranking;

import java.io.Serializable;
import model.linhaTres.Iterator;

/**
 *Classe com visto a ser usado para notificar o ranking e para guardar e definir 
 * a estrategia de comparação do ranking
 * @author tiago
 */
public class JogoRanking<E> implements TADRanking , Serializable {

    Ranking ranking;
    /**
     * Método construtor
     */
    public JogoRanking() {
        this.ranking = new Ranking(new JogadorComparator());
    }
    /**
     * Método para saber o tamanho
     * @return size
     */
    @Override
    public int size() {
        return this.ranking.size();
    }
    /**
     * Método para saber está vazio
     * @return true se estiver vazio
     */
    @Override   
    public boolean isEmpty() {
        return this.ranking.isEmpty();
    }
    /**
     * Método para dicionar ao ranking 
     * @param elem elemento a adicionar
     */
    @Override
    public void add(Object elem) {
        this.ranking.add(elem);
        Rankings.getInstance().gravar();
    }
    /**
     * Método que devolve o iterador
     * @return iterador
     */
    public Iterator getIterator() {
        return this.ranking.getRankingIterator();
    }
}
