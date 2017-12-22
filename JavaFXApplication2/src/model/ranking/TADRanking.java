/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ranking;

/**
 * TAD que representa uma interface com o comportamento de um qualquel ranking
 *
 * @author tiago
 */
public interface TADRanking<E> {

    public int size(); // metodo que devolve o tamanho

    public boolean isEmpty();//metodo que devolve se está vazia

    public void add(E elem);//metodo para adicionar um elemento

}
