/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ranking;

import java.io.Serializable;

/**
 * Classe abstracta utilisada para a implementação do padrao Strategy neste caso
 * usado para uma comparacao de dois elementos
 *
 * @author tiago
 */
public abstract class StrategyComparator<E> implements Serializable {

    public abstract int compareTo(E e1, E e2);
}
