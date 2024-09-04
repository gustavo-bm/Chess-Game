package main.java.xadrez;

import main.java.xadrez.pecas.Bispo;
import main.java.xadrez.pecas.interfacePeca.Peca;
import main.java.xadrez.tabuleiro.TabuleiroXadrez;

public class Gerenciador {

    // Codigo para cores
    private static final String BLACK = "\u001B[30m";
    private static final String WHITE = "\u001B[37m";

    public void teste() {
        // Criar peças e casas para testar os métodos básicos implementados, com a finalizade de gerar o tabuleiro
        Peca torre = new Bispo(WHITE);
        System.out.println(torre.getCor());
        System.out.println(torre.toString());

        Peca bispo = new Bispo(BLACK);
        System.out.println(bispo.getCor());
        System.out.println(bispo.toString());

        // Inicializar o tabuleiro com as peças em suas posições iniciais e exibi-lo
        TabuleiroXadrez tabuleiro = new TabuleiroXadrez();
        System.out.println(tabuleiro.desenho());

        // Exibir as infromações de cada casa
        System.out.println(tabuleiro.getCasas());
    }

    public static void main(String[] args) {
        Gerenciador gerenciador = new Gerenciador();
        gerenciador.teste();
    }
}
