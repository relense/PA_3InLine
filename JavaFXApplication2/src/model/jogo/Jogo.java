package model.jogo;

import java.io.Serializable;
import java.util.Iterator;
import model.jogador.Jogador;
import model.logger.Observable;
import model.peca.PecaGenerator;
import model.peca.PecaGestor;
import model.ranking.JogoRanking;
import model.ranking.RankingField;
import model.undo.Memento;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Class abstracta que representa o Jogo e tudo o que vai incluir o jogo
 *
 * @author Miguel
 */
public abstract class Jogo extends Observable implements Serializable {

    private static int numeroDeJogos = 0;
    private final int ID;

    protected PecaGestor pecaGestor;
    protected Tabuleiro tabuleiro;
    protected EstrategiaTipoPontuacao estrategiaTipoPontuacao;
    protected JogoRanking<RankingField> rank;
    protected int jogadas;
    private long start;
    protected long end;
    private String tema;

    /**
     * Método construtor
     *
     * @param tema
     * @param pecaFactory construcao das pecas
     * @param estrategiaTipoPontuacao estrategia de pontuacao
     * @param rank rank do jogo
     */
    public Jogo(String tema, PecaGenerator pecaFactory, EstrategiaTipoPontuacao estrategiaTipoPontuacao, JogoRanking rank) {
        super();
        this.tema = tema;
        this.estrategiaTipoPontuacao = estrategiaTipoPontuacao;
        this.tabuleiro = new Tabuleiro(pecaFactory);
        this.pecaGestor = new PecaGestor(pecaFactory);
        this.rank = rank;

        this.ID = ++numeroDeJogos;

    }

    public void start() {
        if (this.start == 0) {
            super.notifyObservers("Inicio");

            this.start = System.currentTimeMillis();
        }
    }

    /**
     * Metodo que devolve o ID do jogo
     *
     * @return ID do jogo
     */
    public int getID() {
        return ID;
    }

    /**
     * Método do tipo int para obter a pontuacao de jogo com base na estrategia
     * escolhida pelo jogador
     *
     * @return a pontuacao
     */
    public int obterPontuacao() {
        if (this.end == 0) {
            return estrategiaTipoPontuacao.pontuacao(tabuleiro.getPecasDestruidas(), System.currentTimeMillis() - this.start);
        }

        return estrategiaTipoPontuacao.pontuacao(tabuleiro.getPecasDestruidas(), this.end - this.start);

    }

    /**
     * Método void que irá fazer uma jogada de cada jogador com base na linha
     * que este escolha
     *
     * @param linha linha que o jogador vai escolher para a jogada
     */
    public void jogada(int linha) {
        if (this.start == 0) {
            throw new RuntimeException("Game not started");
        }

        this.notifyObservers("Jogada: esquerda " + pecaGestor.getPecaLeft() + " e direita " + pecaGestor.getPecaRight());
        tabuleiro.adicionar(linha, pecaGestor.getPecaLeft(), pecaGestor.getPecaRight());
        pecaGestor.updatePeca();
        jogadas++;
    }

    /**
     * Método abstracto boolean que vai mostrar se o jogo acabou ou não
     *
     * @return true se acabou
     */
    public abstract boolean acabouJogo();

    /**
     * Método Memento para gravar o jogo
     *
     * @return Memento
     */
    public Memento save() {

        return new Memento(this.tabuleiro.deepCopy(), this.pecaGestor.shallowCopy(), this.jogadas);
    }

    /**
     * Método void para fazer restore
     *
     * @param objMemento objeto que queremos fazer restore
     */
    public void restore(Memento objMemento) {

        this.jogadas = objMemento.getMementoJogadas();
        this.pecaGestor = objMemento.getMementoPecaGestor();
        this.tabuleiro = objMemento.getMementoTabuleiro();
        this.notifyObservers("Undo");
    }

    /**
     * Método void para mostrar o jogo em consola
     */
    public void mostrarInfo() {

        System.out.println("Pontuacao: " + obterPontuacao());
        System.out.println("Jogadas:" + this.jogadas);
        System.out.println("Pecas Destruidas: " + this.tabuleiro.getPecasDestruidas());
        System.out.println("Proximas pecas: " + this.pecaGestor.getNextPecaLeft()
                + " | " + this.pecaGestor.getNextPecaRight());
        System.out.println("Pecas atuais: " + this.pecaGestor.getPecaLeft()
                + " | " + this.pecaGestor.getPecaRight());

        System.out.println(this.tabuleiro);
    }

    /**
     * Método que retorna o tipo de pontuação
     *
     * @return estrategiaTipoPontuacao
     */
    public EstrategiaTipoPontuacao getEstrategiaTipoPontuacao() {
        return estrategiaTipoPontuacao;
    }

    /**
     * Método que devolve a duração do jogo
     *
     * @return duracao
     */
    public double obterDuracao() {
        return this.end - this.start;
    }

    public String obterTemaSelecionado() {
        return tema;
    }

    public Iterator interatorLinha(int linha) {
        //foi criado um iterador no tabuleiro
        return this.tabuleiro.getIterador(linha);

    }

    public PecaGestor getPecaGestor() {
        return pecaGestor;
    }

    public void inserirNoRanking(Jogador jogador) {
        this.rank.add(new RankingField(jogador.getNome(), this.obterPontuacao()));
    }

    public LinhaJogo getLinha(int i) {
   
      return  this.tabuleiro.getLinhaList(i);
        
    }

   

}
