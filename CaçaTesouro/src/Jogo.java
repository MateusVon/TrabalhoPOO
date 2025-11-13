import java.util.Scanner;

public class Jogo {

  private Jogador jogador1;
  private Jogador jogador2;
  private int rodadaAtual;
  private Scanner entradaGlobal;
  private boolean jogoComecou;
  private Jogador jogadorDaVez;

  // Prepara o jogo
  public Jogo() {
    this.entradaGlobal = new Scanner(System.in);
    this.jogador1 = new Jogador("Jogador 1");
    this.jogador2 = new Jogador("Jogador 2");
    this.rodadaAtual = 1;
    this.jogoComecou = false;
    this.jogadorDaVez = this.jogador1;
  }

  // Método principal que faz o jogo funcionar.

  public int menu() {
    int resp = 0;
    boolean entradaValida = false;

    if (this.jogoComecou == false) {
      do {
        System.out.println("\n============ Menu ============");
        System.out.println("Vez de: " + this.jogadorDaVez.getNome());
        System.out.println("1 - Posicionar tesouros");
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
        System.out.println("Vez de: " + this.jogadorDaVez.getNome());
        System.out.println("2 - Procurar Tesouros");
        System.out.println("3 - Ver ilha de tesouros");
        System.out.println("4 - Ver rodadas restantes");
        System.out.println("5 - Ver placar");
        System.out.println("6 - Sair do jogo");
        System.out.println("================================");
        resp = this.entradaGlobal.nextInt();
        if (resp >= 2 && resp <= 6) {
          entradaValida = true;
        } else {
          System.out.println("Opcao inválida! Escolha de 2 a 6.");
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
            faseDePosicionamento();
            System.out.println("Tesouros posicionados! O jogo vai comecar");
            this.jogoComecou = true;
          } else {
            System.out.println("Opcao inválida (O jogo já comecou)");
          }
          break;
        case 2: // Iniciar Rodada
          if (this.jogoComecou) {
            System.out.println("\n--- Rodada " + this.rodadaAtual + (" ---"));
            executarTurno();

            this.rodadaAtual++;
            if (this.rodadaAtual > 20) {
              jogoAtivo = false;
              declararVencedorPorPontos();
            }
            if(jogadorDaVez.getPontuacao() == 50.0){
              jogoAtivo = false;
              System.out.println("\n" + jogadorDaVez.getNome() + " achou todos os tesouros!");
              declararVencedorPorPontos();
            }
          } else {
            System.out.println("Posicione os tesouros primeiro (Opcao 1)!");
          }
          break;
        case 3:
          if (this.jogoComecou) {
            this.jogadorDaVez.exibirIlhaTesouros();
          } else {
            System.out.println("O jogo ainda não comecou");
          }
          break;
        case 4:
          if (this.jogoComecou) {
            int restantes = 20 - this.rodadaAtual + 1;
            System.out.println("Rodada atual: " + this.rodadaAtual + ". Restam " + restantes + " rodadas.");
          } else {
            System.out.println("O jogo ainda não comecou.");
          }
          break;
        case 5:
          System.out.println("--- Placar Atual ---");
          System.out.println(jogador1.getNome() + ": " + jogador1.getPontuacao() + " pontos");
          System.out.println(jogador2.getNome() + ": " + jogador2.getPontuacao() + " pontos");
          break;
        case 6:
          System.out.println("Obrigado por jogar");
          jogoAtivo = false;
          break;
      }
    }
  }

  // Gerencia a fase de posicionamento dos dois jogadores
  private void faseDePosicionamento() {
    System.out.println("--- FASE DE POSICIONAMENTO: JOGADOR 1 ---");
    posicionarTesourosJogador(jogador1);

    System.out.println("\n\n--- FASE DE POSICIONAMENTO: JOGADOR 2 ---");
    posicionarTesourosJogador(jogador2);
  }

  // Gerencia o loop para um jogador posicionar os 8 tesouros
  private void posicionarTesourosJogador(Jogador jogador) {
    loopPosiciona(jogador, 3, "verde");
    loopPosiciona(jogador, 3, "amarelo");
    loopPosiciona(jogador, 2, "vermelho");

    System.out.println(jogador.getNome() + " terminou de posicionar!");
  }

  // Loop para posicionar
  private void loopPosiciona(Jogador jogador, int quant, String cor) {
    int tesourosPosicionados = 0;
    int linha, coluna;
    boolean sucesso;

    System.out.println("\n" + jogador.getNome() + ", posicione seus " + quant + " tesouros da cor " + cor + "!");

    while (tesourosPosicionados < quant) {
      System.out.println("\n--- Posicionando " + (tesourosPosicionados + 1) + "º tesouro " + cor + " ---\n");
      System.out.print("Informe a linha (0 - 9): ");
      linha = entradaGlobal.nextInt();
      System.out.print("--- Informe a coluna (0 - 9): ");
      coluna = entradaGlobal.nextInt();

      sucesso = jogador.posicionarTesouro(linha, coluna, cor);

      if (sucesso) {
        tesourosPosicionados++;
        System.out.println("\n--- Tesouro posicionado! ---");
      } else {
        System.out.println("\n--- Tente novamente. ---\n" );
      }
    }
  }

  // Executa a jogada de um jogador.
  private void executarTurno() {
    Jogador atacante = this.jogadorDaVez;
    Jogador defensor = (this.jogadorDaVez == this.jogador1) ? this.jogador2 : this.jogador1;

    System.out.println("\n--- Turno de: " + atacante.getNome() + " ---");

    System.out.println("--- Digite a linha (0-9) que voce quer cavar ---");
    int linha = entradaGlobal.nextInt();
    System.out.println("--- Digite a coluna (0-9) que voce quer cavar ---");
    int coluna = entradaGlobal.nextInt();

    if (atacante.jaCavou(linha, coluna)) {
      System.out.println("--- Erro! Você já cavou ai. Perde o turno --- ");
    } else {
      atacante.registrarTentativa(linha, coluna);
      Tesouro tesouroAchado = defensor.getMeuTabuleiro().receberAtaque(linha, coluna);
      if (tesouroAchado != null) {
        double pontosGanhos = tesouroAchado.getPontos();
        atacante.registrarResultadoDoAtaque(linha, coluna, pontosGanhos);
        System.out.println("Acertou! Ganhou " + pontosGanhos + " pontos!");
        atacante.adicionarPontos(pontosGanhos);
      } else {
        atacante.registrarResultadoDoAtaque(linha, coluna, 0);
        System.out.println("Nenhum tesouro aí.");
      }
    }
    this.jogadorDaVez = defensor;
  }

  private void declararVencedorPorPontos() {
    if (jogador1.getPontuacao() > jogador2.getPontuacao()) {
      System.out.println("Jogador 1 VENCEU!");
    } 
    else if(jogador1.getPontuacao() == jogador2.getPontuacao()){
      System.out.println("--- É um empate! ---");
    }
    else {
      System.out.println("Jogador 2 VENCEU");
    }
  }

  // Cria um objeto Jogo para iniciar a partida
  public static void main(String[] args) {
    Jogo minhaPartida = new Jogo();
    minhaPartida.executarPartida();
  }
}
