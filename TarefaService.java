package ucb.aplicativo.servico;

import java.util.ArrayList;
import java.util.List;
import ucb.aplicativo.model.Tarefa;

public class TarefaService {

private List<Tarefa> listaDeTarefas = new ArrayList<>();
private long  proximoId = 1L;

public Tarefa criarTarefa(String titulo, String descricao) {
Tarefa novaTarefa = new Tarefa(titulo, descricao);
novaTarefa.setId(proximoId++);
listaDeTarefas.add(novaTarefa);
return novaTarefa;
}

public List<Tarefa> obterTodasAsTarefas() {
return listaDeTarefas;
}

public Tarefa buscarPorId(Long id) {
for (int i = 0; i < listaDeTarefas.size(); i++) {
Tarefa tarefa = listaDeTarefas.get(i);
if (tarefa.getId().equals(id)) {
return tarefa;
}
}
return null;
}
}