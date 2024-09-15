package main.java.xadrez;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.xadrez.jogo.Arquivo;
import main.java.xadrez.jogo.Jogada;
import main.java.xadrez.jogo.Jogador;
import main.java.xadrez.jogo.Jogo;
import main.java.xadrez.pecas.Bispo;
import main.java.xadrez.pecas.Cavalo;
import main.java.xadrez.pecas.Rainha;
import main.java.xadrez.pecas.Torre;
import main.java.xadrez.pecas.interfacePeca.Peca;
import main.java.xadrez.tabuleiro.Caminho;
import main.java.xadrez.tabuleiro.Casa;
import main.java.xadrez.tabuleiro.TabuleiroXadrez;

public class Gerenciador {
    private String caminhoArquivo;

    public Gerenciador() {

    }

    private void testarTorre() {
        Peca torre = new Torre("WHITE");

        System.out.println(torre.movimentoValido(1, 'a', 1, 'b')); // true
        System.out.println(torre.movimentoValido(1, 'a', 4, 'a')); // true
        System.out.println(torre.movimentoValido(1, 'a', 2, 'b')); // false

        System.out.println();
        System.out.println(torre.caminho(1, 'a', 1, 'b')); // 1a1b
        System.out.println(torre.caminho(1, 'a', 4, 'a')); // 1a2a3a4a
        System.out.println(torre.caminho(5, 'b', 2, 'b')); // 5b4b3b2b
    }

    private void testarTabuleiroComEsemPecas() {
        // TabuleiroXadrez tabuleiro1 = new TabuleiroXadrez();
        // System.out.println(tabuleiro1.desenho());

        Jogo jogo1 = new Jogo();
        jogo1.desenhoJogoAtualizado();
    }

    private void testarColocacaoDePecasNoTabuleiro() {
        TabuleiroXadrez tabuleiro1 = new TabuleiroXadrez();
        Casa[][] casas = tabuleiro1.getCasas();

        Peca cavalo = new Cavalo("WHITE");

        Peca torre = new Torre("WHITE");
        casas[0][0].colocarPeca(torre);

        Peca rainha = new Rainha("BLACK");

        System.out.println("caminho da rainha: " + rainha.caminho(1, 'a', 8, 'h'));

        String caminhoCavalo = cavalo.caminho(8, 'b', 6, 'c');
        System.out.println("caminho do cavalo: " + caminhoCavalo);

        casas[7][1].colocarPeca(cavalo);
        Caminho caminho = new Caminho(casas[7][1], casas[5][2]);
        System.out.println(caminho.estaLivre(caminhoCavalo, tabuleiro1));

        System.out.println(tabuleiro1.desenho());
    }

    private void testarJogadorEJogadas() {
        TabuleiroXadrez tabuleiro1 = new TabuleiroXadrez();
        Casa[][] casas = tabuleiro1.getCasas();
        Peca torre = new Torre("WHITE");
        casas[0][0].colocarPeca(torre);

        Jogador jogador1 = new Jogador("GU", "WHITE");

        Jogada jogada1 = new Jogada(casas[0][0], casas[0][4]);
        Jogada jogada2 = new Jogada(casas[0][0], casas[1][4]);
        Jogada jogada3 = new Jogada(casas[0][5], casas[0][4]);
        Jogada jogada4 = new Jogada(casas[0][0], casas[0][5]);

        System.out.println("Jogada1 válida: " + jogada1.ehValida(tabuleiro1, jogador1)); // true
        System.out.println("Jogada2 válida: " + jogada2.ehValida(tabuleiro1, jogador1)); // false
        System.out.println("Jogada3 válida: " + jogada3.ehValida(tabuleiro1, jogador1)); // false
        System.out.println("Jogada4 válida: " + jogada4.ehValida(tabuleiro1, jogador1)); // true
    }

    private void testarCavalo() {
        Jogador jogador = new Jogador("gu", "BLACK");
        TabuleiroXadrez tabuleiro1 = new TabuleiroXadrez();
        Casa[][] casas = tabuleiro1.getCasas();
        Peca cavalo = new Cavalo("BLACK");

        String caminhoCavalo = cavalo.caminho(8, 'b', 6, 'c');
        System.out.println("caminho do cavalo: " + caminhoCavalo);

        casas[7][1].colocarPeca(cavalo);
        Caminho caminho = new Caminho(casas[7][1], casas[5][2]);
        System.out.print("Caminho livre:");
        System.out.println(caminho.estaLivre(caminhoCavalo, tabuleiro1));

        Jogada jogadaCavalo = new Jogada(casas[7][1], casas[5][2]);
        System.out.print("Jogada valida? ");
        System.out.println(jogadaCavalo.ehValida(tabuleiro1, jogador));

        System.out.println(tabuleiro1.desenho());

    }

