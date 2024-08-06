package main.java.xadrez.tabuleiro;

import main.java.xadrez.pecas.interfacePeca.Peca;

public class Casa {

    public enum Cor {
        BRANCO,
        PRETO
    }

    private Cor cor;
    private int linha;
    private char coluna;
    private Peca peca; // Supondo que Peca é a classe base para todas as peças

    // Construtor da classe Casa
    public Casa(Cor cor, int linha, char coluna) {
        this.cor = cor;
        this.linha = linha;
        this.coluna = coluna;
        this.peca = null; // Inicialmente, a casa está livre
    }

    // Retorna a cor da casa
    public Cor getCor() {
        return cor;
    }

    // Retorna a linha da casa
    public int getLinha() {
        return linha;
    }

    // Retorna a coluna da casa
    public char getColuna() {
        return coluna;
    }

    // Verifica se a casa está livre
    public boolean estaLivre() {
        return peca == null;
    }

    // Define uma peça na casa
    public void colocarPeca(Peca peca) {
        this.peca = peca;
    }

    // Remove a peça da casa
    public void removerPeca() {
        this.peca = null;
    }

    // Retorna a peça que ocupa a casa
    public Peca getPeca() {
        return peca;
    }
}
