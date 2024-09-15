package main.java.xadrez.jogo;

import main.java.xadrez.pecas.Cavalo;
import main.java.xadrez.pecas.Peao;
import main.java.xadrez.pecas.Rei;
import main.java.xadrez.pecas.interfacePeca.Peca;
import main.java.xadrez.tabuleiro.Caminho;
import main.java.xadrez.tabuleiro.Casa;
import main.java.xadrez.tabuleiro.TabuleiroXadrez;

/*
 * Uma jogada é criada a partir das informações do jogador que a está realizando, a posição inicial e posição final da jogada, 
 * mas deve manter o caminho com base nessas informações iniciais (veja o diagrama: mostra basicamente que a classe Caminho tem uma relação
 * de composição com relação à classe Jogada, ou seja, são criadas instâncias de objetos da classe Caminho aqui para acessar seus métodos e/ou
 * atributos).
 * 
 * Lembrar que cada peça é quem deve fornecer seu caminho (na forma de uma string) e identificar se o movimento é válido de acordo com sua forma
 *  de se movimentar. Uma vez criada, uma jogada não pode ser alterada.
 */
public class Jogada {
    private Caminho caminho;

    public Jogada(Casa casaInicial, Casa casaFinal) {
        this.caminho = new Caminho(casaInicial, casaFinal);
    }

    public boolean ehValida(TabuleiroXadrez tabuleiro, Jogador jogadorAtual) {
        Casa casaInicial = caminho.casaInicial();
        Casa casaFinal = caminho.casaFinal();

        if (casaInicial.getPeca() == null)
            return false;

        Peca pecaInicial = casaInicial.getPeca();
        String corJogadorAtual = jogadorAtual.getCor();

        if (posicoesDentroDoLimite(tabuleiro, casaInicial, casaFinal)
                && pecaPertenceAoJogador(pecaInicial, corJogadorAtual)
                && (destinoLivre(casaFinal, corJogadorAtual) || ehCaptura(casaFinal, corJogadorAtual))
                && (caminhoEstaLivre(pecaInicial, casaInicial, casaFinal, tabuleiro)
                        || pecaEUmCavalo(pecaInicial))
                && movimentoValidoParaPeca(pecaInicial, casaInicial, casaFinal, corJogadorAtual)) {
            return true;
        }

        // System.out.println("Posições dentro do limite: " +
        // posicoesDentroDoLimite(tabuleiro, casaInicial, casaFinal));
        // System.out.println("Peça pertence ao jogador: " +
        // pecaPertenceAoJogador(pecaInicial, corJogadorAtual));
        // System.out.println("Destino livre ou é captura: "
        // + (destinoLivre(casaFinal, corJogadorAtual) || ehCaptura(casaFinal,
        // corJogadorAtual)));
        // System.out.println("Caminho está livre ou é um cavalo: "
        // + (caminhoEstaLivre(pecaInicial, casaInicial, casaFinal, tabuleiro) ||
        // pecaEUmCavalo(pecaInicial)));
        // System.out.println("Movimento válido para a peça: "
        // + movimentoValidoParaPeca(pecaInicial, casaInicial, casaFinal,
        // corJogadorAtual));

        return false;
    }

    private boolean posicoesDentroDoLimite(TabuleiroXadrez tabuleiro, Casa inicial, Casa fim) {
        return tabuleiro.noLimite(inicial.getLinha(), inicial.getColuna())
                && tabuleiro.noLimite(fim.getLinha(), fim.getColuna());
    }

    private boolean pecaPertenceAoJogador(Peca peca, String corJogador) {
        return peca.getCor().equals(corJogador);
    }

    private boolean destinoLivre(Casa casaFinal, String corJogador) {
        return casaFinal.estaLivre();
    }

    public boolean ehCaptura(Casa casaFinal, String corJogador) {
        return casaFinal.getPeca() != null && !casaFinal.getPeca().getCor().equals(corJogador);
    }

    private boolean caminhoEstaLivre(Peca peca, Casa inicial, Casa fim, TabuleiroXadrez tabuleiro) {
        return caminho.estaLivre(peca.caminho(inicial.getLinha(), inicial.getColuna(),
                fim.getLinha(), fim.getColuna()), tabuleiro);
    }

    private boolean pecaEUmCavalo(Peca peca) {
        return peca instanceof Cavalo;
    }

    private boolean movimentoValidoParaPeca(Peca peca, Casa inicial, Casa fim, String corJogadorAtual) {
        int valido = peca.movimentoValido(inicial.getLinha(), inicial.getColuna(), fim.getLinha(), fim.getColuna());

        if (valido == 1) {
            return true;
        } else if (ehCaptura(fim, corJogadorAtual) && valido == 2) {
            return true;
        }

        return false;
    }

