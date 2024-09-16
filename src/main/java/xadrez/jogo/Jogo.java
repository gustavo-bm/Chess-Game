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

public class Jogo {
    private TabuleiroXadrez tabuleiro;
    private Casa[][] casas;
    private Peca[] pecas;
    private Jogador jogadorBrancas;
    private Jogador jogadorPretas;
    private Jogador jogadorAtual;
    private Jogador vencedor;
    private StringBuilder historicoJogadas;
    private String jogoStatus;
    private String estado;

    public Jogo() {
        this.tabuleiro = new TabuleiroXadrez();
        this.casas = tabuleiro.getCasas();
        this.historicoJogadas = new StringBuilder();
        this.estado = "inicio-jogo";
        this.jogoStatus = "ativo";
        inicializarPecas();
    }

    public void setJogoStatus(String jogoStatus) {
        this.jogoStatus = jogoStatus;
    }

    public String getJogoStatus() {
        return jogoStatus;
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

    public void criaJogadores(String nomeJogadorBrancas, String nomeJogadorPretas) {
        this.jogadorBrancas = new Jogador(nomeJogadorBrancas, "WHITE");
        this.jogadorPretas = new Jogador(nomeJogadorPretas, "BLACK");
        this.jogadorAtual = jogadorBrancas;
    }

    public void iniciarJogo() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Digite os nomes dos jogadores.\n");
        System.out.print("Brancas: ");
        String nomeJogadorBrancas = scanner.nextLine();
    
        System.out.print("Pretas: ");
        String nomeJogadorPretas = scanner.nextLine();
    
        criaJogadores(nomeJogadorBrancas, nomeJogadorPretas);
    
       iniciaJogadas(scanner);
    }
    
    public void iniciaJogadas(Scanner scanner) {
        System.out.println("\nBom jogo!\n");
        desenhoJogoAtualizado();

        while (jogoStatus.equals("ativo") && !estado.equals("xeque-mate")) {
            System.out.print("Vez das ");
            if (jogadorAtual.getCor().equals("WHITE")) {
                System.out.println("brancas.");
            } else {
                System.out.println("pretas.");
            }

            String movimento = jogadorAtual.informaJogada(scanner);
    
            if (movimento.length() == 4) {
                try {
                    int linhaOrigem = Character.getNumericValue(movimento.charAt(0)) - 1;
                    char colunaOrigem = movimento.charAt(1);
                    int linhaDestino = Character.getNumericValue(movimento.charAt(2)) - 1;
                    char colunaDestino = movimento.charAt(3);
                    int colunaOrigemInt = colunaOrigem - 'a';
                    int colunaDestinoInt = colunaDestino - 'a';

                    realizarJogada(linhaOrigem, colunaOrigemInt, linhaDestino, colunaDestinoInt, movimento);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
    
                
            } else {
                if (movimento.equals("parar")) {
                    jogoStatus = "inativo";
                    break;
                } else {
                    System.out.println("Erro: a jogada deve conter exatamente 4 caracteres.");
                }
            }
        }
    
        if (estado.equals("xeque-mate")) {
            System.out.println("Xeque-mate!");
            System.out.println("Jogo acabou! Vencedor: " + vencedor.getCor() + "\nParabéns, " + vencedor.getNome());
        } else {
            jogoStatus = "inativo";
        }
    }    

    public void realizarJogada(int linhaO, int colunaO, int linhaD, int colunaD, String movimento) {
        Jogada jogada = new Jogada(casas[linhaO][colunaO], casas[linhaD][colunaD]);
        Jogador jogadorAdversario = (jogadorAtual == jogadorBrancas) ? jogadorPretas : jogadorBrancas;

        Casa casaOrigem = casas[linhaO][colunaO];
        Casa casaDestino = casas[linhaD][colunaD];
        Peca pecaOrigem = casaOrigem.getPeca();
        Peca pecaDestino = casaDestino.getPeca();

        boolean confirmarJogada = false;
        boolean captura = false;

        if (jogadaValida(jogada)) {

            if (jogada.ehCaptura(casaDestino, jogadorAtual.getCor())) {
                captura = true;
            }

            casaOrigem.removerPeca();
            casaDestino.colocarPeca(pecaOrigem);

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

                // checar se é xeque mate
                if (jogada.ehXequeMate(tabuleiro, jogadorAtual, jogadorAdversario, casas)) {
                    estado = "xeque-mate";
                    vencedor = jogadorAtual;
                } else {
                    System.out.println("Xeque!");
                }
                confirmarJogada = true;
            }

            if (!jogadorAtual.getEstaEmXeque() && jogada.entraEmXeque(tabuleiro, jogadorAtual, jogadorAdversario)) {
                System.out.println("A jogada informada te coloca em xeque!");
                confirmarJogada = false;
            }

            if (confirmarJogada) {
                if (captura) {
                    jogadorAtual.capturarPeca(pecaDestino);

                    if (jogoStatus.equals("ativo")) {
                        System.out.println("Peça capturada: " + pecaDestino.desenho());
                    }
                }

                if (!jogadorAdversario.getEstaEmXeque() && jogoStatus.equals("ativo")) {
                    System.out.println("Jogada realizada.");
                    historicoJogadas.append(movimento + "\n");
                }

                jogadorAtual = (jogadorAtual == jogadorBrancas) ? jogadorPretas : jogadorBrancas;

                if (jogoStatus.equals("ativo")) {
                    desenhoJogoAtualizado();
                }
            } else {
                casaOrigem.colocarPeca(pecaOrigem);

                if (captura) {
                    casaDestino.colocarPeca(pecaDestino);
                } else {
                    casaDestino.removerPeca();
                }
            }

        } else {
            System.out.println("Jogada inválida.");
        }
    }

    private boolean jogadaValida(Jogada jogada) {
        return jogada.ehValida(tabuleiro, jogadorAtual);
    }

    public void desenhoJogoAtualizado() {
        System.out.println("====================================");
        System.out.println();
        System.out.println("Peças capturadas por " + jogadorBrancas.getNome() + ":\n" + jogadorBrancas.pecasCapturadas());
        System.out.println();
        System.out.println(tabuleiro.desenho());
        System.out.println();
        System.out.println("Peças capturadas por " + jogadorPretas.getNome() + ":\n" + jogadorPretas.pecasCapturadas() + "\n");
        System.out.println("====================================");
    }

    public String registroJogo() {
        return "Estado do jogo: " + this.estado + "\n\n" + jogadorBrancas.getNome() + " - Pecas brancas\n"
                + jogadorPretas.getNome() + " - Pecas pretas\n\n" + "Historico de jogadas:\n" + this.historicoJogadas;
    }
}