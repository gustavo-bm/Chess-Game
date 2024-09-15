package main.java.xadrez.jogo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Arquivo {
  // atributo caminho do arquivo
  // construtor que inicializa o nome do arquivo

  // metodo de salvar um arquivo, sendo o texto as jogadas

  // nao fazer ainda: metodo de consumir o arquivo e restaura o estado do jogo

  private String caminhoArquivo;

  // Construtor que inicializa o caminho do arquivo
  public Arquivo(String caminhoArquivo) {
    this.caminhoArquivo = caminhoArquivo;
  }

  // Método para salvar o estado do jogo em um arquivo
  public void salvarJogo(String registroJogo) {
    try {
      Files.write(Paths.get(caminhoArquivo), registroJogo.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Método para ler o estado do jogo de um arquivo e retornar seu conteúdo
  public String restaurarJogo() {
    try {
      return Files.readString(Paths.get(caminhoArquivo));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }
}
