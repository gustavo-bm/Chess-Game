package main.java.xadrez.tabuleiro;

import main.java.xadrez.pecas.interfacePeca.Peca;

/*
 * Cada casa tem uma cor (branco ou preto), uma linha (de 1 a 8) e uma coluna (de a a h).
 * Cada casa pode estar livre ou ocupada por uma peça e deve saber que peça a ocupa. 
 */
public class Casa {

    private String cor;
    private int linha;
    private char coluna;
    private Peca peca; // Supondo que Peca é a classe base para todas as peças

    public Casa(String cor, int linha, char coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.cor = cor;
        this.peca = null; // Inicialmente, a casa está livre
    }

    // Retorna a cor da casa
    public String getCor() {
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
    public Peca colocarPeca(Peca peca) {
        this.peca = peca;
        return peca;
    }

    // Remove a peça da casa
    public void removerPeca() {
        this.peca = null;
    }

    // Retorna a peça que ocupa a casa
    public Peca getPeca() {
        return peca;
    }

    @Override
    public String toString() {
        String pecaStr = (peca != null) ? peca.toString() : "Nenhuma peça";
        return "Cor da casa: " + cor + "\nLinha: " + linha + "\nColuna: " + coluna + "\nPeça: " + pecaStr;
    }

}
