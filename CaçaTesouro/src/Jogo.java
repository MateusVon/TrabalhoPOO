import java.util.Scanner;

public class Jogo {

  private Jogador jogador1;
  private Jogador jogador2;
  private int rodadaAtual;
  private Scanner entradaGlobal;

  // Prepara o jogo
  public Jogo() {
    this.entradaGlobal = new Scanner(System.in);
    this.jogador1 = new Jogador("Jogador 1");
    this.jogador2 = new Jogador("Jogador 2");
    this.rodadaAtual = 1;
  }

  // Método principal que corre o jogo todo.

  public void executarPartida() {
    // 1. Menu inicial
    System.out.println("--- BEM-VINDO AO CAÇA AO TESOURO ---");
    System.out.println("Regra: 3 Verdes (6pts), 3 Amarelos (4pts), 2 Vermelhos (10pts)");

    // Faz cada jogador colocar os Tesouros
    jogador1.posicionarTesouro(this.entradaGlobal);
    jogador2.posicionarTesouro(this.entradaGlobal);

    // 2. Loop Principal do Jogo
    boolean jogoAtivo = true;
    while (jogoAtivo) {
      System.out.println("\n--- RODADA " + this.rodadaAtual + " ---");

      // Turno do Jogador 1
      executarTurno(jogador1, jogador2);

      // Turno do Jogador 2
      executarTurno(jogador2, jogador1);

      this.rodadaAtual++;
      if (this.rodadaAtual > 10) {
        jogoAtivo = false;
        declararVencedorPorPontos();
      }
    }
  }

  // Executa a jogada de um jogador.
  private void executarTurno(Jogador atacante, Jogador defensor) {
    System.out.println("Turno de: " + atacante.getNome());

    // Pedir coordenadas (linha e coluna)
    System.out.print("Digite a LINHA (0-9) do seu ataque: ");
    int linha = entradaGlobal.nextInt();
    System.out.print("Digite a COLUNA (0-9) do seu ataque: ");
    int coluna = entradaGlobal.nextInt();

    // Validar se a jogada é repetida
    if (atacante.jaJogou(linha, coluna)) {
      System.out.println("Erro! Você já atacou aí. Perdeu o turno.");
      return;
    }

    // Se for nova, regista a tentativa
    atacante.registrarTentativa(linha, coluna);

    double pontosGanhos = defensor.receberAtaque(linha, coluna);

    // Processa o resultado
    if (pontosGanhos > 0) {
      System.out.println("ACERTOU! Ganhou " + pontosGanhos + " pontos!");
      atacante.adicionarPontos(pontosGanhos);
    } else {
      System.out.println("Nenhum tesouro aí.");
    }
  }

  private void declararVencedorPorPontos() {
    if (jogador1.getPontuacao() > jogador2.getPontuacao()) {
      System.out.println("Jogador 1 VENCEU!");
    } else {
      System.out.println("Jogador 2 VENCEU");
    }
  }

  public static void main(String[] args) {
    Jogo minhaPartida = new Jogo();
    minhaPartida.executarPartida();
  }
}
