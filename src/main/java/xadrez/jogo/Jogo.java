package main.java.xadrez.jogo;

import main.java.xadrez.jogo.Jogador;
import main.java.xadrez.tabuleiro.TabuleiroXadrez;

/**
 * Essa classe é responsável pelo gerenciamento do jogo, controlando tudo o que acontece. Essa classe contém um tabuleiro, 
 * 2 jogadores e o conjunto de 32 peças disponíveis. O jogo sabe o estado em que se encontra a cada momento (por exemplo: início do jogo, 
 * xeque, xeque-mate). Sabe também de que jogador é a vez, controlando as jogadas, de quem é a vez, as checagens, etc, sendo a classe 
 * responsável por manter as informações na tela e solicitar ao jogador da vez as informações necessárias para a jogada ou interrupção 
 * do jogo. No início do jogo, também solicita a cada jogador o seu nome. Na tela, além do tabuleiro, o Jogo deve manter visível as peças 
 * de cada jogador que já foram capturadas, desenhadas do lado do tabuleiro correspondente ao jogador.

    A cada jogada solicitada por um jogador, o Jogo é a classe que dispara a jogada checando se ela é válida,  atualizando o tabuleiro, 
    a situação, o histórico de jogadas, a tela, a situação do jogo, das peças, etc, usando sempre as chamadas de métodos das outras 
    classes que forem apropriados.

    Todas as jogadas efetivamente realizadas devem ser registradas em um histórico de jogadas que pode ser solicitado pelo Gerenciador 
    para armazenamento em arquivo ou visualização das jogadas.

    A classe Jogo deve ter ao menos os métodos esboçados abaixo (eu, gu, ja coloquei); talvez precise de mais métodos

 */

public class Jogo {
    private TabuleiroXadrez tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogadorAtual;
    private String historicoJogadas;

    // Construtor da classe Jogo
    public Jogo() {
        // Inicializa o tabuleiro e os jogadores
    }

    // Verifica se a jogada é válida ou não
    public boolean jogadaValida(int linhaO, char colunaO, int linhaD, char colunaD) {
        /*
         * Verifica se a jogada do jogador é válida com base nas regras do jogo.
         * Verifica se a peça pode se mover para a nova posição e se a jogada é legal.
         */
        return false; // Implementação necessária
    }

    // Se a jogada for válida, atualiza o tabuleiro e a situação do jogo
    public void realizarJogada(int linhaO, char colunaO, int linhaD, char colunaD) {
        /*
         * Atualiza o tabuleiro com a jogada do jogador.
         * Altera o estado do jogo, atualiza as peças capturadas.
         */
        desenhoTabuleiroAtualizado();
    }

    private void desenhoTabuleiroAtualizado() {
        /*
         * Exibe o tabuleiro atualizado na tela
         */
    }

    // Retorna uma string com todos os dados relevantes do jogo para retomada posterior
    public String registroJogo() {
        /*
         * Retorna uma string que representa o estado atual do jogo, incluindo o histórico de jogadas.
         */
        return ""; // Implementação necessária
    }
}
