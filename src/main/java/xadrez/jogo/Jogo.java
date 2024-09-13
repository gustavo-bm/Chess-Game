package main.java.xadrez.jogo;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.xadrez.jogo.Jogador;
import main.java.xadrez.pecas.Bispo;
import main.java.xadrez.pecas.Cavalo;
import main.java.xadrez.pecas.Peao;
import main.java.xadrez.pecas.Rainha;
import main.java.xadrez.pecas.Rei;
import main.java.xadrez.pecas.Torre;
import main.java.xadrez.pecas.interfacePeca.Peca;
import main.java.xadrez.tabuleiro.Casa;
import main.java.xadrez.tabuleiro.TabuleiroXadrez;

/**
 * Essa classe é responsável pelo gerenciamento do jogo, controlando tudo o que
 * acontece. Essa classe contém um tabuleiro,
 * 2 jogadores e o conjunto de 32 peças disponíveis. O jogo sabe o estado em que
 * se encontra a cada momento (por exemplo: início do jogo,
 * xeque, xeque-mate). Sabe também de que jogador é a vez, controlando as
 * jogadas, de quem é a vez, as checagens, etc, sendo a classe
 * responsável por manter as informações na tela e solicitar ao jogador da vez
 * as informações necessárias para a jogada ou interrupção
 * do jogo. No início do jogo, também solicita a cada jogador o seu nome. Na
 * tela, além do tabuleiro, o Jogo deve manter visível as peças
 * de cada jogador que já foram capturadas, desenhadas do lado do tabuleiro
 * correspondente ao jogador.
 * 
 * A cada jogada solicitada por um jogador, o Jogo é a classe que dispara a
 * jogada checando se ela é válida, atualizando o tabuleiro,
 * a situação, o histórico de jogadas, a tela, a situação do jogo, das peças,
 * etc, usando sempre as chamadas de métodos das outras
 * classes que forem apropriados.
 * 
 * Todas as jogadas efetivamente realizadas devem ser registradas em um
 * histórico de jogadas que pode ser solicitado pelo Gerenciador
 * para armazenamento em arquivo ou visualização das jogadas.
 * 
 * A classe Jogo deve ter ao menos os métodos esboçados abaixo (eu, gu, ja
 * coloquei); talvez precise de mais métodos
 * 
 */

public class Jogo {
    private Peca[] pecas;
    private TabuleiroXadrez tabuleiro;
    private Casa[][] casas;
    private Jogador jogadorBrancas;
    private Jogador jogadorPretas;
    private Jogador jogadorAtual;
    private String estado;
    private StringBuilder historicoJogadas;

    // Construtor da classe Jogo
    public Jogo() {
        this.tabuleiro = new TabuleiroXadrez();
        this.casas = tabuleiro.getCasas();
        this.estado = "ativo";
        inicializarPecas();
    }

    public void iniciarJogo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do jogador que utilizará as peças brancas: ");
        String nomeJogadorBrancas = scanner.nextLine();

        System.out.print("Digite o nome do jogador que utilizará as peças pretas: ");
        String nomeJogadorPretas = scanner.nextLine();

        this.jogadorBrancas = new Jogador(nomeJogadorBrancas, "WHITE");
        this.jogadorPretas = new Jogador(nomeJogadorPretas, "BLACK");

        // brancas começam o jogo
        this.jogadorAtual = jogadorBrancas;

