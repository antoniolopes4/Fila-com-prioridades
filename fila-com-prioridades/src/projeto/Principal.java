package projeto;

import java.util.Scanner;

public class Principal {
    
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        Fila fila = new Fila();
        

        // Thread para mostrar o estado da fila
        Thread mostrarFilaThread = new Thread(() -> {
            while (true) {
            	int flag = 1;
                if (!fila.estaVazia()) {
                    fila.mostrarEstadoDaFila();
                    fila.atenderCliente();
                    flag = 0;
                }
                
                if(fila.size() == 0 && flag == 0) {
                	fila.mostrarClientesAtendidos();
                }
                
                try {
                    Thread.sleep(1000); // Aguarda 1 segundo antes de atualizar
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Inicia a thread para mostrar o estado da fila e os clientes atendidos
        mostrarFilaThread.start();

        // Thread para interação com o usuário e inserção de novos clientes
        Thread interacaoUsuarioThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println();
                System.out.println("Insira o numero de tarefas do cliente:");
                int numTarefas = scanner.nextInt();
                
                // Validar se o número de tarefas está dentro do intervalo desejado
                if (numTarefas <= 0) {
                    System.out.println("Número de tarefas invalido. Insira um numero maior que 0.");
                    continue;
                }
                
                int prioridade;
                do {
                    System.out.println("Insira a prioridade do cliente (0 para prioridade normal, 6 a 10 para prioridade especial):");
                    prioridade = scanner.nextInt();
                    if (prioridade != 0 && (prioridade < 6 || prioridade > 10)) {
                        System.out.println("Prioridade invalida. Insira 0 para prioridade normal ou um número entre 6 e 10 para prioridade especial.");
                    }
                } while (prioridade != 0 && (prioridade < 6 || prioridade > 10));
                
                Cliente novoCliente = new Cliente(prioridade, numTarefas);
                fila.adicionarCliente(novoCliente);
            }
        });

        // Inicia a thread para interação com o usuário
        interacaoUsuarioThread.start();
    }
}
