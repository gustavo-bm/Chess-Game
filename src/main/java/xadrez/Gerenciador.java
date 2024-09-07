package main.java.xadrez;

import main.java.xadrez.jogo.Jogada;
import main.java.xadrez.jogo.Jogador;
import main.java.xadrez.jogo.Jogo;
import main.java.xadrez.pecas.Bispo;
import main.java.xadrez.pecas.Torre;
import main.java.xadrez.pecas.interfacePeca.Peca;
import main.java.xadrez.tabuleiro.Caminho;
import main.java.xadrez.tabuleiro.Casa;
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

    private void testarTorre() {
        Peca torre = new Torre("WHITE");

        System.out.println(torre.movimentoValido(1, 'a', 1, 'b')); // true
        System.out.println(torre.movimentoValido(1, 'a', 4, 'a')); // true
        System.out.println(torre.movimentoValido(1, 'a', 2, 'b')); // false

        System.out.println();

        System.out.println(torre.caminho(1, 'a', 1, 'b')); // 1a1b
        System.out.println(torre.caminho(1, 'a', 4, 'a')); // 1a2a3a4a
        System.out.println(torre.caminho(5, 'b', 2, 'b')); // 5b4b3b2b
    }

    private void testarTabuleiroComEsemPecas() {
        // TabuleiroXadrez tabuleiro1 = new TabuleiroXadrez();
        // System.out.println(tabuleiro1.desenho());

        Jogo jogo1 = new Jogo();
        jogo1.desenhoTabuleiroAtualizado();
    }

    private void testarColocacaoDePecasNoTabuleiro() {
        TabuleiroXadrez tabuleiro1 = new TabuleiroXadrez();
        Casa[][] casas = tabuleiro1.getCasas();

        Peca torre = new Torre("WHITE");
        casas[0][0].colocarPeca(torre); 

        System.out.println(tabuleiro1.desenho());
    }

    private void testarJogadorEJogadas() {
        TabuleiroXadrez tabuleiro1 = new TabuleiroXadrez();
        Casa[][] casas = tabuleiro1.getCasas();
        Peca torre = new Torre("WHITE");
        casas[0][0].colocarPeca(torre); 

        Jogador jogador1 = new Jogador("GU", "WHITE");

        Jogada jogada1 = new Jogada(casas[0][0], casas[0][4]); 
        Jogada jogada2 = new Jogada(casas[0][0], casas[1][4]); 
        Jogada jogada3 = new Jogada(casas[0][5], casas[0][4]); 
        Jogada jogada4 = new Jogada(casas[0][0], casas[0][5]); 

        System.out.println("Jogada1 válida: " + jogada1.ehValida(tabuleiro1, jogador1)); // true
        System.out.println("Jogada2 válida: " + jogada2.ehValida(tabuleiro1, jogador1)); // false
        System.out.println("Jogada3 válida: " + jogada3.ehValida(tabuleiro1, jogador1)); // false
        System.out.println("Jogada4 válida: " + jogada4.ehValida(tabuleiro1, jogador1)); // true
    }

    public void teste() {
        // testarTorre();
        // testarColocacaoDePecasNoTabuleiro();
        // testarJogadorEJogadas();
        testarTabuleiroComEsemPecas();
    }

    public static void main(String[] args) {
        Gerenciador gerenciador = new Gerenciador();
        gerenciador.teste();
    }
}
