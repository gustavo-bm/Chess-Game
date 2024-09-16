package main.java.xadrez.pecas;

import main.java.xadrez.pecas.interfacePeca.Peca;

public class Torre extends Peca {

    public Torre(String cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        if (cor.equals("BLACK")) {
            return "\u001B[30mR\u001B[0m";
        } else {
            return "\u001B[37mR\u001B[0m";
        }
    }

    @Override
    public int movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        return linhaO == linhaD || colunaO == colunaD ? 1 : 0;
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (movimentoValido(linhaO, colunaO, linhaD, colunaD) == 1) {
            StringBuilder sb = new StringBuilder();

            if (linhaO == linhaD) {
                if (colunaO < colunaD) {
                    for (char col = colunaO; col <= colunaD; col++) {
                        sb.append(linhaO).append(col).append("");
                    }
                } else {
                    for (char col = colunaO; col >= colunaD; col--) {
                        sb.append(linhaO).append(col).append("");
                    }
                }
            } else if (colunaO == colunaD) {
                if (linhaO < linhaD) {
                    for (int linha = linhaO; linha <= linhaD; linha++) {
                        sb.append(linha).append(colunaO).append("");
                    }
                } else {
                    for (int linha = linhaO; linha >= linhaD; linha--) {
                        sb.append(linha).append(colunaO).append("");
                    }
                }
            }

            return sb.toString();
        } else {
            return "";
        }
    }

}
