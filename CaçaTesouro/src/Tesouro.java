public class Tesouro {
  private String cor;
  private double pontos;

  public Tesouro(String cor) {
    this.cor = cor;

  // Atribui pontuação com base na cor.    
    if (this.cor.equalsIgnoreCase("amarelo")) {
      this.pontos = 4.0;
    } else if (this.cor.equalsIgnoreCase("verde")) {
      this.pontos = 6.0;
    } else if (this.cor.equalsIgnoreCase("vermelho")) {
      this.pontos = 10.0;
    } else {
      System.out.println("Erro! Cor inválida.");
      this.pontos = 0.0;
    }
  }

  public String getCor() {
    return this.cor;
  }

  public double getPontos() {
    return this.pontos;
  }

  public String toString() {
    return "Tesouro [cor = " + this.cor + ", pontos = " + this.pontos + "]";
  }
}
