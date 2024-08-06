package main.java.xadrez.tabuleiro;

public class TabuleiroXadrez {

    private Casa[][] casas; // Matriz 8x8 para armazenar as casas do tabuleiro

    // Construtor da classe Tabuleiro
    public TabuleiroXadrez() {
        casas = new Casa[8][8];
        inicializarTabuleiro();
    }

    // Inicializa o tabuleiro com as casas e configuração inicial
    private void inicializarTabuleiro() {
        // Configuração inicial do tabuleiro
    }

    // Verifica se uma determinada posição está no limite do tabuleiro
    public boolean noLimite(int linha, char coluna) {
        /*
         * Verifica se a linha e a coluna estão dentro dos limites do tabuleiro.
         * As linhas devem estar entre 1 e 8 e as colunas entre 'a' e 'h'.
         */
        return false; // Implementação necessária
    }

    // Retorna uma string que representa o desenho de todo o tabuleiro na tela
    public String desenho() {
        /*
         * Retorna uma string que representa o desenho do tabuleiro.
         * A string deve mostrar as casas e as peças em suas posições.
         */
        return ""; // Implementação necessária
    }
}
