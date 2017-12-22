/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jogo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import model.peca.Peca;
import model.peca.PecaGenerator;

/**
 * Classe que representa o tabuleiro
 *
 * @author Miguel
 */
public class Tabuleiro implements Serializable {

    private static final int MAX_TAMANHO = 10;

    private ArrayList<LinhaJogo> linhaList;
    private int pecasDestruidas;

    private Tabuleiro() {
    }

    /**
     * Método construtor
     *
     * @param pecaFactory construtor de pecas
     */
    public Tabuleiro(PecaGenerator pecaFactory) {

        this.linhaList = new ArrayList<>();

        criarTabuleiro(pecaFactory);
    }

    /**
     * Método que cria o tabuleiro de jogo
     *
     * @param pecaFactory construtor de pecas
     */
    private void criarTabuleiro(PecaGenerator pecaFactory) {
        for (int i = 0; i < 8; i++) {
            this.linhaList.add(new LinhaJogo(pecaFactory.getPecaAleatoria(), pecaFactory.getPecaAleatoria()));
        }
    }

    /**
     * Método que adciona uma peça à direita e outra à esquerda a uma linha
     *
     * @param linha linha que vamos adicionar pecas
     * @param pecaLeft peca à esquerda
     * @param pecaRight peca à direita
     */
    public void adicionar(int linha, Peca pecaLeft, Peca pecaRight) {

        this.pecasDestruidas += this.linhaList.get(linha - 1).add(pecaLeft, pecaRight);

    }

    /**
     * Método que devolve o numero de peças destruidas
     *
     * @return pecasDestruidas
     */
    public int getPecasDestruidas() {
        return pecasDestruidas;
    }

    /**
     * Método que faz uma copia do tabuleiro
     *
     * @return copy do tabuleiro
     */
    public Tabuleiro deepCopy() {
        Tabuleiro copy = new Tabuleiro();
        ArrayList<LinhaJogo> arrayCopy = new ArrayList();

        for (int i = 0; i < linhaList.size(); i++) {
            arrayCopy.add(this.linhaList.get(i).deepCopy());

        }
        copy.linhaList = arrayCopy;
        copy.pecasDestruidas = this.pecasDestruidas;
        return copy;
    }

    /**
     * Método boolean que indica se uma linha de jogo está cheia
     *
     * @return true se alguma linha estiver cheia
     */
    public boolean linhaJogoCheia() {
        boolean isAnyLinhaFull = false;
        for (int i = 0; i < this.linhaList.size(); i++) {
            if (this.linhaList.get(i).isFull()) {
                isAnyLinhaFull = true;
                break;
            }
        }
        return isAnyLinhaFull;
    }

    public ArrayList<LinhaJogo> getLinhaList() {
        return linhaList;
    }

    public void setLinhaList(ArrayList<LinhaJogo> linhaList) {
        this.linhaList = linhaList;
    }
    
    

    /**
     * Método toString para desenhar o tabuleiro
     *
     * @return tabuleiro
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < this.linhaList.size(); i++) {
            str.append((i + 1)).append(" [").append(this.linhaList.get(i)).append("]").append("\n");
        }
        return str.toString();
    }

   public Iterator getIterador(int linha) {
   return this.linhaList.get(linha).getIterator().iterator();
   }

   public LinhaJogo getLinhaList(int i) {
    return this.linhaList.get(i);
    }
}
