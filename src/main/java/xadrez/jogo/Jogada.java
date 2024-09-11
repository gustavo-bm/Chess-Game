package main.java.xadrez.jogo;

import main.java.xadrez.pecas.Cavalo;
import main.java.xadrez.pecas.Rei;
import main.java.xadrez.pecas.interfacePeca.Peca;
import main.java.xadrez.tabuleiro.Caminho;
import main.java.xadrez.tabuleiro.Casa;
import main.java.xadrez.tabuleiro.TabuleiroXadrez;

/*
 * Uma jogada é criada a partir das informações do jogador que a está realizando, a posição inicial e posição final da jogada, 
 * mas deve manter o caminho com base nessas informações iniciais (veja o diagrama: mostra basicamente que a classe Caminho tem uma relação
 * de composição com relação à classe Jogada, ou seja, são criadas instâncias de objetos da classe Caminho aqui para acessar seus métodos e/ou
 * atributos).
 * 
 * Lembrar que cada peça é quem deve fornecer seu caminho (na forma de uma string) e identificar se o movimento é válido de acordo com sua forma
 *  de se movimentar. Uma vez criada, uma jogada não pode ser alterada.
 */
public class Jogada {
    private Caminho caminho;

    public Jogada(Casa casaInicial, Casa casaFinal) {
        this.caminho = new Caminho(casaInicial, casaFinal);
    }

    public boolean ehValida(TabuleiroXadrez tabuleiro, Jogador jogadorAtual) {
        Casa casaInicial = caminho.casaInicial();
        Casa casaFinal = caminho.casaFinal();

        if (casaInicial.getPeca() == null)
            return false;

        Peca pecaInicial = casaInicial.getPeca();
        String corJogadorAtual = jogadorAtual.getCor();

        if (posicoesDentroDoLimite(tabuleiro, casaInicial, casaFinal)
                && pecaPertenceAoJogador(pecaInicial, corJogadorAtual)
                && (destinoLivre(casaFinal, corJogadorAtual) || ehCaptura(casaFinal, corJogadorAtual))
                && (caminhoEstaLivre(pecaInicial, casaInicial, casaFinal, tabuleiro)
                        || pecaEUmCavalo(pecaInicial))
                && movimentoValidoParaPeca(pecaInicial, casaInicial, casaFinal)) {
            return true;
        }

        return false;
    }

    private boolean posicoesDentroDoLimite(TabuleiroXadrez tabuleiro, Casa inicial, Casa fim) {
        return tabuleiro.noLimite(inicial.getLinha(), inicial.getColuna())
                && tabuleiro.noLimite(fim.getLinha(), fim.getColuna());
    }

    private boolean pecaPertenceAoJogador(Peca peca, String corJogador) {
        return peca.getCor().equals(corJogador);
    }

    private boolean destinoLivre(Casa casaFinal, String corJogador) {
        return casaFinal.estaLivre();
    }

    public boolean ehCaptura(Casa casaFinal, String corJogador) {
        return casaFinal.getPeca() != null && !casaFinal.getPeca().getCor().equals(corJogador);
    }

    private boolean caminhoEstaLivre(Peca peca, Casa inicial, Casa fim, TabuleiroXadrez tabuleiro) {
        return caminho.estaLivre(peca.caminho(inicial.getLinha(), inicial.getColuna(),
                fim.getLinha(), fim.getColuna()), tabuleiro);
    }

    private boolean pecaEUmCavalo(Peca peca) {
        return peca instanceof Cavalo;
    }

    private boolean movimentoValidoParaPeca(Peca peca, Casa inicial, Casa fim) {
        return peca.movimentoValido(inicial.getLinha(), inicial.getColuna(),
                fim.getLinha(), fim.getColuna());
    }

    // Verifica se a jogada levou a uma situação de xeque
     /*
         * A função deve verificar se o rei do oponente está em xeque após a jogada.
         */
    public boolean ehXeque(TabuleiroXadrez tabuleiro, Jogador jogadorAtual) {//Talvez fique melhor usar um Objeto CasaAtual para percorrer
       String corOponente;
       
       if(jogadorAtual.getCor().equals("branca")){
            corOponente = "preta";
       }else{
            corOponente="branca";
       }

       Casa casaRei = null;
       
       //Achar a posição do Rei adversário
       Casa[][] casas = tabuleiro.getCasas();
       
       for(int i = 0; i<8;i++){
         for(int j=0; j<8;j++){
            if(casas[i][j].getPeca() instanceof Rei && casas[i][j].getPeca().getCor().equals(corOponente)){
                casaRei = casas[i][j];
                break;
            }
        }
        //Ja achei o Rei
        if(casaRei!=null){
            break;
        }
    }
    //Percorri e não achei o rei
    if(casaRei == null){
        return false;
    }
        //vejo cada peça do tabuleiro e se for adversária verifico se tem o movimento para meu Rei
        
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){  
                Casa casaAtual = casas[i][j];
                Peca pecaAtual = casaAtual.getPeca();
                if(pecaAtual!=null && pecaAtual.getCor().equals(corOponente)){
                    if(movimentoValidoParaPeca(pecaAtual, casaAtual, casaRei)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Verifica se a jogada levou a uma situação de xeque mate
    public boolean ehXequeMate() {
        /*
         * A função deve verificar se o rei do oponente está em xeque mate após a
         * jogada.
         */
        return false; // Implementação necessária
    }
}
