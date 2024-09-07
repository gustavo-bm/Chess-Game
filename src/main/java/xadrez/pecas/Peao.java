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
        if (cor.equalsIgnoreCase("white")){
            if (linhaO == 2){
                if ((linhaD == linhaO + 1 || linhaD == linhaO + 2) && colunaD == colunaO)
                    return true;
            }else if (linhaD == linhaO + 1 && colunaD == colunaO) return true;
        }else if (cor.equalsIgnoreCase("black")){
            
        }
        return false;
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'caminho'");
    }
    
}
