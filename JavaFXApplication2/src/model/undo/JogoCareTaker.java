package model.undo;


import model.jogo.Jogo;
import model.stack.Stack;
import model.stack.StackLinked;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Class que representa o CareTaker do jogo
 *
 * @author Miguel
 */
public class JogoCareTaker {

    Stack<Memento> stackMementoUndo;

    /**
     * Método construtor
     */
    public JogoCareTaker() {
        stackMementoUndo = new StackLinked();
    }

    /**
     * Método para gravar o jogo
     *
     * @param jogo jogo
     */
    public void saveState(Jogo jogo) {

        Memento objMemento = jogo.save();

        stackMementoUndo.push(objMemento);

    }

    /**
     * Método que faz restore do jogo
     *
     * @param jogo jogo
     */
    public void restoreState(Jogo jogo) {
        if (stackMementoUndo.isEmpty())
            return;
        Memento objMemento = stackMementoUndo.pop();
        jogo.restore(objMemento);

    }
}
