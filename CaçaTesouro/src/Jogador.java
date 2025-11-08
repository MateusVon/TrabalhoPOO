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
    
    if(tesouroAlvo != null) {
      double pontosGanhos = tesouroAlvo.getPontos();

      this.meuTabuleiro.removerTesouro(linha, coluna);

      return pontosGanhos;
    }else{
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

  public void posicionarTesouro(){
    System.out.println("---" + this.nome + ", posicione seus tesouros! ---");
    Scanner entrada = new Scanner(System.in);

    for(int i = 0; i <3; i++){
      System.out.println("Posicione seu Tesouro VERDE(" + (i+1) + "/3):");
    }

    System.out.println(this.nome + " terminou de posicionar!");
  }

  public boolean posicionarTesouro(int linha, int coluna){
    return this.posicionarTesouro(linha, coluna, "Amarelo");
  }

  public boolean posicionarTesouro(int linha, int coluna, String cor){
    if (this.meuTabuleiro.isPosicaoLivre(linha, coluna)){
      Tesouro novoTesouro = new Tesouro(cor);

      this.meuTabuleiro.adicionarTesouro(novoTesouro, linha, coluna);

      return true;
    }else {
      System.out.println("Erro: Posição(" + linha + "," + coluna + ") já está ocupada!");
      return false;
    }
  }

  public void adicionarPontos(double pontosGanhos) {
    this.pontuacao = this.pontuacao + pontosGanhos;
  }
}
