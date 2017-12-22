/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.stack;

import model.excepcoes.EmptyException;
import model.excepcoes.FullException;
import model.linhaTres.Iterator;


/**
 * Interface Stack
 *
 * @author Miguel
 */
public interface Stack<E> {

    /**
     * Metodo int que retorna o tamanho
     *
     * @return size
     */
    public int size();

    /**
     * Método boolean que nos diz se está vazia ou não
     *
     * @return true is empty
     */
    public boolean isEmpty();

    /**
     * Método para retorna o elemento do topo
     *
     * @return element element topo
     * @throws EmptyException caso esteja vazio
     */
    public E peek() throws EmptyException;

    /**
     * Método para adicionar um elemento à pilha
     *
     * @param elem elem a adicionar
     * @throws FullException caso esteja full
     */
    public void push(E elem) throws FullException;

    /**
     * Método para remover o último elemento adicionado à pilha
     *
     * @return elemento removido
     * @throws EmptyException caso esteja vazio
     */
    public E pop() throws EmptyException;

    /**
     * Método iterator
     *
     * @return iterador iterator
     */
    public Iterator<E> getIterator();

}
