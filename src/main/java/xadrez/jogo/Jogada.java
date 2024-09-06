package main.java.xadrez.jogo;

/*
 * Uma jogada é criada a partir das informações do jogador que a está realizando, a posição inicial e posição final da jogada, 
 * mas deve manter o caminho com base nessas informações iniciais (veja o diagrama: mostra basicamente que a classe Caminho tem uma relação
 * de composição com relação à classe Jogada, ou seja, são criadas instâncias de objetos da classe Caminho aqui para acessar seus métodos e/ou
 * atributos).
 * 
 * Lembrar que cada peça é quem deve fornecer seu caminho (na forma de uma string) e identificar se o movimento é válido de acordo com sua forma de se movimentar. Uma vez criada, uma jogada não
 * pode ser alterada.
 */
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
