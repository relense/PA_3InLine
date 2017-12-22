/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.historico;

import java.io.Serializable;
import java.util.Date;
import model.linhaTres.Iterator;

/**
 * Classe que representa o Historico sobre os vários jogadores
 *
 * @author tiago
 */
public class Historico<E> implements TADHistorico<E>, Serializable {

    private int size;
    private DNode<E> header;
    private int maxSize;

    /**
     *
     * Metodo construtor
     *
     *
     * @param maxSize tamanho maximo do historico
     */
    public Historico(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.header = new DNode<>(null, null, null);
        this.header.setAnterior(header);
        this.header.setProximo(header);
    }

    /**
     *
     * Metodo void que adiciona um elemento ao historico
     *
     *
     * @param element adicionar ao historico
     */
    @Override
    public void add(E element) {
        removeLast();

        DNode<E> aux = new DNode<>(header, header.getProximo(), element);

        header.getProximo().setAnterior(aux);
        header.setProximo(aux);

        size++;

    }

    /**
     *
     * Metodo void que remove o ultimo elemento da historia
     *
     */
    private void removeLast() {
        if (size == maxSize) {
            header.getAnterior().getAnterior().setProximo(header);
            header.setAnterior(header.getAnterior().getAnterior());
        }
    }

    /**
     *
     * Metodo int que devolve o tamanho do historico
     *
     * @return o tamanho
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     *
     * Metodo boolean que retorna true se o historico esta vazio
     *
     *
     * @return true se size = 0
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     *
     * Metodo do tipo genero que devolve um elemento do historico a partir da
     * sua data
     *
     *
     *
     * @param inicio data do primeiro
     * @param fim data do ultimo
     * @return o respecitvo elemento
     */
    @Override
    public E[] getElementsByDate(Date inicio, Date fim) {//rever bem este metodo
        E[] aux = (E[]) (new Object[this.maxSize]);
        DNode intAux = header.getProximo();

        int pos = 0;
        while (intAux != header) {

            if (inicio.before(intAux.getData()) && fim.after(intAux.getData()))
                aux[pos++] = (E) (intAux.getElement());
            intAux = intAux.getProximo();

        }
        return aux;

    }

    public HistoricoInterator getIterator() {
        return new HistoricoInterator();
    }

    /**
     *
     * Classe privada do iterador do historico
     *
     * @param <E>
     */
    private class HistoricoInterator implements Iterator<E> {

        private DNode<E> pos;

        /**
         *
         * Metodo contrutor
         *
         */
        public HistoricoInterator() {
            this.pos = header.getProximo();
        }

        /**
         * Metodo boolean que retorna true se existem elementos no iterador
         *
         * @return true se pos diferente do header
         */
        @Override
        public boolean hasNext() {
            return pos != header;
        }

        /**
         * Metodo que retorna o proximo elemento do iterador
         *
         * @return elemento
         */
        @Override
        public E next() {
            E element = pos.getElement();
            pos = pos.getProximo();
            return element;
        }

    }

    private class DNode<E> implements Serializable {

        private DNode<E> anterior, proximo;
        private E element;
        private final Date data;

        /**
         * Metodo construtor
         *
         * @param anterior elemento anterior ao actual
         * @param proximo elemento a seguir ao actual
         * @param element elemento actual
         */
        public DNode(DNode anterior, DNode proximo, E element) {
            this.anterior = anterior;
            this.proximo = proximo;
            this.element = element;
            this.data = new Date();
        }

        /**
         * Metodo que devolve o elemento anterior
         *
         * @return anterior
         */
        public DNode<E> getAnterior() {
            return anterior;
        }

        /**
         * Metodo que muda para um novo elemento anterior ao actual
         *
         * @param anterior node anterior
         */
        public void setAnterior(DNode anterior) {
            this.anterior = anterior;
        }

        /**
         * Metodo que devolve o proximo elemento
         *
         * @return proximo
         */
        public DNode<E> getProximo() {
            return proximo;
        }

        /**
         * Metodo que muda para um novo elemento o a seguir ao actual
         *
         * @param proximo proximo node
         */
        public void setProximo(DNode proximo) {
            this.proximo = proximo;
        }

        /**
         * Metodo que devolve o elemento
         *
         * @return elemento
         */
        public E getElement() {
            return element;
        }

        public Date getData() {
            return data;
        }

    }
}
