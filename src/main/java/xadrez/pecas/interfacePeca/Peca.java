package main.java.xadrez.pecas.interfacePeca;

import main.java.xadrez.tabuleiro.Casa;

public abstract class Peca {

    protected Casa cor;
    protected boolean capturada;

    public Peca(Casa cor) {
        this.cor = cor;
        this.capturada = false;
    }

    public abstract String desenho();

    public abstract boolean movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD);

    public abstract String caminho(int linhaO, char colunaO, int linhaD, char colunaD);

    public void capturar() {
        this.capturada = true;
    }

    public void liberar() {
        this.capturada = false;
    }

    public Casa getCor() {
        return cor;
    }

    public boolean estaCapturada() {
        return capturada;
    }
}
