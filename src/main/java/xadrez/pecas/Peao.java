package main.java.xadrez.pecas;

import main.java.xadrez.pecas.interfacePeca.Peca;

public class Peao extends Peca {

    public Peao(String cor) {
        super(cor);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String desenho() {
        if (cor.equals("\u001B[30m")) {
            return "\u001B[30mP\u001B[0m";
        } else {
            return "\u001B[37mP\u001B[0m";
        }
    }

    @Override
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        return false;
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'caminho'");
    }
    
}