        iniciaJogadas();
        scanner.close();
    }

    private void inicializarPecas() {
        pecas = new Peca[32];
        int index = 0;

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

        posicionarPecasNoTabuleiro();
    }

    private void posicionarPecasNoTabuleiro() {
        casas[7][0].colocarPeca(pecas[0]);
        casas[7][1].colocarPeca(pecas[1]);
        casas[7][2].colocarPeca(pecas[2]);
        casas[7][3].colocarPeca(pecas[3]);
        casas[7][4].colocarPeca(pecas[4]);
        casas[7][5].colocarPeca(pecas[5]);
        casas[7][6].colocarPeca(pecas[6]);
        casas[7][7].colocarPeca(pecas[7]);
        for (int i = 0; i < 8; i++) {
            casas[6][i].colocarPeca(pecas[8 + i]);
        }

        casas[0][0].colocarPeca(pecas[16]);
        casas[0][1].colocarPeca(pecas[17]);
        casas[0][2].colocarPeca(pecas[18]);
        casas[0][3].colocarPeca(pecas[19]);
        casas[0][4].colocarPeca(pecas[20]);
        casas[0][5].colocarPeca(pecas[21]);
        casas[0][6].colocarPeca(pecas[22]);
        casas[0][7].colocarPeca(pecas[23]);
        for (int i = 0; i < 8; i++) {
            casas[1][i].colocarPeca(pecas[24 + i]);
        }
    }

    public void iniciaJogadas() {
        desenhoJogoAtualizado();

        while (estado.equals("ativo")) {

            System.out.print("Vez das ");
            if (jogadorAtual.getCor() == "WHITE") {
                System.out.println("brancas.");
            } else {
                System.out.println("pretas.");
            }

            String jogada = jogadorAtual.informaJogada();

            if (jogada.length() == 4) {
                int linhaOrigem = Character.getNumericValue(jogada.charAt(0)) - 1;
                char colunaOrigem = jogada.charAt(1);
                int linhaDestino = Character.getNumericValue(jogada.charAt(2)) - 1;
                char colunaDestino = jogada.charAt(3);
                int colunaOrigemInt = colunaOrigem - 'a';
                int colunaDestinoInt = colunaDestino - 'a';

                realizarJogada(linhaOrigem, colunaOrigemInt, linhaDestino, colunaDestinoInt);

            } else {
                if (jogada.equals("parar")) {
                    estado = "inativo";
                }
                System.out.println("Erro: a jogada deve conter exatamente 4 caracteres.");
            }
        }
    }

    public void realizarJogada(int linhaO, int colunaO, int linhaD, int colunaD) {
        Jogada jogada = new Jogada(casas[linhaO][colunaO], casas[linhaD][colunaD]);
        Jogador jogadorAdversario = (jogadorAtual == jogadorBrancas) ? jogadorPretas : jogadorBrancas;

        Casa casaOrigem = casas[linhaO][colunaO];
        Casa casaDestino = casas[linhaD][colunaD];
        Peca pecaOrigem = casaOrigem.getPeca();
        Peca pecaDestino = casaDestino.getPeca();

        boolean confirmarJogada = false;
        boolean saiuDoXeque = false;
        boolean captura = false;

        if (jogadaValida(jogada)) {
            casaOrigem.removerPeca();
            casaDestino.colocarPeca(pecaOrigem);

            if (jogada.ehCaptura(casaDestino, jogadorAtual.getCor())) {
                captura = true;
            }

            if (jogadorAtual.getEstaEmXeque()) {
                if (jogada.saiDoXeque(tabuleiro, jogadorAtual, jogadorAdversario)) {
                    jogadorAtual.setEstaEmXeque(false);
                    confirmarJogada = true;
                } else {
                    System.out.println("Informe uma jogada para sair do xeque.");
                    confirmarJogada = false;
                }
            } else {
                confirmarJogada = true;
            }

            if (jogada.ehXeque(tabuleiro, jogadorAtual, jogadorAdversario)) {
                jogadorAdversario.setEstaEmXeque(true);
                System.out.println("Xeque!");
                confirmarJogada = true;
            }

            if (saiuDoXeque && !jogadorAtual.getEstaEmXeque() && jogada.entraEmXeque(tabuleiro, jogadorAtual, jogadorAdversario)) {
                System.out.println("A jogada informada te coloca em xeque!");
                confirmarJogada = false;
            }

            if (confirmarJogada) {
                if (captura) {
                    jogadorAtual.capturarPeca(pecaDestino);
                }
                System.out.println("Jogada realizada.");
                jogadorAtual = (jogadorAtual == jogadorBrancas) ? jogadorPretas : jogadorBrancas;
                desenhoJogoAtualizado();
            } else {
                casaOrigem.colocarPeca(pecaOrigem);
                casaDestino.removerPeca();
            }
        }
    }

    private boolean jogadaValida(Jogada jogada) {
        /*
         * Verifica se a jogada do jogador é válida com base nas regras do jogo.
         * Verifica se a peça pode se mover para a nova posição e se a jogada é legal.
         */
        return jogada.ehValida(tabuleiro, jogadorAtual);
    }

    public void desenhoJogoAtualizado() {
        System.out.println();
        System.out.println("Peças capturadas por " + jogadorPretas.getNome() + ": " + jogadorBrancas.pecasCapturadas());
        System.out.println();
        System.out.println();
        System.out.println(tabuleiro.desenho());
        System.out.println();
        System.out.println("Peças capturadas por " + jogadorBrancas.getNome() + " " + jogadorPretas.pecasCapturadas());
    }

    // Retorna uma string com todos os dados relevantes do jogo para retomada
    // posterior
    public String registroJogo() {
        /*
         * Retorna uma string que representa o estado atual do jogo, incluindo o
         * histórico de jogadas.
         */
        return "Estado do jogo: " + this.estado + "\n\n" + jogadorBrancas.getNome() + " - Peças brancas\n"
                + jogadorPretas.getNome() + " - Peças pretas\n\n" + "Histórico de jogadas:\n" + this.historicoJogadas;
    }
}