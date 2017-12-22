package model.undo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.jogo.Tabuleiro;
import model.peca.PecaGestor;

/**
 * Class que representa um Memento
 *
 * @author Miguel
 */
public class Memento {

    private Tabuleiro mementoTabuleiro;
    private PecaGestor mementoPecaGestor;
    private int mementoJogadas;

    /**
     * Método construtor
     *
     * @param tabuleiro tabuleiro
     * @param pecaGestor peca gestor
     * @param jogadas jogadas
     */
    public Memento(Tabuleiro tabuleiro, PecaGestor pecaGestor, int jogadas) {
        this.mementoTabuleiro = tabuleiro;
        this.mementoPecaGestor = pecaGestor;
        this.mementoJogadas = jogadas;

    }

    /**
     * Método do tipo tabuleiro que devolve um memento do tabuleiro
     *
     * @return mementoTabuleiro
     */
    public Tabuleiro getMementoTabuleiro() {
        return mementoTabuleiro;
    }

    /**
     * Método para definir o memento do tabuleiro
     *
     * @param mementoTabuleiro guarda o momemnto do tabuleiro
     */
    public void setMementoTabuleiro(Tabuleiro mementoTabuleiro) {
        this.mementoTabuleiro = mementoTabuleiro;
    }

    /**
     * Método do tipo PecaGestor que devolve um memento do pecaGestor
     *
     * @return mementoPecaGestor
     */
    public PecaGestor getMementoPecaGestor() {
        return mementoPecaGestor;
    }

    /**
     * Método que definir um memento do peça gestor
     *
     * @param mementoPecaGestor guarda o momemento da peca gestor
     */
    public void setMementoPecaGestor(PecaGestor mementoPecaGestor) {
        this.mementoPecaGestor = mementoPecaGestor;
    }

    /**
     * Método int que devolve um memento as jogadas feitas
     *
     * @return mementoJogadas
     */
    public int getMementoJogadas() {
        return mementoJogadas;
    }

    /**
     * Método que define um memento das jogadas
     *
     * @param mementoJogadas guarda o momento da jogada
     */
    public void setMementoJogadas(int mementoJogadas) {
        this.mementoJogadas = mementoJogadas;
    }

}
