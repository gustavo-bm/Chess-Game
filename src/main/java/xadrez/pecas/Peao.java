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
                if (linhaD == linhaO + 2 && colunaD == colunaO)
                    return true;
                if (linhaD == linhaO + 1 && (colunaD == colunaO || colunaD == colunaO - 1 || colunaD == colunaO + 1))
                    return true;
            }else if (linhaD == linhaO + 1 && (colunaD == colunaO || colunaD == colunaO - 1 || colunaD == colunaO + 1)) return true;
        }else if (cor.equalsIgnoreCase("black")){
            if (linhaO == 7){
                if (linhaD == linhaO - 2 && colunaD == colunaO)
                    return true;
                if (linhaD == linhaO - 1 && (colunaD == colunaO || colunaD == colunaO - 1 || colunaD == colunaO + 1))
                    return true;
            }else if (linhaD == linhaO - 1 && (colunaD == colunaO || colunaD == colunaO - 1 || colunaD == colunaO + 1)) return true;
        }
        return false;
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (movimentoValido(linhaO, colunaO, linhaD, colunaD)){
            if (linhaD == linhaO + 2){
                return linhaO + "" + colunaO + "" + (linhaO + 1) + "" + colunaO + "" + linhaD + "" + colunaD; 
            }else if (linhaD == linhaO - 2){
                    return linhaO + "" + colunaO + "" + (linhaO - 1) + "" + colunaO + "" + linhaD + "" + colunaD;
            }else return linhaO + "" + colunaO + "" + linhaD + "" + colunaD;
        }
        return "";
    }
    
}
