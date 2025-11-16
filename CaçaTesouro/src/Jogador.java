import java.util.*;

public class Jogador {
  private String nome;
  private double pontuacao;
  private Tabuleiro meuTabuleiro; // Composição
  private ArrayList<String> jogadasFeitas; // Histórico de tentativas de ataque
  private char[][] mapaDeTesouros; // Mapa de ataque

  public Jogador(String nome) {
    this.nome = nome;
    this.pontuacao = 0.0;
    this.meuTabuleiro = new Tabuleiro(); // Jogador é composto por um tabuleiro --> Composição

    this.jogadasFeitas = new ArrayList<String>(); // Cria a lista de jogadas vazia
    this.mapaDeTesouros = new char[10][10]; // Cria o mapa de ataque
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        this.mapaDeTesouros[i][j] = '~';
      }
    }
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return this.nome;
  }

  public double getPontuacao() {
    return this.pontuacao;
  }

  public Tabuleiro getMeuTabuleiro() {
    return this.meuTabuleiro;
  }

  // Verifica se o jogador ja tentou "cavar" nessa coordenada,
  // usando o ArrayList 'jogadasFeitas'
  public boolean jaCavou(int linha, int coluna) {
    String coordenada = linha + "," + coluna;
    return this.jogadasFeitas.contains(coordenada);
  }

  // Adiciona uma cordenada ao histórico de tentativas
  public void registrarTentativa(int linha, int coluna) {
    String coordenada = linha + "," + coluna;
    this.jogadasFeitas.add(coordenada);
  }


  // Atualiza o mapa de ataque com o resultado das jogadas
  public void registrarResultadoDoAtaque(int linha, int coluna, double pontosGanhos) {
    if (pontosGanhos > 0) {
      this.mapaDeTesouros[linha][coluna] = 'X'; // 'X' para ACERTO
    } else {
      this.mapaDeTesouros[linha][coluna] = 'O'; // 'O' para ERRO
    }
  }

  // Exibe no terminal onde o jogador já cavou
  public void exibirIlhaTesouros() {
    System.out.println("--- Ilha de Tesouros de " + this.nome + " ---");
    System.out.println(" ");
    for (int j = 0; j < 10; j++) {
      if (j == 0) {
        System.out.print("  ");
      }
      System.out.print(j + " ");
    }
    System.out.println();

    for (int i = 0; i < 10; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < 10; j++) {
        System.out.print(this.mapaDeTesouros[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("------------------------");
  }

  // Posiciona o tesouro de uma cor especifica no tabuleiro de defesa
  public boolean posicionarTesouro(int linha, int coluna, String cor) {
    // Cria o objeto tesouro, com a pontuação definida pela cor
    Tesouro novoTesouro = new Tesouro(cor);

    // Tenta posicionar no tabuleiro de defesa
    boolean sucesso = this.meuTabuleiro.posicionarTesouro(linha, coluna, novoTesouro);
    return sucesso;
  }

  public void adicionarPontos(double pontosGanhos) {
    this.pontuacao = this.pontuacao + pontosGanhos;
  }
}
