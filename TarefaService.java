package ucb.aplicativo.service;

import ucb.aplicacao.model.Tarefas;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TarefaService {
    private List<Tarefas> tarefas = new ArrayList<>();
    private Long contadorId = 1L;

    // Criar tarefa
    public Tarefas criarTarefa(String titulo, String descricao) {
        Tarefas novaTarefa = new Tarefas(titulo, descricao);
        novaTarefa.setId(contadorId++);
        tarefas.add(novaTarefa);
        return novaTarefa;
    }

    // Listar tarefas
    public List<Tarefas> listarTarefas() {
        return new ArrayList<>(tarefas);
    }

    // Atualizar tarefa
    public boolean atualizarTarefa(Long id, String novoTitulo, String novaDescricao) {
        Optional<Tarefas> tarefa = tarefas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
        
        if (tarefa.isPresent()) {
            Tarefas t = tarefa.get();
            t.setTitulo(novoTitulo);
            t.setDescricao(novaDescricao);
            return true;
        }
        return false;
    }

    // Remover tarefa
    public boolean removerTarefa(Long id) {
        return tarefas.removeIf(t -> t.getId().equals(id));
    }

    // Buscar tarefa por ID
    public Optional<Tarefas> buscarPorId(Long id) {
        return tarefas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }
    
    // Marcar como concluída
    public boolean marcarComoConcluida(Long id) {
        Optional<Tarefas> tarefa = buscarPorId(id);
        if (tarefa.isPresent()) {
            tarefa.get().setCompleta(true);
            return true;
        }
        return false;
    }

    // Total de tarefas concluídas
    public int totalTarefasConcluidas() {
        return (int) tarefas.stream().filter(Tarefas::isCompleta).count();
    }
    
    // Método para verificar o total de tarefas
    public int totalDeTarefas() {
        return tarefas.size();
    }
}