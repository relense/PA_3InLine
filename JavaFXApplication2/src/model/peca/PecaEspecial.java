package model.peca;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Class que vai representar uma peça especial e extende da peça
 *
 * @author tiago
 */
public class PecaEspecial extends Peca {

    /**
     * Método construtor
     *
     * @param cor cor da peca
     */
    public PecaEspecial() {
        super("Especial", 5);
    }

    /**
     * Método String que devolve como é a peça
     *
     * @return String
     */
    @Override
    public String toString() {
        return "5";
    }

}
