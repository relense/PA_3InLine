package model.peca;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;


/**
 * Class que representa o gestor de peças
 *
 * @author tiago
 */
public class PecaGestor implements Serializable {

    private PecaGenerator pecaFactory;
    private Peca pecaRight;
    private Peca pecaLeft;
    private Peca nextPecaRight;
    private Peca nextPecaLeft;

    /**
     * Método construtor
     *
     * @param pecaFactory construtor de pecas
     */
    public PecaGestor(PecaGenerator pecaFactory) {
        this.pecaFactory = pecaFactory;
        this.pecaRight = pecaFactory.getPecaAleatoriaComPecaEspecial();
        this.pecaLeft = pecaFactory.getPecaAleatoriaComPecaEspecial();
        this.nextPecaLeft = pecaFactory.getPecaAleatoriaComPecaEspecial();
        this.nextPecaRight = pecaFactory.getPecaAleatoriaComPecaEspecial();
    }

    /**
     * Método para definir as peças actuais à esquerda e à direita e quais irão
     * ser as proximas peças à direita e à esquerda
     */
    public void updatePeca() {
        this.pecaLeft = this.nextPecaLeft;
        this.pecaRight = this.nextPecaRight;
        this.nextPecaLeft = pecaFactory.getPecaAleatoriaComPecaEspecial();
        this.nextPecaRight = pecaFactory.getPecaAleatoriaComPecaEspecial();
    }

    /**
     * Método do tipo PecaFactory que devolve a pecafactory
     *
     * @return pecaFactory
     */
    public PecaGenerator getPecaFactory() {
        return pecaFactory;
    }

    /**
     * Método que devolve a peça à direita
     *
     * @return pecaRight
     */
    public Peca getPecaRight() {
        return pecaRight;
    }

    /**
     * Método que devolve a peça à esquerda
     *
     * @return pecaLeft
     */
    public Peca getPecaLeft() {
        return pecaLeft;
    }

    /**
     * Método que devolve a proxima peça à direita
     *
     * @return nextPecaRight
     */
    public Peca getNextPecaRight() {
        return nextPecaRight;
    }

    /**
     * Método que devolve a proxima peca à esquerda
     *
     * @return nextPecaLeft
     */
    public Peca getNextPecaLeft() {
        return nextPecaLeft;
    }

    /**
     * Método para fazer uma copia das peças actuais e das proximas
     *
     * @return copia
     */
    public PecaGestor shallowCopy() {
        PecaGestor copy = new PecaGestor(this.pecaFactory);
        copy.nextPecaLeft = this.nextPecaLeft;
        copy.nextPecaRight = this.nextPecaRight;
        copy.pecaLeft = this.pecaLeft;
        copy.pecaRight = this.pecaRight;
        return copy;
    }

   
}
