import java.util.*;

public class Jogador {
  private String nome;
  private double pontuacao;
  private Tabuleiro meuTabuleiro;

  private ArrayList<String> jogadasFeitas;
  private char[][] mapaDeTesouros;

  public Jogador() {
  }

  public Jogador(String nome) {
    this.nome = nome;
    this.pontuacao = 0.0;
    this.meuTabuleiro = new Tabuleiro();

    this.jogadasFeitas = new ArrayList<String>(); // Cria a lista de jogadas vazia
    this.mapaDeTesouros = new char[10][10];
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

  public void setPontuacao(int pontuacao) {
    this.pontuacao = pontuacao;
  }

  public double getPontuacao() {
    return this.pontuacao;
  }

  public void setMeuTabuleiro(Tabuleiro meuTabuleiro) {
    this.meuTabuleiro = meuTabuleiro;
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

  public void registrarResultadoDoAtaque(int linha, int coluna, double pontosGanhos) {
    if (pontosGanhos > 0) {
      this.mapaDeTesouros[linha][coluna] = 'X'; // 'X' para ACERTO
    } else {
      this.mapaDeTesouros[linha][coluna] = 'O'; // 'O' para ÁGUA/ERRO
    }
  }

  public void exibirIlhaTesouros() {
    System.out.println("--- Ilha de Tesouros de " + this.nome + " ---");
    System.out.println(" ");
    for (int j = 0; j < 10; j++) {
      System.out.println(j + " ");
    }
    System.out.println();

    for (int i = 0; i < 10; i++) {
      System.out.println(i + " ");
      for (int j = 0; j < 10; j++) {
        System.out.println(this.mapaDeTesouros[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("------------------------");
  }

  // Posiciona o tesouro de uma cor especifica
  public boolean posicionarTesouro(int linha, int coluna, String cor) {
    Tesouro novoTesouro = new Tesouro(cor);

    boolean sucesso = this.meuTabuleiro.posicionarTesouro(linha, coluna, novoTesouro);
    return sucesso;
  }

  public void posicionarTesouro() {
    System.out.println("---" + this.nome + ", posicione seus tesouros! ---");
    System.out.println("(O tabuleiro é 10x1, use linha e colunas de 0 a 9)");

    loopPosicionamento("verde", 3);
    loopPosicionamento("amarelo", 3);
    loopPosicionamento("vermelho", 2);

    System.out.println("---" + this.nome + " terminou de posicionar! ---");
  }

  private void loopPosicionamento(String cor, int quantidade) {
    Random aleatorio = new Random();

    for (int i = 0; i < quantidade; i++) {
      boolean sucesso = false;

      while (!sucesso) {
        int linha = aleatorio.nextInt(10);
        int coluna = aleatorio.nextInt(10);

        sucesso = this.posicionarTesouro(linha, coluna, cor);
      }
    }

  }

  public boolean posicionarTesouro(int linha, int coluna) {
    return this.posicionarTesouro(linha, coluna, "Amarelo");
  }

  public void adicionarPontos(double pontosGanhos) {
    this.pontuacao = this.pontuacao + pontosGanhos;
  }
}
