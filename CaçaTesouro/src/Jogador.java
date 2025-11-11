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

  public double receberAtaque(int linha, int coluna) {
    Tesouro tesouroAlvo = this.meuTabuleiro.verificarPosicao(linha, coluna);

    // Condição para verificar se acertouo tesouro e remove-lo;
    if (tesouroAlvo != null) {
      double pontosGanhos = tesouroAlvo.getPontos();

      this.meuTabuleiro.removerTesouro(linha, coluna);

      return pontosGanhos;
    } else {
      return 0.0;
    }
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
