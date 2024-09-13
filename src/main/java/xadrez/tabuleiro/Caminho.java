package main.java.xadrez.tabuleiro;

/**
 * O caminho tem a sequência das casas e informa sobre a posição inicial, a
 * posição final e a situação do caminho.
 */
public class Caminho {
    private Casa casaInicial;
    private Casa casaFinal;

    public Caminho(Casa casaInicial, Casa casaFinal) {
        this.casaInicial = casaInicial;
        this.casaFinal = casaFinal;
    }

    public boolean estaLivre(String caminho, TabuleiroXadrez tabuleiro) {
        Casa[][] casas = tabuleiro.getCasas();
        int length = caminho.length();

        // Verifica se o caminho é válido (mínimo de 4 caracteres: origem e destino)
        if (length < 4) {
            return true; // Não há casas intermediárias
        }

        // Percorre as casas intermediárias, ignorando a primeira e a última casa
        for (int i = 2; i < length - 2; i += 2) {
            int linha = Character.getNumericValue(caminho.charAt(i)) - 1;
            int coluna = caminho.charAt(i + 1) - 'a';

            // Verifica se os índices estão dentro do limite do tabuleiro
            if (linha < 0 || linha >= casas.length || coluna < 0 || coluna >= casas[0].length) {
                throw new IndexOutOfBoundsException("Posição fora dos limites do tabuleiro: " + linha + ", " + coluna);
            }

            // Verifica se a casa não está livre
            if (!casas[linha][coluna].estaLivre()) {
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
