/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ranking;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import model.jogador.Jogador;

/**
 * Classe que representa o ranking e irá funcionar como um singleton
 *
 * @author tiago
 */
public final class Rankings implements Serializable {

    private static Rankings instance = null;

    //meter em array
    private JogoRanking[] rankings;

    private static final String RANKING_DATA_FILE = "rankings.dat";

    /**
     * Método privado construtor
     */
    private Rankings() {
        rankings = new JogoRanking[4];
        rankings[0] = new JogoRanking();
        rankings[1] = new JogoRanking();
        rankings[2] = new JogoRanking();
        rankings[3] = new JogoRanking();

    }

    /**
     * Método para ir buscar uma instancia do ranking
     *
     * @return instancia do ranking
     */
    public static Rankings getInstance() {
        if (instance == null) {
            try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(RANKING_DATA_FILE))) {
                instance = (Rankings) oin.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
                instance = new Rankings();

            }
        }
        instance.gravar();
        return instance;
    }

    /**
     * Método para ir buscar o ranking de um jogo normal com pontuacao base
     *
     * @return rank
     */
    public JogoRanking<RankingField> getRankingJogoNormalPontuacaoBase() {
        return rankings[0];
    }

    /**
     * Método para ir buscar o ranking jogo normal com pontuacao de corrida
     *
     * @return rank
     */
    public JogoRanking<RankingField> getRankingJogoNormalPontuacaoCorrida() {
        return rankings[1];
    }

    /**
     * Método par air buscar o ranking de um jogo rapido com pontuacao base
     *
     * @return rank
     */
    public JogoRanking<RankingField> getRankingJogoRapidoPontuacaoBase() {
        return rankings[2];
    }

    /**
     * Método para ir buscar o ranking de um jogo rapido com pontuacao de
     * corrida
     *
     * @return rank
     */
    public JogoRanking<RankingField> getRankingJogoRapidoPontuacaoCorrida() {
        return rankings[3];

    }

    /**
     * Método para gravar para ficheiro
     */
    public void gravar() {
        System.out.println("Saving");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RANKING_DATA_FILE));
            oos.writeObject(this);
            oos.flush();
            oos.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
