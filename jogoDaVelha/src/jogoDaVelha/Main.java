package jogoDaVelha;

public class Main {
    public static void main(String[] args) {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("""
                              Menu do Jogo da Velha:
                              1. Novo Jogo
                              2. Instru��es
                              3. Integrantes do Grupo
                              4. Sair""");
            System.out.println("Op��o: ");
            
            int escolha = Jogo.entrada.nextInt();
            switch (escolha) {
                case 1 -> Jogo.game();
                case 2 -> Jogo.instruct();
                case 3 -> Jogo.group();
                case 4 -> {
                    System.out.println("Fim do Programa");
                    Jogo.entrada.close();
                    exit = true;
                }
                default -> System.out.println("\nOp��o inv�lida. Tente novamente!\n");
            }
        }
    }
}