    // verifica se a jogada do jogador atual deixa o jogador adversario em xeque
    public boolean ehXeque(TabuleiroXadrez tabuleiro, Jogador jogadorAtual, Jogador jogadorAdversario) {
        Casa[][] casas = tabuleiro.getCasas();
        Casa casaRei = null;

        // Achar a posição do Rei adversário
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca pecaAtual = casas[i][j].getPeca();
                if (pecaAtual instanceof Rei && pecaAtual.getCor().equals(jogadorAdversario.getCor())) {
                    casaRei = casas[i][j];
                    break;
                }
            }
            if (casaRei != null)
                break;
        }

        if (casaRei == null) {
            throw new IllegalStateException("O Rei do adversário não foi encontrado.");
        }

        // Verificar se alguma peça do jogador atual pode capturar o Rei adversário
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca pecaAtual = casas[i][j].getPeca();
                if (pecaAtual != null && pecaAtual.getCor().equals(jogadorAtual.getCor())) {
                    if (ehCaptura(casaRei, jogadorAtual.getCor())
                            && caminhoEstaLivre(pecaAtual, casas[i][j], casaRei, tabuleiro)
                            && movimentoValidoParaPeca(pecaAtual, casas[i][j], casaRei, jogadorAtual.getCor())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // verifica se a jogada do jogador atual em xeque o tira do xeque
    public boolean saiDoXeque(TabuleiroXadrez tabuleiro, Jogador jogadorAtual, Jogador jogadorAdversario) {
        Casa[][] casas = tabuleiro.getCasas();
        Casa casaRei = null;

        // Achar a posição do Rei do jogador atual
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca pecaAtual = casas[i][j].getPeca();
                if (pecaAtual instanceof Rei && pecaAtual.getCor().equals(jogadorAtual.getCor())) {
                    casaRei = casas[i][j];
                    break;
                }
            }
            if (casaRei != null)
                break;
        }

        if (casaRei == null) {
            throw new IllegalStateException("O Rei do jogador atual não foi encontrado.");
        }

        // Verificar se alguma peça do adversário pode capturar o Rei após o movimento
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca pecaAtual = casas[i][j].getPeca();
                if (pecaAtual != null && pecaAtual.getCor().equals(jogadorAdversario.getCor())) {
                    if (ehCaptura(casaRei, jogadorAdversario.getCor())
                            && caminhoEstaLivre(pecaAtual, casas[i][j], casaRei, tabuleiro)
                            && movimentoValidoParaPeca(pecaAtual, casas[i][j], casaRei, jogadorAdversario.getCor())) {
                        return false; // O jogador ainda está em xeque
                    }
                }
            }
        }

        return true; // Saiu do xeque
    }

    // verifica se a jogada do jogador atual o coloca em xeque
    public boolean entraEmXeque(TabuleiroXadrez tabuleiro, Jogador jogadorAtual, Jogador jogadorAdversario) {
        Casa[][] casas = tabuleiro.getCasas();
        Casa casaRei = null;

        // Achar a posição do Rei do jogador atual
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca pecaAtual = casas[i][j].getPeca();
                if (pecaAtual instanceof Rei && pecaAtual.getCor().equals(jogadorAtual.getCor())) {
                    casaRei = casas[i][j];
                    break;
                }
            }
            if (casaRei != null)
                break;
        }

        if (casaRei == null) {
            throw new IllegalStateException("O Rei do jogador atual não foi encontrado.");
        }

        // Verificar se alguma peça do adversário pode capturar o Rei após o movimento
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca pecaAtual = casas[i][j].getPeca();
                if (pecaAtual != null && pecaAtual.getCor().equals(jogadorAdversario.getCor())) {
                    if (ehCaptura(casaRei, jogadorAdversario.getCor())
                            && caminhoEstaLivre(pecaAtual, casas[i][j], casaRei, tabuleiro)
                            && movimentoValidoParaPeca(pecaAtual, casas[i][j], casaRei, jogadorAdversario.getCor())) {
                        return true; // O jogador está em xeque
                    }
                }
            }
        }

        return false;
    }

    // Verifica se a jogada levou a uma situação de xeque mate
    /*
     * A função deve verificar se o rei do oponente está em xeque mate após a
     * jogada.
     */
    public boolean ehXequeMate(TabuleiroXadrez tabuleiro, Jogador jogador, Casa[][] casas) {
        Casa casaRei = null;
        int linhaRei;
        int colunaRei;

        int[] movimentosLinha = { -1, -1, -1, 0, 1, 1, 1, 0 };
        int[] movimentosColuna = { -1, 0, 1, 1, 1, 0, -1, -1 };

        Peca pecaDestino = casaDestino.getPeca();

        // Achar a posição do Rei do jogador atual
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca pecaAtual = casas[i][j].getPeca();
                if (pecaAtual instanceof Rei && pecaAtual.getCor().equals(jogadorAtual.getCor())) {
                    casaRei = casas[i][j];
                    break;
                }
            }
            if (casaRei != null)
                break;
        }

        if (casaRei == null) {
            throw new IllegalStateException("O Rei do jogador atual não foi encontrado.");
        }

        // testar se algum movimento do rei o tira de xeque
        for (int i = 0; i < 8; i++) {
            movimentoValidoParaPeca(casaRei.getPeca(), casaRei, casas[movimentosLinha[i]][movimentosColuna[i]],
                    jogador.getCor());

            // Verificar se alguma peça do adversário pode capturar o Rei após o movimento
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Peca pecaAtual = casas[j][k].getPeca();
                    if (pecaAtual != null && pecaAtual.getCor().equals(jogadorAdversario.getCor())) {
                        if (ehCaptura(casaRei, jogadorAdversario.getCor())
                                && caminhoEstaLivre(pecaAtual, casas[i][k], casaRei, tabuleiro)
                                && movimentoValidoParaPeca(pecaAtual, casas[i][k], casaRei,
                                        jogadorAdversario.getCor())) {
                            return false; // O jogador ainda está em xeque
                        }
                    }
                }
            }
        }

        return false; // Implementação necessária
    }
}
