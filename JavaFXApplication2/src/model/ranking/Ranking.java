/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ranking;

import java.io.Serializable;
import model.linhaTres.Iterator;

/**
 * Classe responsavel por representar o comportamento de um Ranking
 *
 * @author tiago
 */
public class Ranking<E> implements TADRanking<E>, Serializable {

    private RankingNode header, tail;
    private int size;
    private StrategyComparator comparador;

    /**
     * Metodo construtor que recebe como parametros o tipo de comparação a
     * adotar para a comparaçao e ordenação de elementos
     *
     * @param c padrao estrategia a adotar para comparar
     */
    public Ranking(StrategyComparator c) {

        size = 0;
        header = new RankingNode(null, null, null);
        tail = new RankingNode(null, null, null);
        tail.previous = header;
        header.next = tail;
        this.comparador = c;
    }

    /**
     * Metodo que devolve o numero de elementos no ranking
     *
     * @return numero de elementos
     */
    @Override
    public int size() {
        return size;

    }

    /**
     * Metodo que verifica que a lista esta vazia
     *
     * @return (true) se sim (false) caso contrario
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Metodo que adiciona um elemento no ranking e caso ele exista duas vezes
     * remove a ultima aparicao
     *
     *
     * @param elem elemento a meter
     */
    @Override
    public void add(E elem) {

        RankingNode position = nodeAtRank(elem);
        RankingNode previous = position.previous;
        RankingNode newNode = new RankingNode(elem, previous, position);
        previous.next = newNode;
        position.previous = newNode;
        size++;

        removerChecker(newNode);//verificar se este existe 2 vezes e se sim remove a ultima aparicao
        Rankings.getInstance().gravar();
    }

    /**
     * Metodo que devolve o Node a descer de posicao segundo o criterio de
     * comparacao
     *
     * @param E elemento a comparar (que vai ser adicionado)
     * @return Node no rank a descer no ranking
     */
    private RankingNode nodeAtRank(E elem) {

        RankingNode posicao = header.next;
        // procurar o no no rank r;
        while (posicao != tail) {
            if (this.comparador.compareTo(elem, posicao.element) < 0)
                return posicao;
            posicao = posicao.next;
        }
        return posicao;

    }

    /**
     * Metodo que verifica se o elemento aparece duas ou mais vezes e se sim
     * remove as ultimas aparicoes
     *
     * @param newNode Node com o novo elemento
     */
    private void removerChecker(RankingNode newNode) {
        int numeroDeAparicoes = 0;
        RankingNode aux = header.next;

        while (aux != tail) {

            if (aux.element.equals(newNode.element))//verifica se aparece

                numeroDeAparicoes++;//se sim incrementa
            if (numeroDeAparicoes == 2) {//verifica se ja apareceu duas vezes, se sim esta é a segunda logo remove-se este Node
                aux.previous.next = aux.next;
                aux.next.previous = aux.previous;
                this.size--;

                break;
            }
            aux = aux.next;

        }

    }

    /**
     * Meotod que chama o iterador do ranking
     *
     * @return a lista
     */
    public Iterator getRankingIterator() {
        return new RankingIterator();
    }

    /**
     * Classe privada que representa um elemento do rank
     */
    private class RankingNode implements Serializable {

        E element;
        RankingNode previous, next;

        /**
         * metodo construtor
         *
         * @param element dado para o novo node
         * @param previous node anterior
         * @param next node a seguir
         */
        public RankingNode(E element, RankingNode previous, RankingNode next) {
            this.element = element;
            this.previous = previous;
            this.next = next;

        }

    }

    /**
     * Classe privada iterator
     */
    private class RankingIterator implements Iterator<E> {

        private RankingNode pos;

        /**
         * metodo construtor
         */
        public RankingIterator() {
            this.pos = header.next;
        }

        /**
         * metodo que mostrs o elemento atual
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            return pos != tail;
        }

        /**
         * Metodo que retorna o proximo elemento
         *
         * @return elemento
         */
        @Override
        public E next() {
            E element = pos.element;
            pos = pos.next;
            return element;
        }
    }

}
