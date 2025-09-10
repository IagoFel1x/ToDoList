package ucb.aplicatvo.cb;

import java.util.List;
import java.util.Scanner;
import ucb.aplicacao.model.Tarefas;
import ucb.aplicativo.service.TarefaService;
import java.util.Optional;

public class AppToDoList {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        TarefaService servico = new TarefaService();

        while (true) {
            System.out.println("\n===== GERENCIADOR DE TAREFAS =====");
            System.out.println("1. Criar Tarefa");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Atualizar Tarefa");
            System.out.println("4. Remover Tarefa");
            System.out.println("5. Pesquisar Tarefa por ID");
            System.out.println("6. Marcar como Concluída");
            System.out.println("7. Total de tarefas concluídas");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = -1;
            try {
                opcao = entrada.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite um número.");
                entrada.nextLine();
                continue;
            }
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = entrada.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = entrada.nextLine();
                    Tarefas nova = servico.criarTarefa(titulo, descricao);
                    System.out.println("Tarefa criada com sucesso: " + nova.getTitulo());
                    break;
                case 2:
                    List<Tarefas> tarefas = servico.listarTarefas();
                    if (tarefas.isEmpty()) {
                        System.out.println("Nenhuma tarefa cadastrada.");
                    } else {
                        System.out.println("\nLista de Tarefas:");
                        for (Tarefas t : tarefas) {
                            String status = t.isCompleta() ? "Concluída" : "Pendente";
                            System.out.println(
                                "ID: " + t.getId() +
                                " | Título: " + t.getTitulo() +
                                " | Descrição: " + t.getDescricao() +
                                " | Data: " + t.getDataCriacao().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
                                " | Status: " + status
                            );
                        }
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID da tarefa para atualizar: ");
                    Long idAtualizar = entrada.nextLong();
                    entrada.nextLine();
                    System.out.print("Novo Título: ");
                    String novoTitulo = entrada.nextLine();
                    System.out.print("Nova Descrição: ");
                    String novaDescricao = entrada.nextLine();
                    if (servico.atualizarTarefa(idAtualizar, novoTitulo, novaDescricao)) {
                        System.out.println("Tarefa atualizada com sucesso!");
                    } else {
                        System.out.println("Tarefa com ID " + idAtualizar + " não encontrada.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID da tarefa para remover: ");
                    Long idRemover = entrada.nextLong();
                    entrada.nextLine();
                    if (servico.removerTarefa(idRemover)) {
                        System.out.println("Tarefa removida com sucesso!");
                    } else {
                        System.out.println("Tarefa com ID " + idRemover + " não encontrada.");
                    }
                    break;
                case 5:
                    System.out.print("Digite o ID da tarefa para pesquisar: ");
                    Long idPesquisar = entrada.nextLong();
                    entrada.nextLine();
                    Optional<Tarefas> tarefaPesquisada = servico.buscarPorId(idPesquisar);
                    if (tarefaPesquisada.isPresent()) {
                        Tarefas t = tarefaPesquisada.get();
                        String status = t.isCompleta() ? "Concluída" : "Pendente";
                        System.out.println("\nTarefa Encontrada:");
                        System.out.println(
                            "ID: " + t.getId() +
                            " | Título: " + t.getTitulo() +
                            " | Descrição: " + t.getDescricao() +
                            " | Data: " + t.getDataCriacao().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
                            " | Status: " + status
                        );
                    } else {
                        System.out.println("Tarefa com ID " + idPesquisar + " não encontrada.");
                    }
                    break;
                case 6:
                    System.out.print("Digite o ID da tarefa para marcar como concluída: ");
                    Long idConcluir = entrada.nextLong();
                    entrada.nextLine();
                    if (servico.marcarComoConcluida(idConcluir)) {
                        System.out.println("Tarefa marcada como concluída com sucesso!");
                    } else {
                        System.out.println("Tarefa com ID " + idConcluir + " não encontrada.");
                    }
                    break;
                case 7:
                    int totalConcluidas = servico.totalTarefasConcluidas();
                    System.out.println("Total de tarefas concluídas: " + totalConcluidas);
                    break;
                case 8:
                    System.out.println("Saindo do sistema...");
                    entrada.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
    }
}