    private void testarRainha() {
        Jogador jogador = new Jogador("gu", "BLACK");
        TabuleiroXadrez tabuleiro1 = new TabuleiroXadrez();
        Casa[][] casas = tabuleiro1.getCasas();
        Peca rainha = new Rainha("BLACK");

        String caminhoRainha = rainha.caminho(1, 'a', 6, 'f');
        System.out.println("caminho da rainha: " + caminhoRainha);

        casas[0][0].colocarPeca(rainha);
        Caminho caminho = new Caminho(casas[0][0], casas[5][5]);
        System.out.print("Caminho livre:");
        System.out.println(caminho.estaLivre(caminhoRainha, tabuleiro1));

        Jogada jogadaRainha = new Jogada(casas[0][0], casas[5][5]);
        System.out.print("Jogada valida? ");
        System.out.println(jogadaRainha.ehValida(tabuleiro1, jogador));

        System.out.println(tabuleiro1.desenho());

    }

    private void rodarJogo() {
        Jogo jogo = new Jogo();
        Arquivo arquivo = new Arquivo();

        // Antes de iniciar o jogo tradicionalmente, ver se o usuário quer carregar um
        // jogo salvo
        Scanner scanner = new Scanner(System.in);

        int opcao = -1;

        do {
            System.out.println("Digite 1 se quer começar um jogo do zero ou 2 se deseja carregar um jogo salvo.");
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        } while (opcao != 1 && opcao != 2);

        if (opcao == 1) {
            jogo.iniciarJogo();
            if (jogo.getJogoStatus().equals("inativo")) {
                scanner.nextLine();
                System.out.print("Informe o nome do arquivo para salvar o jogo: ");

                try {
                    caminhoArquivo = scanner.nextLine();
                    arquivo.setCaminhoArquivo(caminhoArquivo);
                    arquivo.salvarJogo(jogo.registroJogo());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (opcao == 2) {
            scanner.nextLine(); // Consumir quebra de linha pendente do nextInt anterior
            System.out.print("Informe o nome do arquivo que deseja recuperar: ");
            caminhoArquivo = scanner.nextLine();
            arquivo.setCaminhoArquivo(caminhoArquivo);

            jogo.setJogoStatus("inativo");

            String infoJogo = arquivo.restaurarJogo();

            // Separar as linhas do conteúdo do arquivo
            String[] linhas = infoJogo.split("\n");

            String nomeJogadorBrancas = linhas[2].split("-")[1].trim();
            String nomeJogadorPretas = linhas[3].split("-")[1].trim();

            // Configurar os jogadores
            jogo.criaJogadores(nomeJogadorBrancas, nomeJogadorPretas);

            // As jogadas começam a partir da sétima linha (índice 6) no registro do jogo
            for (int i = 6; i < linhas.length; i++) {
                String jogada = linhas[i];

                if (jogada.length() == 4) {
                    // Convertendo a jogada para linha e coluna
                    int linhaOrigem = Character.getNumericValue(jogada.charAt(0)) - 1;
                    char colunaOrigem = jogada.charAt(1);
                    int linhaDestino = Character.getNumericValue(jogada.charAt(2)) - 1;
                    char colunaDestino = jogada.charAt(3);

                    int colunaOrigemInt = colunaOrigem - 'a';
                    int colunaDestinoInt = colunaDestino - 'a';

                    jogo.realizarJogada(linhaOrigem, colunaOrigemInt, linhaDestino, colunaDestinoInt, jogada);
                } else {
                    System.out.println("Erro na leitura da jogada: " + jogada);
                }
            }

            jogo.setJogoStatus("ativo");
            jogo.iniciaJogadas(scanner);
        }

    }

    public void teste() {
        // testarTorre();
        // testarColocacaoDePecasNoTabuleiro();
        // testarJogadorEJogadas();
        // testarTabuleiroComEsemPecas();
        // testarCavalo();
        // testarRainha();
        rodarJogo();
    }

    public static void main(String[] args) {
        Gerenciador gerenciador = new Gerenciador();
        gerenciador.rodarJogo();
    }
}
