package main.java.xadrez.tabuleiro;

import main.java.xadrez.pecas.Torre;
import main.java.xadrez.pecas.Cavalo;
import main.java.xadrez.pecas.Bispo;
import main.java.xadrez.pecas.Rainha;
import main.java.xadrez.pecas.Rei;
import main.java.xadrez.pecas.Peao;

/*
 * Um tabuleiro contém 64 casas organizadas em 8 linhas e 8 colunas. Essa classe é responsável pela configuração inicial do tabuleiro, 
 * manutenção da configuração do tabuleiro a cada jogada,  pelas informações dos limites do tabuleiro, bem como pelo desenho do tabuleiro (com as peças nas posições ocupadas) a ser usado pelo Jogo.

 */
public class TabuleiroXadrez {

    private Casa[][] casas;

    // codigo para cores
    private static final String BLACK = "\u001B[30m";
    private static final String WHITE = "\u001B[37m";

    public TabuleiroXadrez() {
        casas = new Casa[8][8];
        inicializarTabuleiro();
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String cor = (i + j) % 2 == 0 ? "Branco" : "Preto";
                char coluna = (char) ('a' + j);
                
                // por enquanto a cor das casas não são exibidas, pois não achei nenhuma forma de fazer isso
                casas[i][j] = new Casa(cor, i + 1, coluna);
            }
        }
    
        colocarPecasPretas();
        colocarPecasBrancas();
    }

    private void colocarPecasPretas() {
        casas[0][0].colocarPeca(new Torre(BLACK));
        casas[0][1].colocarPeca(new Cavalo(BLACK));
        casas[0][2].colocarPeca(new Bispo(BLACK));
        casas[0][3].colocarPeca(new Rainha(BLACK));
        casas[0][4].colocarPeca(new Rei(BLACK));
        casas[0][5].colocarPeca(new Bispo(BLACK));
        casas[0][6].colocarPeca(new Cavalo(BLACK));
        casas[0][7].colocarPeca(new Torre(BLACK));

        for (int j = 0; j < 8; j++) {
            casas[1][j].colocarPeca(new Peao(BLACK));
        }
    }

    private void colocarPecasBrancas() {
        casas[7][0].colocarPeca(new Torre(WHITE));
        casas[7][1].colocarPeca(new Cavalo(WHITE));
        casas[7][2].colocarPeca(new Bispo(WHITE));
        casas[7][3].colocarPeca(new Rainha(WHITE));
        casas[7][4].colocarPeca(new Rei(WHITE));
        casas[7][5].colocarPeca(new Bispo(WHITE));
        casas[7][6].colocarPeca(new Cavalo(WHITE));
        casas[7][7].colocarPeca(new Torre(WHITE));

        for (int j = 0; j < 8; j++) {
            casas[6][j].colocarPeca(new Peao(WHITE));
        }
    }

    public boolean noLimite(int linha, char coluna) {
        return linha >= 1 && linha <= 8 && coluna >= 'a' && coluna <= 'h';
    }

    public String desenho() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            sb.append("  ");
            for (int j = 0; j < 8; j++) {
                sb.append("+---");
            }
            sb.append("+\n");

            sb.append((8 - i) + " ");
            for (int j = 0; j < 8; j++) {
                sb.append("| ");
                if (casas[i][j].getPeca() != null) {
                    //por enquanto os desenhos são simplesmente as letras iniciais dos nomes de cada peça
                    sb.append(casas[i][j].getPeca().desenho());
                } else {
                    sb.append(" ");
                }
                sb.append(" ");
            }
            sb.append("|\n");
        }

        sb.append("  ");
        for (int j = 0; j < 8; j++) {
            sb.append("+---");
        }
        sb.append("+\n");

        sb.append("    a   b   c   d   e   f   g   h\n");

        return sb.toString();
    }

    public String getCasas() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < casas.length; i++) {
            for (int j = 0; j < casas[i].length; j++) {
                sb.append(casas[i][j].toString());
                sb.append("\n");
            }
        }
        
        return sb.toString();
    }

}
