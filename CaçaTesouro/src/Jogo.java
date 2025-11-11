import java.util.Scanner;

public class Jogo {

  private Jogador jogador1;
  private Jogador jogador2;
  private int rodadaAtual;
  private Scanner entradaGlobal;
  private boolean jogoComecou;

  // Prepara o jogo
  public Jogo() {
    this.entradaGlobal = new Scanner(System.in);
    this.jogador1 = new Jogador("Jogador 1");
    this.jogador2 = new Jogador("Jogador 2");
    this.rodadaAtual = 1;
    this.jogoComecou = false;
  }

  // Método principal que faz o jogo funcionar.

  public int menu() {
    int resp = 0;
    boolean entradaValida = false;

    if (this.jogoComecou == false) {
      do {
        System.out.println("\n============ Menu ============");
        System.out.println("1 - Posicionar tesouros automaticamente");
        System.out.println("5 - Sair do jogo");
        System.out.println("================================");
        resp = this.entradaGlobal.nextInt();
        if (resp == 1 || resp == 5) {
          entradaValida = true;
        } else {
          System.out.println("Opcao inválida! Escolha 1 ou 5.");
        }
      } while (!entradaValida);
    } else {
      do {
        System.out.println("\n============ Menu " + this.rodadaAtual + ") ============");
        System.out.println("2 - Iniciar Rodada (Cavar)");
        System.out.println("3 - Ver rodadas restantes");
        System.out.println("4 - Ver placar");
        System.out.println("5 - Sair do jogo");
        System.out.println("================================");
        resp = this.entradaGlobal.nextInt();
        if (resp >= 2 && resp <= 5) {
          entradaValida = true;
        } else {
          System.out.println("Opcao inválida! Escolha de 2 a 5.");
        }
      } while (!entradaValida);
    }
    return resp;
  }

  public void executarPartida() {
    // 1. Menu inicial
    System.out.println("--- BEM-VINDO AO CAÇA AO TESOURO ---");
    System.out.println("Regra: 3 Verdes (6pts), 3 Amarelos (4pts), 2 Vermelhos (10pts)");

    // Faz cada jogador colocar os Tesouros
    boolean jogoAtivo = true;

    while (jogoAtivo) {
      int opcao = menu();

      switch (opcao) {
        case 1: // Posicionar Tesouros
          if (!this.jogoComecou) {
            System.out.println("Posicionando tesouros automaticamente.");
            jogador1.posicionarTesouro();
            jogador2.posicionarTesouro();
            System.out.println("Tesouros posicionados! O jogo vai comecar");
            this.jogoComecou = true;
          } else {
            System.out.println("Opcao inválida (O jogo já comecou)");
          }
          break;
        case 2: // Iniciar Rodada
          if (this.jogoComecou) {
            System.out.println("\n--- Rodada " + this.rodadaAtual + (" ---"));

            executarTurno(jogador1, jogador2); // Jogador 2 procura tesouros do jogador 1
            executarTurno(jogador2, jogador1); // Jogador 2 procura tesouros do jogador 1

            this.rodadaAtual++;
            if (this.rodadaAtual > 10) {
              jogoAtivo = false;
              declararVencedorPorPontos();
            }
          } else {
            System.out.println("Posicione os tesouros primeiro (Opcao 1)!");
          }
          break;
        case 3:
          if (this.jogoComecou) {
            int restantes = 10 - this.rodadaAtual + 1;
            System.out.println("Rodada atual: " + this.rodadaAtual + ". Restam " + restantes + " rodadas.");
          } else {
            System.out.println("O jogo ainda não comecou.");
          }
          break;
        case 4:
          System.out.println("--- Placar Atual ---");
          System.out.println(jogador1.getNome() + ": " + jogador1.getPontuacao() + " pontos");
          System.out.println(jogador2.getNome() + ": " + jogador1.getPontuacao() + " pontos");
          break;
        case 5:
          System.out.println("Obrigado por jogar");
          jogoAtivo = false;
          break;
      }
    }
  }

  // Executa a jogada de um jogador.
  private void executarTurno(Jogador atacante, Jogador defensor) {
    System.out.println("Turno de: " + atacante.getNome());

    // Pede as coordenadas da jogada (linha e coluna)
    System.out.print("Digite a LINHA (0-9) para procurar ");
    int linha = entradaGlobal.nextInt();
    System.out.print("Digite a COLUNA (0-9) para procurar ");
    int coluna = entradaGlobal.nextInt();

    // Validar se a jogada é repetida
    if (atacante.jaCavou(linha, coluna)) {
      System.out.println("Erro! Você já atacou aí. Perdeu o turno.");
      return;
    }

    // Se for nova, registra a tentativa
    atacante.registrarTentativa(linha, coluna);

    // Executa o "ataque"
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

  // Cria um objeto Jogo para iniciar a partida
  public static void main(String[] args) {
    Jogo minhaPartida = new Jogo();
    minhaPartida.executarPartida();
  }
}
