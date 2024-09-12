package main.java.xadrez.pecas;

import main.java.xadrez.pecas.interfacePeca.Peca;

public class Bispo extends Peca {
    public Bispo(String cor) {
        super(cor);
    }

    @Override
    public String desenho() {
        if (cor.equals("BLACK")) {
            return "\u001B[30mB\u001B[0m";
        } else {
            return "\u001B[37mB\u001B[0m";
        }
    }

    @Override
    public int movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD) {
        // Considerado que é um movimento coordenado sempre a difereça entre as
        // respectivas dimensoes deve ser iguail

        int difLinhas = Math.abs(linhaO - linhaD);
        int difColunas = Math.abs(colunaO - colunaD);

        return difLinhas == difColunas ? 1 : 0;
    }

    @Override
    public String caminho(int linhaO, char colunaO, int linhaD, char colunaD) {

        StringBuilder caminhoPrint = new StringBuilder();

        if (movimentoValido(linhaO, colunaO, linhaD, colunaD) == 1) {
            int difLinhas = linhaD - linhaO;
            int difColunas = colunaD - colunaO;
            int qntPassos = Math.abs(difLinhas); // Pode ser das colunas não tem diferença;

            for (int i = 0; i <= qntPassos; i++) { // Se vai ser negativo ou positivo vai depender do sinal da diferença
                int linhaAtual = linhaO + i * (difLinhas / qntPassos);// Em módulo estarei andando uma unidade;
                char colunaAtual = (char) (colunaO + i * (difColunas / qntPassos));
                caminhoPrint.append(linhaAtual).append(colunaAtual);
            }
        } else {
            return caminhoPrint.append("").toString();
        }
        return caminhoPrint.toString();
    }

}
