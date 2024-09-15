package main.java.xadrez.pecas;

import main.java.xadrez.pecas.interfacePeca.Peca;

public class Rainha extends Peca {

    public Rainha(String cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        if (cor.equals("BLACK")) {
            return "\u001B[30mQ\u001B[0m";
        } else {
            return "\u001B[37mQ\u001B[0m";
        }
    }

    @Override
    public int movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        if (linhaO == linhaD && colunaD != colunaO) { 
            return 1;
        } else if (linhaO != linhaD && colunaD == colunaO) { 
            return 1;
        } else if (Math.abs(linhaO - linhaD) == Math.abs(colunaO - colunaD)) { 
                                                                               
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        StringBuilder cam = new StringBuilder(); 

        
        if (linhaO == linhaD && colunaO != colunaD) {
            if (colunaO < colunaD) { 
                for (char col = (char) (colunaO + 1); col <= colunaD; col++) {
                    cam.append(linhaO).append(col); 
                }
            } else { 
                for (char col = (char) (colunaO - 1); col >= colunaD; col--) {
                    cam.append(linhaO).append(col); 
                }
            }

            
        } else if (linhaO != linhaD && colunaO == colunaD) {
            if (linhaO < linhaD) { 
                for (int lin = linhaO + 1; lin <= linhaD; lin++) {
                    cam.append(lin).append(colunaO); 
                }
            } else { 
                for (int lin = linhaO - 1; lin >= linhaD; lin--) {
                    cam.append(lin).append(colunaO); 
                }
            }

            
        } else if (Math.abs(linhaO - linhaD) == Math.abs(colunaO - colunaD)) {
            int dirLin = (linhaO < linhaD) ? 1 : -1; 
            int dirCol = (colunaO < colunaD) ? 1 : -1; 

            char col = colunaO;
            int lin = linhaO;
            cam.append(lin).append(col);
            while (lin != linhaD && col != colunaD) {
                lin += dirLin;
                col += dirCol;
                cam.append(lin).append(col); 
            }

        } else {
            return ""; 
        }

        return cam.toString().trim(); 
    }

}
