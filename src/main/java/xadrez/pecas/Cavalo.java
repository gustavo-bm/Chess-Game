package main.java.xadrez.pecas;

import main.java.xadrez.pecas.interfacePeca.Peca;

// teste
public class Cavalo extends Peca {

    public Cavalo(String cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        if (cor.equals("BLACK")) {
            return "\u001B[30mH\u001B[0m";
        } else {
            return "\u001B[37mH\u001B[0m";
        }
    }

    @Override
    public int movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        int colO = colunaO - 'a' + 1;
        int colD = colunaD - 'a' + 1;

        if (linhaD == linhaO + 1 || linhaD == linhaO - 1) {
            if (colD == colO + 2 || colD == colO - 2) {
                return 1;
            } else {
                return 0;
            }
        } else if (linhaD == linhaO + 2 || linhaD == linhaO - 2) {
            if (colD == colO + 1 || colD == colO - 1) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (movimentoValido(linhaO, colunaO, linhaD, colunaD) == 1) {
            StringBuilder caminhoCavalo = new StringBuilder();

            caminhoCavalo.append(linhaO).append(colunaO);

            // Posicao Inicial
            int linhaAtual = linhaO;
            int colunaAtual = colunaO;

            // Caso 2 linhas e 1 coluna
            if (Math.abs(linhaD - linhaAtual) == 2 && Math.abs(colunaD - colunaAtual) == 1) {
                linhaAtual += (linhaD - linhaAtual) / 2;
                caminhoCavalo.append(linhaAtual).append(colunaAtual);
                colunaAtual = colunaD;
                caminhoCavalo.append(linhaAtual).append(colunaAtual);
            }
            // Caso 1 linha e 2 colunas
            else if (Math.abs(linhaD - linhaAtual) == 1 && Math.abs(colunaD - colunaAtual) == 2) {
                colunaAtual += (char) ((colunaD - colunaAtual) / 2);
                caminhoCavalo.append(linhaAtual).append(colunaAtual);
                linhaAtual = linhaD;
                caminhoCavalo.append(linhaAtual).append(colunaAtual);
            }

            // Movimento Final
            caminhoCavalo.append(linhaD).append(colunaD);

            return caminhoCavalo.toString();
        }

        return "";
    }

}
