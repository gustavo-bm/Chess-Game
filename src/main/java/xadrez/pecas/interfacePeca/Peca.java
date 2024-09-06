package main.java.xadrez.pecas.interfacePeca;

import main.java.xadrez.tabuleiro.Casa;

/*
 * Peças específicas
Cada tipo de peça específica tem uma classe própria: Rei, Dama, Cavalo, Bispo, Torre, Peão. 
Cada peça tem uma cor e é responsável pela representação que a peça terá na tela, pela checagem da adequação do movimento que o usuário 
deseja fazer em relação ao tipo específico de peça e pelo fornecimento do caminho que a peça realizaria. 
Cada peça também é responsável por manter a sua situação (se está capturada ou em jogo). Deve ter no mínimo os métodos:

    - String desenho(): que vai retornar o elemento que representa a peça específica, que será desenhado na tela. 

    - boolean movimentoValido(linhaO, colunaO, linhaD, colunaD):   que vai verificar se o movimento que o usuário deseja fazer é adequado
        para aquele tipo específico de peça. Este método se preocupa apenas com a parte do movimento que se refere à peça, não se preocupando
        com seu posicionamento do tabuleiro ou se o caminho está livre para a peça se movimentar

    - String caminho(linhaO, colunaO, linhaD, colunaD): se o movimento for válido para a peça, retorna uma String que representa a sequência de casas pela qual a peça irá se mover ou
     “” (string vazia) caso contrário. Por exemplo, se a peça for o cavalo, a posição inicial for 4b e a final for 5d, o movimento é válido
      e o método retorna a string “4b5b5c5d”.
 */

public abstract class Peca {

    protected String cor;
    protected boolean capturada;

    public Peca(String cor) {
        this.cor = cor;
        this.capturada = false;
    }

    public abstract String desenho();
    
    /* verificar se o movimento que o usuário deseja fazer é adequado para aquele tipo específico de peça. 
     * Este método se preocupa apenas com a parte do movimento que se refere à peça, não se preocupando com seu posicionamento do tabuleiro
     * ou se o caminho está livre para a peça se movimentar */
    public abstract boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD);

    /* se o movimento for válido para a peça, retorna uma String que representa a sequência de casas pela qual a peça irá se mover 
     * ou “” (string vazia) caso contrário. Por exemplo, se a peça for o cavalo, a posição inicial for 4b e a final for 5d, 
     * o movimento é válido e o método retorna a string “4b5b5c5d”. */
    public abstract String caminho(int linhaO, char colunaO, int linhaD, char colunaD);

    public void capturar() {
        this.capturada = true;
    }

    public void liberar() {
        this.capturada = false;
    }

    public String getCor() {
        return cor;
    }

    public boolean estaCapturada() {
        return capturada;
    }
}
