/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.linhaTres;

import model.excepcoes.EmptyException;
import model.excepcoes.FullException;

/**
 * TAD que contem o comportamento de um DequeLinhaTres
 *
 * @author Tiago
 */
public interface TADDequeLinhaTres<E> {

    public int size();// Devolve o tamnho

    public boolean isEmpty();// true se está vazia

    public void addFirst(E elem) throws FullException;//adiciona um elemento no inicio

    public void addLast(E elem) throws FullException;//adiciona um elemento no fim

    public Iterator removeLast() throws EmptyException;//remove um array de elementos a partir do fim

    public Iterator removeFirst() throws EmptyException;//remove um array de elementos a partir do fim

    public Iterator getLast() throws EmptyException;//devolve um array de elemento a partir do fim

    public Iterator getFirst() throws EmptyException;//devolve um array de elementos a partir do inicio

    public void removeAll() throws EmptyException; //remove todos os elementos
}
