package model.peca;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Enum para as diferentes cores das peças
 *
 * @author tiago
 */
public enum Cor {

    AZUL, ROXO, VERDE, VERMELHO, PRETO, BRANCO;

    private static final List<Cor> VALOR = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALOR.size();
    private static final Random RANDOM = new Random();

    /**
     * Método que vai devolver uma cor aleatoria
     *
     * @return uma cor aleatoria
     */
    public static Cor Aleatorio() {
        return VALOR.get(RANDOM.nextInt(SIZE));
    }

    /**
     * Método que devolve o primeiro caracter do nome da cor
     *
     * @return o primeiro caracter
     */
    public char returnChar() {

        String s = super.toString();
        return s.charAt(0);

    }
}
