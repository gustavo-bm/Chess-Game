package main.java.xadrez.jogo;

public class Jogada {

    // Verifica se uma jogada é válida
    public boolean ehValida() {
        /*
         * Uma jogada é válida se:
         * - Tem uma posição inicial e uma posição final dentro dos limites do tabuleiro
         * - A peça que está na posição inicial é do jogador que está realizando o movimento
         * - A posição final está livre ou está ocupada por peça do oponente
         * - O caminho está livre, caso a peça não puder pular sobre as outras
         * - O movimento é válido para a peça que está na casa inicial
         */
        return false; // Implementação necessária
    }

    // Verifica se a jogada levou a uma situação de xeque
    public boolean ehXeque() {
        /*
         * A função deve verificar se o rei do oponente está em xeque após a jogada.
         */
        return false; // Implementação necessária
    }

    // Verifica se a jogada levou a uma situação de xeque mate
    public boolean ehXequeMate() {
        /*
         * A função deve verificar se o rei do oponente está em xeque mate após a jogada.
         */
        return false; // Implementação necessária
    }
}
