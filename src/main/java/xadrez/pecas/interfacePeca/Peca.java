package main.java.xadrez.pecas.interfacePeca;

public abstract class Peca {

    protected String cor;
    protected boolean capturada;

    public Peca(String cor) {
        this.cor = cor;
        this.capturada = false;
    }

    public abstract String desenho();
    
    public abstract int movimentoValido(int linhaO, char colunaO, int linhaD, char colunaD);

    public abstract String caminho(int linhaO, char colunaO, int linhaD, char colunaD);

    public String getCor() {
        return cor;
    }

    public boolean estaCapturada() {
        return capturada;
    }
}
