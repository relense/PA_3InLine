/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.peca;

import java.io.Serializable;

/**
 * Class que representa uma peça do tabuleiro
 *
 * @author Miguel
 */
public class Peca implements Serializable {

    private String tipo;
    private int numero;

    /**
     * Método construtor
     *
     * @param cor cor da peca
     */
    public Peca(int numero) {
        this.numero = numero;
        this.tipo = "Normal";
    }

    /**
     * Método construtor
     *
     * @param tipo tipo de peca
     * @param cor cor da peca
     */
    protected Peca(String tipo, int numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    /**
     * Método String que devolve o tipo de peça
     *
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Método void para definir o tipo de pça
     *
     * @param tipo tipo de peca
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Método que devolve a cor da peça
     *
     * @return cor
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Método para definir a cor da peça
     *
     * @param cor cor da peca
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Método String para imprimir uma peça
     *
     * @return "desenho" da peça
     */
    @Override
    public String toString() {

        if (this instanceof PecaEspecial)
            return "5";

        return this.numero+"";

    }
}
