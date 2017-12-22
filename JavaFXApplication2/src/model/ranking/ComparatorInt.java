/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ranking;

/**
 * Classe que indica o como se comporta uma comparação com elementos inteiros,
 * dando suporte neste caso aos testes
 *
 * @author tiago
 */
public class ComparatorInt extends StrategyComparator {

    @Override
    public int compareTo(Object e1, Object e2) {
        return (int) e1 - (int) e2;
    }

}
