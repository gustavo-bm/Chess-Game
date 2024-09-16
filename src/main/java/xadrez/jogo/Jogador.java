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

    // Construtor da classe Jogador
    public Jogador(String nome, String cor) {
        this.pecasCapturadas = new ArrayList<Peca>(16);
        this.nome = nome;
        this.cor = cor;
        this.estaEmXeque = false;
    }

    public void setEstaEmXeque(boolean xeque) {
        estaEmXeque = xeque;
    }

    public boolean getEstaEmXeque() {
        return estaEmXeque;
    }

    // solicita ao jogador a jogada desejada e retorna a informação
    public String informaJogada(Scanner scanner) {
        System.out.print("Informe a jogada: ");
        String jogada = scanner.nextLine();

        return jogada;
    }

    // retorna uma string com os desenhos das peças capturadas
    public String pecasCapturadas() {
        StringBuilder resultado = new StringBuilder();

        for (Peca peca : pecasCapturadas) {
            resultado.append(peca.desenho() + " ");
        }

        return resultado.toString();
    }

    // adiciona uma peça capturada ao vetor de peças capturadas
    public void capturarPeca(Peca peca) {
        pecasCapturadas.add(peca);
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }
}
