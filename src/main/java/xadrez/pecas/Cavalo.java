package main.java.xadrez.pecas;

import main.java.xadrez.pecas.interfacePeca.Peca;

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
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (linhaD == linhaO + 1 || linhaD == linhaO - 1) {
            if (colunaD == colunaO + 2 || colunaD == colunaO - 2) {
                return true;
            } else {
                return false;
            }
        } else if (linhaD == linhaO + 2 || linhaD == linhaO - 2) {
            if (colunaD == colunaO + 1 || colunaD == colunaO - 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (linhaO > linhaD) {
            for (int i = linhaO; i >= linhaD; i--) {
                System.out.print(i);
                System.out.print(colunaO);
            }
            if (colunaO > colunaD) {
                for (int i = colunaO; i >= colunaD; i--) {
                    if (i == colunaD) {
                        System.out.print(linhaD);
                        System.out.println(i);
                        break;
                    }
                    System.out.print(linhaD);
                    System.out.print(i);
                }
            } else {
                for (int i = colunaO; i <= colunaD; i++) {
                    if (i == colunaD) {
                        System.out.print(linhaD);
                        System.out.println(i);
                        break;
                    }
                    System.out.print(linhaD);
                    System.out.print(i);
                }
            }
        } else {
            for (int i = linhaO; i <= linhaD; i++) {
                System.out.print(colunaO);
                System.out.print(i);
            }
            if (colunaO > colunaD) {
                for (int i = colunaO; i >= colunaD; i--) {
                    if (i == colunaD) {
                        System.out.print(linhaD);
                        System.out.println(i);
                        break;
                    }
                    System.out.print(linhaD);
                    System.out.print(i);
                }
            } else {
                for (int i = colunaO; i <= colunaD; i++) {
                    if (i == colunaD) {
                        System.out.print(linhaD);
                        System.out.println(i);
                        break;
                    }
                    System.out.print(linhaD);
                    System.out.print(i);
                }
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'caminho'");
    }

}
