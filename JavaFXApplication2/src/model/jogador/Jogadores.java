/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jogador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe para guardar os diferentes jogadores em ficheiro
 *
 * @author tiago
 */
public class Jogadores implements Serializable {

    private HashMap<String, Jogador> jogadores;

    private static Jogadores instance;
    private static final String JOGADORES_DATA_FILE = "jogadores.dat";
private Jogadores(){
    this.jogadores=new HashMap();
}
    public static Jogadores getInstance() {
        if (instance == null)
            try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(JOGADORES_DATA_FILE))) {
                instance = (Jogadores) oin.readObject();

                oin.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                instance = new Jogadores();

            }
        return instance;
    }

    /**
     * Método que retorna o nome do jogador
     *
     * @param nome nome do jogador
     * @return
     */
    public Jogador getJogador(String nome) {
        if (this.jogadores.containsKey(nome)){
            System.out.println("existia");   
            return this.jogadores.get(nome);
        }
        return addJogador(nome);

    }

    private Jogador addJogador(String nome) {
        Jogador aux = new Jogador(nome);
        this.jogadores.put(aux.getNome(), aux);
        this.save();
        return aux;
    }
    
    
    public HashMap<String, Jogador> getJogadores(){
    
    return this.jogadores;
    }
    /**
     * Método para gravar para ficheiro
     */
    public void save() {
        System.out.println("Saving");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(JOGADORES_DATA_FILE));
            oos.writeObject(this);
            oos.flush();
            oos.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
