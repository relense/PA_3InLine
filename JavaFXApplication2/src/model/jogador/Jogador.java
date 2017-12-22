package model.jogador;


import java.io.Serializable;
import model.historico.Historico;
import model.jogo.Jogo;
import model.jogo.JogoNormal;
import model.jogo.PontuacaoBase;
import model.jogoFactory.TipoJogo;
import model.linhaTres.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Miguel
 */
public class Jogador implements Serializable {

    private String nome;
    private Historico<Jogo> historico;
    private static final int MAX_SIZE = 10;
    private GameStats[] estatisticasDeJogo;

    public Jogador(String nome) {
        this.estatisticasDeJogo = new GameStats[4];
        for (int i = 0; i < 4; i++) {
            this.estatisticasDeJogo[i] = new GameStats();
        }
   

        this.nome = validarNome(nome);
        this.historico = new Historico(MAX_SIZE);
    }

    public Jogo getLastGame() {
        Iterator ir = this.historico.getIterator();
        return (Jogo) ir.next();
    }

    /**
     * Metodo que valida o nome nao podendo ser vazio nem nulo
     *
     * @param nome nome a validar
     * @return var (nome) se ok, "Nome indefenido" caso contrario
     */
    public String validarNome(String nome) {
        if (nome == null || nome.isEmpty())
            return "Nome indefenido";
        else
            return nome;
    }

    /**
     * Metodo que retorna o nome do jogador
     *
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo que altera o nome do jogador
     *
     * @param nome nome do jogador
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que devolve o historico do jogador
     *
     * @return historico
     */
    public Historico<Jogo> getHistorico() {
        return historico;
    }

    /**
     * Metodo para definir o historico
     *
     * @param historico historico do jogador
     */
    public void setHistorico(Historico<Jogo> historico) {
        this.historico = historico;
    }

    /**
     * Método que adiciona um jogo ao historico do jogador e as estatisticas
     * deste
     *
     * @param jogo Jogo
     */
    public void addHistorico(Jogo jogo) {
        this.historico.add(jogo);
        adicionarEstatisticas(jogo);
        Jogadores.getInstance().save();
    }

    /**
     * Método que adiciona as estatisticas para os diferentes tipo de jogos
     * possiveis
     *
     * @param jogo Jogo
     */
    private void adicionarEstatisticas(Jogo jogo) {
        if (jogo instanceof JogoNormal)
            if (jogo.getEstrategiaTipoPontuacao() instanceof PontuacaoBase){
                this.estatisticasDeJogo[0].adicionar(jogo);
            System.out.println("adicionou bem");
            }else
                this.estatisticasDeJogo[1].adicionar(jogo);
        else if (jogo.getEstrategiaTipoPontuacao() instanceof PontuacaoBase)
            this.estatisticasDeJogo[2].adicionar(jogo);
        else
            this.estatisticasDeJogo[3].adicionar(jogo);
    }

    /**
     * Método para obter as estatisticas dos diferentes jogos com base nos tipos
     * de jogos escolhidos
     *
     * @param tipoJogo tipo de jogo escolhido
     * @return estatistica
     */
    public GameStats obterEstatisticas(TipoJogo tipoJogo) {
        if (tipoJogo == TipoJogo.JOGO_NORMAL_BASE){
            System.out.println("retornou bem");
            return this.estatisticasDeJogo[0];
        
        }else if (tipoJogo == TipoJogo.JOGO_NORMAL_CORRIDA)
            return this.estatisticasDeJogo[1];
        else if (tipoJogo == TipoJogo.JOGO_RAPIDO_CORRIDA)
            return this.estatisticasDeJogo[2];
        else
            return this.estatisticasDeJogo[3];
    }

}
