/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.logger;

import java.util.ArrayList;

/**
 * Class abstracta que representa o que irá ser observavél
 *
 * @author tiago
 */
public abstract class Observable {

    ArrayList<Observer> observers = new ArrayList();

    /**
     * Método para adicionar um observer
     *
     * @param o oberserver
     */
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    /**
     * Método para remover um observer
     *
     * @param o oberserver
     */
    public void deletObserver(Observer o) {
        this.observers.remove(o);
    }
    /**
     * Método para notificar os diferentes observers de algum tipo de mudança
     * @param args objecto
     */
    public void notifyObservers(Object args) {
        observers.stream().forEach((o) -> {
            o.update(this, args);
        });
    }

}
