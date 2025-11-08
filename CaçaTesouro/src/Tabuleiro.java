import java.util.*;

public class Tabuleiro {
  private Tesouro[][] mapaTesouros;

  public Tabuleiro() {
    mapaTesouros = new Tesouro[10][10];
  }

  public boolean posicionarTesouro(int linha, int coluna, Tesouro t) {
    if (!(linha >= 0 && linha < 10 && coluna >= 0 && coluna < 10)) { // Verificação de limite do tabuleiro para evitar
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

  public void exibeMapa() {
    for (int i = 0; i < this.mapaTesouros.length; i++) {
      for (int j = 0; j < this.mapaTesouros[i].length; j++) {
        System.out.print(this.mapaTesouros[i][j] + " ");
      }
      System.out.println();
    }
  }
}
