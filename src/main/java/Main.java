// Ponto de entrada da aplicacao Java Event Planner.
// Por enquanto so imprime uma mensagem de inicializacao.
// A UI em Swing e a persistencia em CSV vem nas proximas etapas.

public class Main {

    // RNF01 / DT02: aplicacao single-threaded.
    // Nao criamos Thread, Runnable nem SwingWorker em lugar nenhum.
    // Quando a UI existir, tudo roda na EDT nativa do Swing.
    public static void main(String[] args) {
        System.out.println("Java Event Planner - inicializando...");
    }
}
