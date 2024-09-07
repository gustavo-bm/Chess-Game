package main.java.xadrez.pecas;

import main.java.xadrez.pecas.interfacePeca.Peca;

public class Rei extends Peca {

    public Rei(String cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        if (cor.equals("BLACK")) {
            return "\u001B[30mK\u001B[0m";
        } else {
            return "\u001B[37mK\u001B[0m";
        }
    }

    @Override
    public boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        //Poderia fazer que a diferença das duas dimensoes deve ser menor ou igual a 1 em absoluto
        if((linhaD == linhaO + 1) && colunaO == colunaD){
            return true;
        }
        else if((linhaD == linhaO -1) && colunaO == colunaD){
            return true;
        }else if((linhaD == linhaO - 1) && (colunaD == colunaO +1)){
            return true;
        }else if((linhaD == linhaO - 1) && (colunaD == colunaO  - 1)){
            return true;
        }else if(linhaD == linhaO && (colunaD == colunaO + 1)){
            return true;
        }else if(linhaD == linhaO && (colunaD == colunaO - 1)){
            return true;
        }else if((linhaD == linhaO +1) && colunaD == colunaO - 1){
            return true;
        }else if((linhaD == linhaO + 1) && colunaD == colunaO + 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        StringBuilder caminho = new StringBuilder();
        if(movimentoValido(linhaO, colunaO, linhaD, colunaD)){
             caminho.append(linhaO).append(colunaO).append(linhaD).append(colunaD);
        }else{
            caminho.append("");
        }
        return caminho.toString();
    }
    
}
