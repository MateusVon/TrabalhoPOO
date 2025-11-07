public class Jogo {
    public static void main(String[] args) {
        Tabuleiro mapaTesouro = new Tabuleiro();
        Tesouro t1 = new Tesouro("verde");
        Tesouro t2 = new Tesouro("amarelo");
        mapaTesouro.exibeMapa();
        boolean posiciona = mapaTesouro.posicionarTesouro(5, 2, t1);
        posiciona = mapaTesouro.posicionarTesouro(6, 0, t2);
        mapaTesouro.exibeMapa();

        double ponto = t1.getPontos();
        System.out.println(ponto);
    }
}
