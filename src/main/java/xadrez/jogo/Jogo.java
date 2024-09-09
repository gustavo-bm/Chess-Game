package main.java.xadrez.jogo;

import java.util.Scanner;

import main.java.xadrez.jogo.Jogador;
import main.java.xadrez.pecas.Bispo;
import main.java.xadrez.pecas.Cavalo;
import main.java.xadrez.pecas.Peao;
import main.java.xadrez.pecas.Rainha;
import main.java.xadrez.pecas.Rei;
import main.java.xadrez.pecas.Torre;
import main.java.xadrez.pecas.interfacePeca.Peca;
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
    private Peca[] pecas;
    private TabuleiroXadrez tabuleiro;
    private Jogador jogadorBrancas;
    private Jogador jogadorPretas;
    private Jogador jogadorAtual;
    private String historicoJogadas;

    // Construtor da classe Jogo
    public Jogo() {
        this.tabuleiro = new TabuleiroXadrez();
        inicializarPecas();
    }

    public void iniciarJogo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do jogador que utilizará as peças brancas: ");
        String nomeJogadorBrancas = scanner.nextLine();

        System.out.println("Digite o nome do jogador que utilizará as peças pretas: ");
        String nomeJogadorPretas = scanner.nextLine();

        this.jogadorBrancas = new Jogador(nomeJogadorBrancas, "WHITE");
        this.jogadorPretas = new Jogador(nomeJogadorPretas, "BLACK");

        // brancas começam o jogo
        this.jogadorAtual = jogadorBrancas;
        
        scanner.close();
    }

    private void inicializarPecas() {
        pecas = new Peca[32];
        int index = 0;

        pecas[index++] = new Torre("WHITE");
        pecas[index++] = new Cavalo("WHITE");
        pecas[index++] = new Bispo("WHITE");
        pecas[index++] = new Rainha("WHITE");
        pecas[index++] = new Rei("WHITE");
        pecas[index++] = new Bispo("WHITE");
        pecas[index++] = new Cavalo("WHITE");
        pecas[index++] = new Torre("WHITE");
        for (int i = 0; i < 8; i++) {
            pecas[index++] = new Peao("WHITE");
        }

        pecas[index++] = new Torre("BLACK");
        pecas[index++] = new Cavalo("BLACK");
        pecas[index++] = new Bispo("BLACK");
        pecas[index++] = new Rainha("BLACK");
        pecas[index++] = new Rei("BLACK");
        pecas[index++] = new Bispo("BLACK");
        pecas[index++] = new Cavalo("BLACK");
        pecas[index++] = new Torre("BLACK");
        for (int i = 0; i < 8; i++) {
            pecas[index++] = new Peao("BLACK");
        }

        posicionarPecasNoTabuleiro();
    }

    private void posicionarPecasNoTabuleiro() {
        tabuleiro.getCasas()[7][0].colocarPeca(pecas[0]);
        tabuleiro.getCasas()[7][1].colocarPeca(pecas[1]);
        tabuleiro.getCasas()[7][2].colocarPeca(pecas[2]);
        tabuleiro.getCasas()[7][3].colocarPeca(pecas[3]);
        tabuleiro.getCasas()[7][4].colocarPeca(pecas[4]);
        tabuleiro.getCasas()[7][5].colocarPeca(pecas[5]);
        tabuleiro.getCasas()[7][6].colocarPeca(pecas[6]);
        tabuleiro.getCasas()[7][7].colocarPeca(pecas[7]);
        for (int i = 0; i < 8; i++) {
            tabuleiro.getCasas()[6][i].colocarPeca(pecas[8 + i]);
        }

        tabuleiro.getCasas()[0][0].colocarPeca(pecas[16]);
        tabuleiro.getCasas()[0][1].colocarPeca(pecas[17]);
        tabuleiro.getCasas()[0][2].colocarPeca(pecas[18]);
        tabuleiro.getCasas()[0][3].colocarPeca(pecas[19]);
        tabuleiro.getCasas()[0][4].colocarPeca(pecas[20]);
        tabuleiro.getCasas()[0][5].colocarPeca(pecas[21]);
        tabuleiro.getCasas()[0][6].colocarPeca(pecas[22]);
        tabuleiro.getCasas()[0][7].colocarPeca(pecas[23]);
        for (int i = 0; i < 8; i++) {
            tabuleiro.getCasas()[1][i].colocarPeca(pecas[24 + i]);
        }
    }

    // Verifica se a jogada é válida ou não
    private boolean jogadaValida(int linhaO, char colunaO, int linhaD, char colunaD) {
        /*
         * Verifica se a jogada do jogador é válida com base nas regras do jogo.
         * Verifica se a peça pode se mover para a nova posição e se a jogada é legal.
         */
        return false; // Implementação necessária
    }

    // Se a jogada for válida, atualiza o tabuleiro e a situação do jogo
    public void realizarJogada(int linhaO, char colunaO, int linhaD, char colunaD) {
        /*
         * Atualiza o tabuleiro com a jogada do jogador -> mudando a peça de lugar após a jogada ter sido efetivamente validada
         * Altera o estado do jogo, atualiza as peças capturadas.
         */

        if (jogadaValida(linhaO, colunaO, linhaD, colunaD)) {
            // coloca a peça em seu lugar

            // exibe o tabuleiro
            desenhoTabuleiroAtualizado();
        }
        scanner.close();
    }

    private void desenhoTabuleiroAtualizado() {
        System.out.println(tabuleiro.desenho());
    }

    // Retorna uma string com todos os dados relevantes do jogo para retomada posterior
    public String registroJogo() {
        /*
         * Retorna uma string que representa o estado atual do jogo, incluindo o histórico de jogadas.
         */
        
        return ""; // Implementação necessária
    }
}
