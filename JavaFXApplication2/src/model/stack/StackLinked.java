/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.stack;

import java.util.EmptyStackException;
import model.excepcoes.EmptyException;
import model.linhaTres.Iterator;

/**
 * Class que representa o stack dinamico
 *
 * @author Miguel
 */
public class StackLinked<E> implements Stack<E> {

    private int size;
    private Node top;

    /**
     * método construtor
     */
    public StackLinked() {
        this.size = 0;
        this.top = null;
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
     * Método boolean que nos diz se está vazia ou não
     *
     * @return true is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Método para retorna o elemento do topo
     *
     * @return element elemento topo
     * @throws EmptyException caso esteja vazio
     */
    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyException();
        return top.element;
    }

    /**
     * Método para adicionar um elemento à pilha
     *
     * @param element elemento a adicionar
     */
    @Override
    public void push(E element) {
        Node newNode = new Node(top, element);
        top = newNode;
        size++;

    }

    /**
     * Método para remover o último elemento adicionado à pilha
     *
     * @return elemento removido
     * @throws EmptyException caso esteja vazio
     */
    @Override
    public E pop() throws EmptyStackException {
        E elemRemoved = peek();
        this.top = this.top.next;
        this.size--;
        return elemRemoved;

    }

    /**
     * Método iterator
     *
     * @return iterador
     */
    @Override
    public Iterator<E> getIterator() {
        return new StackIterator();
    }

    /**
     * Class privada do Iterator
     */
    private class StackIterator implements Iterator<E> {

        private Node pos;

        /**
         * Método consturtor
         */
        public StackIterator() {
            pos = top;
        }

        /**
         * Método que verifica o elemento actual
         *
         * @return elemento actual
         */
        @Override
        public boolean hasNext() {
            return pos != null;

        }

        /**
         * Método que retorna o próximo elemento
         *
         * @return elem
         */
        @Override
        public E next() {

            E elem = pos.element;
            pos = pos.next;
            return elem;

        }
    }

    /**
     * Classe privada do tipo Node
     */
    private class Node {

        private Node next;
        private E element;

        /**
         * Método construtor
         *
         * @param next
         * @param element
         */
        public Node(Node next, E element) {
            this.next = next;
            this.element = element;
        }

    }

}
