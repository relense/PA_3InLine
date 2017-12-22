/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.linhaTres;

import java.io.Serializable;
import model.excepcoes.EmptyException;
import model.excepcoes.FullException;
import model.excepcoes.InsuficientElementsException;
import model.stack.StackLinked;

/**
 * Classe semelhante ao TadDeque mas que remove 3 elementos e o getLast e
 * getFirst devolve tambem 3 elementos
 *
 * @author tiago
 */
public class LinhaTres<E> implements TADDequeLinhaTres<E>, Serializable {

    private int size;

    private DNode<E> header;
    private int maxSize;

    /**
     * Metodo construtor
     *
     * @param maxSize para definir o tamanho
     */
    public LinhaTres(int maxSize) {
        this.maxSize = maxSize;
        size = 0;
        header = new DNode<E>(null, null, null);
        header.setAnterior(header);
        header.setProximo(header);
    }

    /**
     * Metodo int que retorna o tamanho
     *
     * @return size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Metodo boolean que retorn true caso esteja vazio
     *
     * @return size == 0
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Metodo que adciona um elemento no inicio
     *
     * @param elemt a adicionar
     */
    @Override
    public void addFirst(E elemt) throws FullException {
        if (this.maxSize == this.size)
            throw new FullException();
        DNode<E> newNode = new DNode<>(header, header.getProximo(), elemt);
        header.getProximo().setAnterior(newNode);
        header.setProximo(newNode);

        size++;
    }

    /**
     * MEtodo que adiciona um elemento no fim
     *
     * @param elemt a adicionar
     */
    @Override
    public void addLast(E elemt) throws FullException {
        if (this.maxSize == this.size)
            throw new FullException();

        DNode<E> newNode = new DNode<>(header.getAnterior(), header, elemt);
        header.getAnterior().setProximo(newNode);
        header.setAnterior(newNode);

        size++;
    }

    /**
     * Metodo que remove os 3 primeiros elementos
     *
     * @return array de elementos removidos
     */
    @Override
    public Iterator removeFirst() {
        Iterator it = getFirst();

        header.setProximo(header.getProximo().getProximo().getProximo().getProximo());
        header.getProximo().setAnterior(header);

        size = this.size - 3;
        return it;
    }

    /**
     * Meotodo o que remove os 3 ultimos elementos
     *
     * @return array de elementos removidos
     */
    @Override
    public Iterator removeLast() {
        Iterator it = getLast();

        header.setAnterior(header.getAnterior().getAnterior().getAnterior().getAnterior());
        header.getAnterior().setProximo(header);

        size = this.size - 3;
        return it;
    }

    /**
     * Mtodo que vai buscar os 3 primeiros elementos
     *
     * @return array com 3 primeiros elementos
     * @throws InsuficientElementsException se não houver 3 elementos
     */
    @Override
    public Iterator getFirst() throws InsuficientElementsException {
        if (this.size < 3)
            throw new InsuficientElementsException();

        StackLinked<E> stack = new StackLinked();
        stack.push(header.getProximo().getElement());
        stack.push(header.getProximo().getProximo().getElement());
        stack.push(header.getProximo().getProximo().getProximo().getElement());
        return stack.getIterator();
    }

    /**
     * Metodo que vai buscar os 3 ultimos elementos
     *
     * @return array com 3 ultimos elementos
     * @throws EmptyException caso não haja
     */
    @Override
    public Iterator getLast() throws EmptyException {
        if (this.size < 3)
            throw new InsuficientElementsException();

        StackLinked<E> stack = new StackLinked();
        stack.push(header.getAnterior().getElement());
        stack.push(header.getAnterior().getAnterior().getElement());
        stack.push(header.getAnterior().getAnterior().getAnterior().getElement());
        return stack.getIterator();
    }

    public Iterator<E> getIterator() {
        return new IteratorTresEmLinha();
    }

    @Override
    public void removeAll() throws EmptyException {
        this.header.anterior = this.header;
        this.header.proximo = this.header;
        this.size = 0;
    }

    /**
     * classe privada iterador
     */
    private class IteratorTresEmLinha<E> implements Iterator<E> {

        DNode pos;

        /**
         * Metodo construtor
         */
        IteratorTresEmLinha() {
            this.pos = header.getProximo();
        }

        /**
         * Metodo boolean retorna true se a pos for diferente do header
         *
         * @return se pos diferente de header
         */
        @Override
        public boolean hasNext() {
            return pos != header;
        }

        /**
         * Metodo que avança para o proximo elementos
         *
         * @return o elemento actual
         */
        @Override
        public E next() {
            E aux = (E) pos.getElement();
            pos = pos.getProximo();
            return aux;
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        Iterator it = new IteratorTresEmLinha();
        while (it.hasNext()) {

            str.append(it.next().toString());
        }
        return str.toString();

    }

    private class DNode<E> implements Serializable {

        private DNode<E> anterior, proximo;
        private E element;

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

    }
}
