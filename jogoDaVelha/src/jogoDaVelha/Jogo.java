package jogoDaVelha;

import java.util.Scanner;

public class Jogo {

    public static Scanner entrada = new Scanner(System.in); // Variável de entrada de dados

    public static char[ ][ ] initialize() { // Inicializa a primeira Matriz.
        char[][] m = {{'_', '_', '_'},
                      {'_', '_', '_'},
                      {'_', '_', '_'}};
        return m;
    }

    public static boolean step(char M[ ][ ], int lin, int col, char gamer){ // Coloca "X" ou "O" na posição escolhida pelo jogador.
        if (M[lin][col] != 'X' && M[lin][col] != 'O') {
            M[lin][col] = gamer;
            return true;
        }
        System.out.println("Posição já ocupada, escolha novamente!");
        return false;
    }

    public static int status(char M[ ][ ], int jogadas){ // Verifica o estado atual do jogo.
        String[] T = new String[8];

        // Linhas
        T[0] = "" + M[0][0] + M[0][1] + M[0][2];
        T[1] = "" + M[1][0] + M[1][1] + M[1][2];
        T[2] = "" + M[2][0] + M[2][1] + M[2][2];

        // Colunas
        T[3] = "" + M[0][0] + M[1][0] + M[2][0];
        T[4] = "" + M[0][1] + M[1][1] + M[2][1];
        T[5] = "" + M[0][2] + M[1][2] + M[2][2];

        // Diagonais
        T[6] = "" + M[0][0] + M[1][1] + M[2][2];
        T[7] = "" + M[0][2] + M[1][1] + M[2][0];

        int vencedor = -1; // Valor padrão que indica que o jogo deve continuar.

        // Verifica se O ou X venceram.
        for (String T1 : T) {
            if (T1.equals("OOO")) {
                vencedor = 1;
            } else if (T1.equals("XXX")) {
                vencedor = 2;
            }
        }

        if (jogadas == 9 && vencedor < 1) // Verifica se houve empate.
            vencedor = 0;

        return vencedor;
    }

    public static void show(char M[][]) { // Exibe o tabuleiro atual.
        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++)
                System.out.printf("  |  " + M[i][j]);
            System.out.printf("  |\n");
        }
    }

    public static void instruct() { // Exibe as instruções do jogo.

            System.out.println("\n----------------------------------------------------------------");
            System.out.println("Para escolher a posição. Utilize os números do teclado numérico:");
            System.out.println("""
                               |  7  |  8  |  9  |
                               |  4  |  5  |  6  |
                               |  1  |  2  |  3  |""");

            System.out.println("O círculo sempre começa!\n");
    }

    public static void group() { // Exibe os integrantes do grupo.

            System.out.println("\n--------------------------------");
            System.out.println("""
                               Integrantes:
                               - Giovanni Assis Lopes
                               - Kaique Mantoanelli Silva
                               - Pedro Henrique Oliveira Dantas Lopes
                               - Rafael Serino Kiss\n""");
    }

    public static void game(){ // Função que inicializa o jogo.

        char tabuleiro[][] = initialize();
        int jogadas = 0;
        int alternar_jogador = 1; // Variável que determina qual jogador está jogando.

        // Exibe o tabuleiro vazio.
        System.out.println("----- Jogo da Velha -----");
        show(tabuleiro);

        OUTER:
        while (jogadas < 9) {
            int i = -1;
            int j = -1;
            boolean valido = false;
            char jogador;

            /* Math.signum retorna 1 para número positivo e -1 para número negativo
            Com isso podemos alternar os jogadores multiplicando a variável por -1 */ 
            if (Math.signum(alternar_jogador) == 1)
                jogador = 'O';
            else
                jogador = 'X';

            // Estrutura que valida se a jogada é possível e não permite valores fora do intervalo 1 <= x <= 9.
            while (!valido) {
                if (jogador == 'O')
                    System.out.println("Jogador O, informe a posição: ");
                else
                    System.out.println("Jogador X, informe a posição: ");

                int posicao = entrada.nextInt();
                switch (posicao) {
                    case 1 -> {
                        i = 2;
                        j = 0;
                    }
                    case 2 -> {
                        i = 2;
                        j = 1;
                    }
                    case 3 -> {
                        i = 2;
                        j = 2;
                    }
                    case 4 -> {
                        i = 1;
                        j = 0;
                    }
                    case 5 -> {
                        i = 1;
                        j = 1;
                    }
                    case 6 -> {
                        i = 1;
                        j = 2;
                    }
                    case 7 -> {
                        i = 0;
                        j = 0;
                    }
                    case 8 -> {
                        i = 0;
                        j = 1;
                    }
                    case 9 -> {
                        i = 0;
                        j = 2;
                    }
                }
                if (posicao <= 9 && posicao >= 1)
                    valido = step(tabuleiro, i, j, jogador);
                else
                    System.out.println("Posição inválida, tente novamente!");
            }
            
            // Exibe o tabuleiro atual após cada jogada
            System.out.println("");
            show(tabuleiro);

            jogadas++; // Incrementa o contador de jogadas
            alternar_jogador *= -1; // Inverte o valor para alternar o jogador 

            // É impossivel vencer com menos de 5 jogadas, logo verificamos o status do jogo apenas na rodada 5 para cima
            if (jogadas >= 5) {
                int resultado = status(tabuleiro, jogadas);
                switch (resultado) {
                    case 0 -> {
                        System.out.println("\n***** Empate! *****\n");
                        break OUTER;
                    }
                    case 1 -> {
                        System.out.println("\n***** Jogador O venceu! *****\n");
                        break OUTER;
                    }
                    case 2 -> {
                        System.out.println("\n***** Jogador X venceu! *****\n");
                        break OUTER;
                    }
                }
            }
        }
    }
}