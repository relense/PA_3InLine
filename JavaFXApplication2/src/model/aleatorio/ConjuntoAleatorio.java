/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aleatorio;

import java.io.Serializable;
import java.util.Random;

/**
 * Classe que representa um gerador de objectos aleatorio
 *
 * @author Miguel
 */
public class ConjuntoAleatorio<E> implements TADAleatorio<E>, Serializable {

    private E[] array;
    Random r;

    /**
     * Método contrutor
     *
     *
     * @param array array de elementos
     */
    public ConjuntoAleatorio(E[] array) {
        this.array = array;
        r = new Random();
    }

    /**
     * Metodo que retorna uma posicao no conjunto aleatorio aleatorio
     *
     * @return uma peca posicao
     */
    @Override
    public E peek() {
        return array[r.nextInt(this.array.length)];//retorna um espaço random no array
    }

}
