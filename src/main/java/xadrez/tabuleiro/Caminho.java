package main.java.xadrez.tabuleiro;

/**
 * O caminho tem a sequência das casas e informa sobre a posição inicial, a posição final e a situação do caminho.
 */
public class Caminho {
    private Casa casaInicial;
    private Casa casaFinal;

    public Caminho(Casa casaInicial, Casa casaFinal) {
        this.casaInicial = casaInicial;
        this.casaFinal = casaFinal;
    }

    // Retorna se todas as casas do caminho estão livres, exceto a inicial e a final
    public boolean estaLivre(String caminho, TabuleiroXadrez tabuleiro) {
        Casa[][] casas = tabuleiro.getCasas();
        int length = caminho.length();
    
        for (int i = 0; i < length - 2; i += 2) {
            int linha = Character.getNumericValue(caminho.charAt(i)) - 1;
            int coluna = caminho.charAt(i + 1) - 'a';
    
            if (i != 0 && i != length - 2 && !casas[linha][coluna].estaLivre()) {
                return false;
            }
        }
    
        return true;
    }

    // Retorna a casa inicial do caminho
    public Casa casaInicial() {
        return casaInicial;
    }

    // Retorna a casa final do caminho
    public Casa casaFinal() {
        return casaFinal;
    }
}
