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
    private ArrayList<Peca> pecasCapturadas;
    private boolean estaEmXeque;
    private int numPecasCapturadas;

    // Construtor da classe Jogador
    public Jogador(String nome, String cor) {
        this.nome = nome;
        this.cor = cor;
        this.estaEmXeque = false;
        this.pecasCapturadas = new ArrayList<Peca>(16);
        this.numPecasCapturadas = 0;
    }

    public void setEstaEmXeque(boolean xeque) {
        estaEmXeque = xeque;
    }

    public boolean getEstaEmXeque() {
        return estaEmXeque;
    }

    // Solicita ao jogador a jogada desejada e retorna a informação
    public String informaJogada(Scanner scanner) {
        /*
         * Solicita ao jogador que digite a jogada ou o código "parar" para interromper
         * o jogo.
         * Retorna uma string com a jogada ou o comando digitado pelo jogador.
         */

        System.out.print("Informe a jogada: ");
        String jogada = scanner.nextLine();

        return jogada;
    }

    // Retorna uma string com os desenhos das peças capturadas
    public String pecasCapturadas() {
        StringBuilder resultado = new StringBuilder();

        for (Peca peca : pecasCapturadas) {
            resultado.append(peca.desenho() + " ");
        }

        return resultado.toString();
    }

    // Adiciona uma peça capturada ao vetor de peças capturadas
    public void capturarPeca(Peca peca) {
        pecasCapturadas.add(peca);
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public ArrayList<Peca> getPecasCapturadas() {
        return pecasCapturadas;
    }
}
