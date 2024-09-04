package main.java.xadrez.pecas;

import main.java.xadrez.pecas.interfacePeca.Peca;

public class Bispo extends Peca {
    public Bispo(String cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        if (cor.equals("\u001B[30m")) {
            return "\u001B[30mB\u001B[0m";
        } else {
            return "\u001B[37mB\u001B[0m";
        }
    }

    @Override
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'movimentoValido'");
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'caminho'");
    }

}
