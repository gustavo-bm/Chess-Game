package main.java.xadrez;

import main.java.xadrez.pecas.Bispo;
import main.java.xadrez.pecas.interfacePeca.Peca;
import main.java.xadrez.tabuleiro.TabuleiroXadrez;

/**
 * Classe que vai criar e disparar o jogo, permitindo ao usuário a escolha de iniciar um jogo do zero, carregar um jogo a partir de um 
 * arquivo texto, cujo nome seja fornecido pelo usuário e salvar um jogo após o encerramento ou interrupção de uma partida, 
 * também em um arquivo com nome escolhido pelo usuário. Nenhum controle do jogo em si deve ser feito nessa classe. 
 * Essa classe é a que contém o main, mas estejam atentos para separar as funcionalidades da classe em métodos para uma melhor organização.
 * O main deve ser o mais enxuto possível. 
 * Incluir também um método teste para incluir testes exaustivos das várias classes executados ao longo do desenvolvimento

        Observação: apenas a parte de comunicação com o usuário necessária para o carregamento/salvamento de um jogo e das opções iniciais
         deve ser feita nessa classe. Manipulação de arquivos será vista no roteiro 5.

        Os arquivos para registro dos jogos devem ter o seguinte formato:
        <Nome do Jogador 1 - peças brancas>
        <Nome do Jogador 2 - peças pretas>
        <Jogada 1>
        <Jogada 2>
        <Jogada 3>
        …

        Cada jogada tem a linha e coluna da casa inicial da jogada e a linha e coluna da casa final, sem qualquer separação. Por exemplo:
        1a3b
        4c2h
        3g7g

 */
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
