/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.linhaTres;

/**
 * interface que contem o comportamento de um Iterator
 *
 * @author tiago
 */
public interface Iterator<E> {

    public boolean hasNext();//Devolve se existir elemento

    public E next();//Devolve o elemento actual
}
