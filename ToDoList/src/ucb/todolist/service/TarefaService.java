package ucb.todolist.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import ucb.todolist.model.Tarefa;

/**
 * Regras de negócio do gerenciador de tarefas. Os dados ficam em memória.
 */
public class TarefaService {

    private final List<Tarefa> tarefas = new ArrayList<>();
    private long proximoId = 1L;

    /**
     * @throws IllegalArgumentException se o título estiver em branco
     */
    public Tarefa criarTarefa(String titulo, String descricao) {
        Tarefa nova = new Tarefa(titulo, descricao);
        nova.setId(proximoId++);
        tarefas.add(nova);
        return nova;
    }

    /** Retorna uma visão somente leitura da lista. */
    public List<Tarefa> listarTarefas() {
        return Collections.unmodifiableList(tarefas);
    }

    public Optional<Tarefa> buscarPorId(long id) {
        return tarefas.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    /**
     * @return false se a tarefa não existir
     * @throws IllegalArgumentException se o novo título estiver em branco
     */
    public boolean atualizarTarefa(long id, String novoTitulo, String novaDescricao) {
        Optional<Tarefa> tarefa = buscarPorId(id);
        if (!tarefa.isPresent()) {
            return false;
        }
        tarefa.get().setTitulo(novoTitulo);
        tarefa.get().setDescricao(novaDescricao);
        return true;
    }

    public boolean removerTarefa(long id) {
        return tarefas.removeIf(t -> t.getId() == id);
    }

    public boolean marcarComoConcluida(long id) {
        Optional<Tarefa> tarefa = buscarPorId(id);
        tarefa.ifPresent(t -> t.setConcluida(true));
        return tarefa.isPresent();
    }

    public int totalTarefasConcluidas() {
        return (int) tarefas.stream().filter(Tarefa::isConcluida).count();
    }

    public int totalDeTarefas() {
        return tarefas.size();
    }
}
