public class Tabuleiro {
  private Tesouro[][] mapaTesouros;

  public Tabuleiro() {
    mapaTesouros = new Tesouro[10][10];
  }

  public boolean posicionarTesouro(int linha, int coluna, Tesouro t) {
    if (posicaoValida(linha, coluna)) { // Verificação de limite do tabuleiro para evitar
                                                                     // que o usuário tente inserir um tesouro em uma
                                                                     // posição fora dos limites da matriz.
      System.out.println("Posição inválida! Linha e coluna deve ser entre 0 e 9.");
      return false;
    }
    if (posicaoLivre(linha, coluna)) {
      mapaTesouros[linha][coluna] = t;
      return true;
    }
    System.out.println("Posição já ocupada!");
    return false;
  }

  public boolean posicaoLivre(int linha, int coluna) { // Metódo para verificar se a posição do mapa está livre.
    if (mapaTesouros[linha][coluna] != null) { // Se linha e coluna informadas forem diferente de null, retorna false
                                               // para informar que a posição já está ocupada.
      return false;
    }
    return true; // Se não, retorna true e confirma a posição livre para inserir o tesouro.
  }

  public boolean posicaoValida(int linha, int coluna){
    if (!(linha >= 0 && linha < 10 && coluna >= 0 && coluna < 10)) {
      return false;
    }
    return true;
  }

  public Tesouro receberAtaque(int linha, int coluna) {
    if(!posicaoValida(linha, coluna)){ //Verifica se a posição informada é valida
      return null;
    }
    // Condição para verificar se acertou o tesouro e remove-lo;
    if (mapaTesouros[linha][coluna] != null) {
      Tesouro achou = mapaTesouros[linha][coluna];
      mapaTesouros[linha][coluna] = null;
      return achou;
    }
    return null;
  }

  public void exibeMapa() {
    String cor;
    for (int i = 0; i < this.mapaTesouros.length; i++) {
      for (int j = 0; j < this.mapaTesouros[i].length; j++) {
        if (this.mapaTesouros[i][j] == null) {
          System.out.print("~ ");
        } else {
          cor = this.mapaTesouros[i][j].getCor();
          System.out.print(cor + " ");
        }
      }
      System.out.println();
    }
  }
}
