package main.java.xadrez.pecas;

import main.java.xadrez.pecas.interfacePeca.Peca;

public class Torre extends Peca {

    public Torre(String cor) {
        super(cor);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String desenho() {
        if (cor.equals("\u001B[30m")) {
            return "\u001B[30mR\u001B[0m";
        } else {
            return "\u001B[37mR\u001B[0m";
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
