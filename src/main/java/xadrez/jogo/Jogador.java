package main.java.xadrez.jogo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.xadrez.pecas.interfacePeca.Peca;
import main.java.xadrez.tabuleiro.Casa;
import main.java.xadrez.tabuleiro.TabuleiroXadrez;

/**
 * Cada jogador tem um nome, um conjunto de peças de uma das cores possíveis e
 * sabe quais peças suas ainda estão ativas no jogo.
 * Essa classe é onde é feita a entrada de dados para o jogo em si (dados do
 * jogador e jogadas).
 */
public class Jogador {
    private String nome;
    private String cor;
    private ArrayList<Peca> pecasAtivas;
    private ArrayList<Peca> pecasCapturadas;
    private int numPecasAtivas;
    private int numPecasCapturadas;

    // Construtor da classe Jogador
    public Jogador(String nome, String cor) {
        this.nome = nome;
        this.cor = cor;
        this.pecasAtivas = new ArrayList<Peca>(16);
        this.pecasCapturadas = new ArrayList<Peca>(16);
        this.numPecasAtivas = 0;
        this.numPecasCapturadas = 0;
    }

    // Solicita ao jogador a jogada desejada e retorna a informação
    public String informaJogada() {
        /*
         * Solicita ao jogador que digite a jogada ou o código "parar" para interromper
         * o jogo.
         * Retorna uma string com a jogada ou o comando digitado pelo jogador.
         */
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe a jogada: ");
        String jogada = scanner.nextLine();

        return jogada;
    }

    // Retorna uma string com os desenhos das peças capturadas
    public String pecasCapturadas() {
        /*
         * Retorna uma string com os desenhos das peças que foram capturadas pelo
         * jogador.
         */
        StringBuilder resultado = new StringBuilder();

        for (Peca peca : pecasCapturadas) {
            resultado.append(peca.desenho() + " ");
        }

        return resultado.toString();
    }

    // Adiciona uma peça capturada ao vetor de peças capturadas
    public void capturarPeca(Peca peca) {
        /*
         * Adiciona a peça capturada ao vetor de peças capturadas e remove a peça do
         * vetor de peças ativas.
         */
        pecasCapturadas.add(peca);
    }

    // Remove uma peça do vetor de peças ativas
    public void removerPeca(Peca peca) {
        /*
         * Remove a peça do vetor de peças ativas.
         */
        pecasAtivas.remove(peca);
    }

    // Adiciona uma peça ao vetor de peças ativas
    public void adicionarPeca(Peca peca) {
        /*
         * Adiciona a peça ao vetor de peças ativas.
         */
        pecasAtivas.add(peca);
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public ArrayList<Peca> getPecasAtivas() {
        return pecasAtivas;
    }

    public ArrayList<Peca> getPecasCapturadas() {
        return pecasCapturadas;
    }
}
