package main.java.xadrez.jogo;

import main.java.xadrez.pecas.Cavalo;
import main.java.xadrez.pecas.Peao;
import main.java.xadrez.pecas.Rei;
import main.java.xadrez.pecas.interfacePeca.Peca;
import main.java.xadrez.tabuleiro.Caminho;
import main.java.xadrez.tabuleiro.Casa;
import main.java.xadrez.tabuleiro.TabuleiroXadrez;

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
                && (destinoLivre(casaFinal) || ehCaptura(casaFinal, corJogadorAtual))
                && (caminhoEstaLivre(pecaInicial, casaInicial, casaFinal, tabuleiro)
                        || pecaEUmCavalo(pecaInicial))
                && movimentoValidoParaPeca(pecaInicial, casaInicial, casaFinal, corJogadorAtual)) {
            return true;
        }

        return false;
    }

    private boolean posicoesDentroDoLimite(TabuleiroXadrez tabuleiro, Casa inicial, Casa fim) {
        return tabuleiro.noLimite(inicial.getLinha(), inicial.getColuna())
                && tabuleiro.noLimite(fim.getLinha(), fim.getColuna());
    }

    private boolean pecaPertenceAoJogador(Peca peca, String corJogador) {
        return peca.getCor().equals(corJogador);
    }

    private boolean destinoLivre(Casa casaFinal) {
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

    // acha a posição do Rei do jogador
    private Casa encontrarCasaRei(Jogador jogador, Casa[][] casas) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca pecaAtual = casas[i][j].getPeca();

                if (pecaAtual != null && pecaAtual instanceof Rei) {
                    if (pecaAtual.getCor().equals(jogador.getCor())) {
                        return casas[i][j];
                    }
                }
            }
        }

        return null;
    }

    // verifica se uma peça pode capturar o Rei
    private boolean podeCapturarRei(Jogador jogador, TabuleiroXadrez tabuleiro, Casa casaRei, Casa[][] casas) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca pecaAtual = casas[i][j].getPeca();
                if (pecaAtual != null && pecaAtual.getCor().equals(jogador.getCor())) {
                    // verifica se a peça pode capturar o Rei
                    if (ehCaptura(casaRei, jogador.getCor())
                            && caminhoEstaLivre(pecaAtual, casas[i][j], casaRei, tabuleiro)
                            && movimentoValidoParaPeca(pecaAtual, casas[i][j], casaRei, jogador.getCor())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // verifica se a jogada do jogador atual deixa o jogador adversário em xeque
    public boolean ehXeque(TabuleiroXadrez tabuleiro, Jogador jogadorAtual, Jogador jogadorAdversario) {
        Casa[][] casas = tabuleiro.getCasas();
        Casa casaReiAdversario = encontrarCasaRei(jogadorAdversario, casas);

        if (casaReiAdversario == null) {
            throw new IllegalStateException("O Rei do adversário não foi encontrado.");
        }

        return podeCapturarRei(jogadorAtual, tabuleiro, casaReiAdversario, casas);
    }

    // verifica se a jogada do jogador atual em xeque o tira do xeque
    public boolean saiDoXeque(TabuleiroXadrez tabuleiro, Jogador jogadorAtual, Jogador jogadorAdversario) {
        Casa[][] casas = tabuleiro.getCasas();
        Casa casaReiJogadorAtual = encontrarCasaRei(jogadorAtual, casas);

        if (casaReiJogadorAtual == null) {
            throw new IllegalStateException("O Rei do jogador atual não foi encontrado.");
        }

        return !podeCapturarRei(jogadorAdversario, tabuleiro, casaReiJogadorAtual, casas);
    }

    // verifica se a jogada do jogador atual o coloca em xeque
    public boolean entraEmXeque(TabuleiroXadrez tabuleiro, Jogador jogadorAtual, Jogador jogadorAdversario) {
        Casa[][] casas = tabuleiro.getCasas();
        Casa casaReiJogadorAtual = encontrarCasaRei(jogadorAtual, casas);

        if (casaReiJogadorAtual == null) {
            throw new IllegalStateException("O Rei do jogador atual não foi encontrado.");
        }

        return podeCapturarRei(jogadorAdversario, tabuleiro, casaReiJogadorAtual, casas);
    }

    public boolean ehXequeMate(TabuleiroXadrez tabuleiro, Jogador jogadorAtual, Jogador jogadorAdversario,
            Casa[][] casas) {
        Casa casaRei = encontrarCasaRei(jogadorAdversario, casas);

        if (casaRei == null) {
            throw new IllegalStateException("O Rei do jogador adversário não foi encontrado.");
        }

        // itera sobre todas as peças do jogador atual
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Casa casaOrigem = casas[i][j];
                Peca pecaAtual = casaOrigem.getPeca();

                // verifica se a peça pertence ao jogador adversario
                if (pecaAtual != null && pecaAtual.getCor().equals(jogadorAdversario.getCor())) {
                    // simula todos os movimentos possíveis da peça, assumindo grosseiramente que,
                    // se não podemos saber qual é a peça, ela pode ser qualquer uma, e portanto pode 
                    // alcançar qualquer casa do tabuleiro
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            Casa casaDestino = casas[x][y];

                            // verifica se o movimento é válido
                            if ((destinoLivre(casaDestino) || ehCaptura(casaDestino, jogadorAdversario.getCor()))
                                    && (caminhoEstaLivre(pecaAtual, casaOrigem, casaDestino, tabuleiro)
                                            || pecaEUmCavalo(pecaAtual))
                                    && movimentoValidoParaPeca(pecaAtual, casaOrigem, casaDestino,
                                            jogadorAdversario.getCor())) 
                            {
                                
                                // simulação do movimento
                                Peca pecaCapturada = casaDestino.getPeca();
                                casaDestino.colocarPeca(pecaAtual);
                                casaOrigem.colocarPeca(null);

                                // verifica se o jogador sai do xeque
                                boolean saiuDoXeque = saiDoXeque(tabuleiro, jogadorAdversario, jogadorAtual);

                                // desfaz o movimento simulado
                                casaOrigem.colocarPeca(pecaAtual);
                                casaDestino.colocarPeca(pecaCapturada);

                                // se o joagdor saiu do xeque, não é xeque mate
                                if (saiuDoXeque) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        // se nenhum movimento possível tira o jogador do xeque, é xeque-mate
        return true;
    }
}
