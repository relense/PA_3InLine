/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jogo;

import java.io.Serializable;
import java.util.ArrayList;
import model.linhaTres.Iterator;

import model.linhaTres.LinhaTres;
import model.peca.Peca;
import model.peca.PecaEspecial;

/**
 * Class que representa uma linha de jogo
 *
 * @author Miguel
 */
public class LinhaJogo implements Serializable {

    private final int MAX_SIZE = 10;

    private int numeroPecasDireita;
    private int numeroPecasEsquerda;
    private LinhaTres<Peca> linha;

    /**
     * Método construtor vazio
     */
    private LinhaJogo() {
        this.linha = new LinhaTres(MAX_SIZE);
    }

    /**
     * Método construtor
     *
     * @param left peca à esquerda
     * @param right peca à direita
     */
    public LinhaJogo(Peca left, Peca right) {
        this.linha = new LinhaTres(MAX_SIZE);
        this.linha.addFirst(left);
        this.linha.addLast(right);
        this.numeroPecasDireita = 1;
        this.numeroPecasEsquerda = 1;

    }

    /**
     * Método que adiciona uma peça à direta e à esquerda
     *
     * @param left peca à esquerda
     * @param right peca à direita
     * @return pecas destruidas
     */
    public int add(Peca left, Peca right) {

        if (left instanceof PecaEspecial || right instanceof PecaEspecial) {
            int aux = this.numeroPecasDireita + this.numeroPecasEsquerda + 2;
            this.numeroPecasDireita = 0;
            this.numeroPecasEsquerda = 0;
            this.linha.removeAll();
            return aux;

        }

        addLeft(left);
        addRight(right);
        return update();
    }

    /**
     * Método que adiciona uma peça à esquerda
     *
     * @param peca
     */
    private void addLeft(Peca peca) {

        linha.addFirst(peca);
        this.numeroPecasEsquerda++;
    }

    /**
     * Método que adiciona uma peça à direita
     *
     * @param peca
     */
    private void addRight(Peca peca) {
        linha.addLast(peca);
        this.numeroPecasDireita++;

    }

    /**
     * Método boolean que devolve true se estiver cheia
     *
     * @return se à esquerda ou à direita não houver espaço que está cheio
     */
    public boolean isFull() {
        return this.numeroPecasDireita == this.MAX_SIZE / 2 || this.numeroPecasEsquerda == this.MAX_SIZE / 2;
    }

    /**
     * Método int que vai confirmar quantas peças ja foram destruidas
     *
     * @return numero de pecas destruidas
     */
    private int update() {
        int pecasDestruidas = 0;

        pecasDestruidas += verifyRight();
        pecasDestruidas += verifyLeft();
        return pecasDestruidas;
    }

    /**
     * Método que verifica quantas peças existem à direita
     *
     * @return pecas à direita
     */
    private int verifyRight() {
        if (this.linha.size() < 3)
            return 0;

        Iterator it = this.linha.getLast();
        Peca aux = (Peca) it.next();
        if (aux.equals(it.next()) && aux.equals(it.next())) {
            this.linha.removeLast();
            this.numeroPecasDireita -= 3;
            if (this.numeroPecasDireita < 0) {
                this.numeroPecasEsquerda += this.numeroPecasDireita;
                this.numeroPecasDireita = 0;
            }
            return 3;

        }
        return 0;

    }

    /**
     * Método int que verfica quantas peças existem à esquerda
     *
     * @return peças à esquerda
     */
    private int verifyLeft() {
        if (this.linha.size() < 3)
            return 0;

        Iterator it = this.linha.getFirst();
        Peca aux = (Peca) it.next();

        if (aux.equals(it.next()) && aux.equals(it.next())) {
            this.linha.removeFirst();
            this.numeroPecasEsquerda -= 3;
            if (this.numeroPecasEsquerda < 0) {
                this.numeroPecasDireita += this.numeroPecasEsquerda;
                this.numeroPecasEsquerda = 0;
            }
            return 3;

        }
        return 0;
    }

    /**
     * Método toString para desenhar a linha
     *
     * @return linha
     */
    @Override
    public String toString() {
        Iterator it = this.linha.getIterator();
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < this.MAX_SIZE / 2; i++) {
            if (i < this.MAX_SIZE / 2 - this.numeroPecasEsquerda)
                stb.append(" -");
            else
                stb.append(" ").append(it.next());
        }
        stb.append(" ");
        for (int i = 0; i < this.MAX_SIZE / 2; i++) {
            if (i < this.numeroPecasDireita)
                stb.append(it.next()).append(" ");
            else
                stb.append("- ");

        }
        return stb.toString();
    }

    /**
     * Método de copia que faz uma copia da linha de jogo
     *
     * @return copia da linha
     */
    public LinhaJogo deepCopy() {
        LinhaJogo copy = new LinhaJogo();
        Iterator it = linha.getIterator();
        for (int i = 0; i < this.linha.size(); i++) {

            copy.addRight((Peca) it.next());

        }
        copy.numeroPecasDireita = this.numeroPecasDireita;
        copy.numeroPecasEsquerda = this.numeroPecasEsquerda;
        return copy;

    }

    public ArrayList<Peca> getIterator() {
        ArrayList<Peca> aux=new ArrayList();
        int i=0;
        while(i!=this.MAX_SIZE/2-this.numeroPecasEsquerda){
            aux.add(null);
            i++;
        }
        
      
           Iterator it=(this.linha.getIterator());
           while(it.hasNext()){
               aux.add((Peca) it.next());
           }
        
         i=0;
        while(i!=this.MAX_SIZE/2-this.numeroPecasDireita){
            aux.add(null);
            i++;
        }  
        return aux;
    }

    public int getNumeroPecasDireita() {
        return numeroPecasDireita;
    }

    public int getNumeroPecasEsquerda() {
        return numeroPecasEsquerda;
    }
    
    
    
    

}
