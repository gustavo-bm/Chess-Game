package main.java.xadrez.pecas;

import main.java.xadrez.pecas.interfacePeca.Peca;

public class Peao extends Peca {

    public Peao(String cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        if (cor.equals("BLACK")) {
            return "\u001B[30mP\u001B[0m";
        } else {
            return "\u001B[37mP\u001B[0m";
        }
    }

    // retorna 1 se o movimento for válido e para a frente, 2 se for para a diagonal e 0 se for inválido 
    @Override
    public int movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        int diferencaLinha = linhaD - linhaO;
        int diferencaColuna = Math.abs(colunaD - colunaO);

        if (cor.equalsIgnoreCase("white")) {
            if (linhaO == 2 && diferencaLinha == 2 && colunaD == colunaO) {
                return 1;
            }
            if (diferencaLinha == 1) {
                if (colunaD == colunaO) {
                    return 1;
                }
                if (diferencaColuna == 1) {
                    return 2;
                }
            }
        } else if (cor.equalsIgnoreCase("black")) {
            if (linhaO == 7 && diferencaLinha == -2 && colunaD == colunaO) {
                return 1;
            }
            if (diferencaLinha == -1) {
                if (colunaD == colunaO) {
                    return 1;
                }
                if (diferencaColuna == 1) {
                    return 2;
                }
            }
        }
        return 0;
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (movimentoValido(linhaO, colunaO, linhaD, colunaD) == 1) {
            if (linhaD == linhaO + 2) {
                return linhaO + "" + colunaO + "" + (linhaO + 1) + "" + colunaO + "" + linhaD + "" + colunaD;
            } else if (linhaD == linhaO - 2) {
                return linhaO + "" + colunaO + "" + (linhaO - 1) + "" + colunaO + "" + linhaD + "" + colunaD;
            } else
                return linhaO + "" + colunaO + "" + linhaD + "" + colunaD;
        }
        return "";
    }

}
