package model.historico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;

/**
 * TAD que contem o comportamento de um qualquer historico
 *
 * @author tiago
 */
public interface TADHistorico<E> {

    public void add(E element);//Adiciona um elemento

    public int getSize();//Devolve o tamanho

    public boolean isEmpty();//Devolve se está vazio ou nao

    public E[] getElementsByDate(Date inicio, Date fim);

}
