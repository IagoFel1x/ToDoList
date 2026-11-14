package ucb.todolist.app;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import ucb.todolist.model.Tarefa;
import ucb.todolist.service.TarefaService;

/**
 * Menu de terminal do gerenciador de tarefas.
 */
public class AppToDoList {

    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final TarefaService SERVICO = new TarefaService();

    public static void main(String[] args) {
        boolean executando = true;
        while (executando) {
            exibirMenu();
            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    criarTarefa();
                    break;
                case 2:
                    listarTarefas();
                    break;
                case 3:
                    atualizarTarefa();
                    break;
                case 4:
                    removerTarefa();
                    break;
                case 5:
                    pesquisarTarefa();
                    break;
                case 6:
                    concluirTarefa();
                    break;
                case 7:
                    System.out.println("Total de tarefas concluídas: " + SERVICO.totalTarefasConcluidas()
                            + " de " + SERVICO.totalDeTarefas());
                    break;
                case 8:
                    System.out.println("Saindo do sistema...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        ENTRADA.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===== GERENCIADOR DE TAREFAS =====");
        System.out.println("1. Criar Tarefa");
        System.out.println("2. Listar Tarefas");
        System.out.println("3. Atualizar Tarefa");
        System.out.println("4. Remover Tarefa");
        System.out.println("5. Pesquisar Tarefa por ID");
        System.out.println("6. Marcar como Concluída");
        System.out.println("7. Total de tarefas concluídas");
        System.out.println("8. Sair");
    }

    private static void criarTarefa() {
        String titulo = lerTexto("Título: ");
        String descricao = lerTexto("Descrição: ");
        try {
            Tarefa nova = SERVICO.criarTarefa(titulo, descricao);
            System.out.println("Tarefa criada com sucesso: " + nova.getTitulo() + " (ID " + nova.getId() + ")");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarTarefas() {
        List<Tarefa> tarefas = SERVICO.listarTarefas();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }
        System.out.println("\nLista de Tarefas:");
        for (Tarefa t : tarefas) {
            System.out.println(t);
        }
    }

    private static void atualizarTarefa() {
        long id = lerLong("Digite o ID da tarefa para atualizar: ");
        if (!SERVICO.buscarPorId(id).isPresent()) {
            System.out.println("Tarefa com ID " + id + " não encontrada.");
            return;
        }
        String novoTitulo = lerTexto("Novo Título: ");
        String novaDescricao = lerTexto("Nova Descrição: ");
        try {
            SERVICO.atualizarTarefa(id, novoTitulo, novaDescricao);
            System.out.println("Tarefa atualizada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removerTarefa() {
        long id = lerLong("Digite o ID da tarefa para remover: ");
        if (SERVICO.removerTarefa(id)) {
            System.out.println("Tarefa removida com sucesso!");
        } else {
            System.out.println("Tarefa com ID " + id + " não encontrada.");
        }
    }

    private static void pesquisarTarefa() {
        long id = lerLong("Digite o ID da tarefa para pesquisar: ");
        Optional<Tarefa> tarefa = SERVICO.buscarPorId(id);
        if (tarefa.isPresent()) {
            System.out.println("\nTarefa Encontrada:");
            System.out.println(tarefa.get());
        } else {
            System.out.println("Tarefa com ID " + id + " não encontrada.");
        }
    }

    private static void concluirTarefa() {
        long id = lerLong("Digite o ID da tarefa para marcar como concluída: ");
        if (SERVICO.marcarComoConcluida(id)) {
            System.out.println("Tarefa marcada como concluída com sucesso!");
        } else {
            System.out.println("Tarefa com ID " + id + " não encontrada.");
        }
    }

    // ---- Leitura de entrada ----

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return ENTRADA.nextLine();
    }

    /** Lê um inteiro; devolve -1 se o usuário digitar algo que não seja número. */
    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        try {
            return Integer.parseInt(ENTRADA.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /** Lê um ID; insiste até receber um número válido. */
    private static long lerLong(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Long.parseLong(ENTRADA.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }
}
