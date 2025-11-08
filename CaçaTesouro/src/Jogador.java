import java.util.*;

public class Jogador {
  private String nome;
  private double pontuacao;
  private Tabuleiro meuTabuleiro;

  private ArrayList<String> jogadasFeitas;

  public Jogador() {
  }

  public Jogador(String nome) {
    this.nome = nome;
    this.pontuacao = 0.0;
    this.meuTabuleiro = new Tabuleiro();

    this.jogadasFeitas = new ArrayList<String>(); // Cria a lista de jogadas vazia
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

  public int receberAtaque(int linha, int coluna) {
    Tesouro tesouroAlvo = this.meuTabuleiro.verificarPosicao(linha, coluna);

    if (tesouroAlvo != null) {
      double pontosGanhos = tesouroAlvo.getPontos();

      this.meuTabuleiro.removerTesouro(linha, coluna);

      return pontosGanhos;
    } else {
      return 0.0;
    }
  }

  public boolean jaJogou(int linha, int coluna) {
    String coordenada = linha + "," + coluna;
    return this.jogadasFeitas.contains(coordenada);
  }

  public void registrarTentativa(int linha, int coluna) {
    String coordenada = linha + "," + coluna;
    this.jogadasFeitas.add(coordenada);
  }

  public boolean posicionarTesouro(int linha, int coluna, String cor) {
    Tesouro novoTesouro = new Tesouro(cor);

    boolean sucesso = this.meuTabuleiro.posicionarTesouro(linha, coluna, novoTesouro);
    return sucesso;
  }

  public boolean posicionarTesouro(int linha, int coluna) {
    return this.posicionarTesouro(linha, coluna, "Amarelo");

    loopPosicionamento("verde", 3, entrada);
    loopPosicionamento("amarelo", 3, entrada);
    loopPosicionamento("vermelho", 2, entrada);

    System.out.println("---" + this.nome + " terminou de posicionar! ---");

  }

  private void loopPosicionamento(String cor, int quantidade, Scanner entrada) {
    for (int i = 0; i < quantidade; i++) {
      boolean sucesso = false;

      while (!sucesso) {
        System.out.println("\nPosicione seu Tesouro" + cor.toUpperCase() + "(" + (i + 1) + "/" + quantidade + "):");
        System.out.println(" Digite a linha (0-9)");
        int linha = entrada.nextInt();

        sucesso = this.posicionarTesouro(linha, linha, cor);

      }
    }
  }

  public void posicionarTesouro(Scanner entrada) {
    System.out.println("--- " + this.nome + ", posicione seus tesouros! ---");
    System.out.println("(O tabuleiro é 10x10, use linhas e colunas de 0 a 9)");
  }

  public void adicionarPontos(double pontosGanhos) {
    this.pontuacao = this.pontuacao + pontosGanhos;
  }
}
