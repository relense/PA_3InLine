package model.peca;

import java.io.Serializable;
import model.aleatorio.ConjuntoAleatorio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Class que representa uma fabrica de peças
 *
 * @author tiago
 */
public class PecaGenerator implements Serializable {

    private ConjuntoAleatorio conjuntoAleatorio;
    private PecaEspecial pecaEspecial ;
    private static Peca[] pecas = {new Peca(1), new Peca(2),
        new Peca(3), new Peca(4)};

    /**
     * Método construtor
     *
     *
     *
     */
    public PecaGenerator() {

        this.conjuntoAleatorio = new ConjuntoAleatorio(pecas);
        this.pecaEspecial = new PecaEspecial();
    }

    /**
     * Método que devolve uma peça aleatoria
     *
     * @return peca aleatoria
     */
    public Peca getPecaAleatoria() {

        return (Peca) this.conjuntoAleatorio.peek();
    }

    /**
     * Método que devolve uma peça aleatoria especial
     *
     * @return peça aleeatoria
     */
    public Peca getPecaAleatoriaComPecaEspecial() {

        return (Math.random() * 20 < 1) ? this.pecaEspecial : (Peca) this.conjuntoAleatorio.peek();
    }

   
}
