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
        if(linhaO == linhaD && colunaD != colunaO) { //Movimento na Horizontal, só se muda coluna
            return 1;
        }else if(linhaO != linhaD && colunaD == colunaO){ //Movimento na Vertical, só se muda linha
            return 1;
        }else  if(Math.abs(linhaO - linhaD) == Math.abs(colunaO - colunaD)){ //Movimento na Diagonal, linha e coluna se mexem na mesma proporção
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {
        String cam;
        cam = "" + linhaO + colunaO + "\0";
       if(linhaO == linhaD && colunaD != colunaO) { //Movimento na Horizontal, só se muda coluna
            
            if(colunaO < colunaD){ // se for maior moveu para a direita, se for menor moveu para esquerda
                for(char col = (char) (colunaO +1) ; col <= colunaD; col++){ 
                  cam += linhaO;
                  cam += col; 
                }
            }else{
               for(char col = (char) (colunaO -1) ; col >= colunaD; col--){ 
                  cam += linhaO;
                  cam += col; 
                }
            }
           
           
        }else if(linhaO != linhaD && colunaD == colunaO){ //Movimento na Vertical, só se muda linha
            if(linhaO < linhaD){ // se for menor moveu para cima, se for maior moveu para baixo
                for(int lin = linhaO +1 ; lin <= linhaD; lin++){ 
                  cam += lin;
                  cam += colunaO; 
                }
            }else{
               for(int lin = linhaO - 1 ; lin >= linhaD; lin--){ 
                  cam += lin;
                  cam += colunaO; 
                }
            }
           
        }else  if(Math.abs(linhaO - linhaD) == Math.abs(colunaO - colunaD)){ //Movimento na Diagonal, linha e coluna se mexem na mesma proporção
           int dir_lin; //Direçao que a lin muda (Cima ou baixo)
           int dir_col; //Direçao que a col muda (esquerda ou direita)

           if(linhaO < linhaD){ //Se O menor que D, linha sobe
            dir_lin = 1;    
           }else{
            dir_lin = -1;    //linha desce
           }

            if(colunaO < colunaD){ //Se O menor que D, coluna vai para a direita
            dir_col = 1;    
           }else{
            dir_col = -1; //Coluna vai para a esquerda
           }

           char col = colunaO;
           int lin = linhaO;
           
            while(col != colunaD && lin != linhaD){
                col += dir_col; // Move a coluna para a direcao decidida
                lin += dir_lin; // Move a linha para a direcao decidida
                cam += lin; //concatena a string com a linha
                cam += col; //concatena a string com a coluna
            }           
        }else {
            return "";
        }

        return cam;
    }

}
