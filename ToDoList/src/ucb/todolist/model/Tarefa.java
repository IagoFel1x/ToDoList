package ucb.todolist.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa uma tarefa do gerenciador.
 */
public class Tarefa {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private Long id;
    private String titulo;
    private String descricao;
    private boolean concluida;
    private final LocalDateTime dataCriacao;

    /** Cria uma nova tarefa pendente, com a data de criação atual. */
    public Tarefa(String titulo, String descricao) {
        this(null, titulo, descricao, LocalDateTime.now(), false);
    }

    /** Construtor completo, útil para reconstruir tarefas vindas de outra fonte. */
    public Tarefa(Long id, String titulo, String descricao, LocalDateTime dataCriacao, boolean concluida) {
        this.id = id;
        setTitulo(titulo);
        setDescricao(descricao);
        this.dataCriacao = dataCriacao == null ? LocalDateTime.now() : dataCriacao;
        this.concluida = concluida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    /**
     * @throws IllegalArgumentException se o título for nulo ou estiver em branco
     */
    public final void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título da tarefa não pode ficar em branco.");
        }
        this.titulo = titulo.trim();
    }

    public String getDescricao() {
        return descricao;
    }

    public final void setDescricao(String descricao) {
        this.descricao = descricao == null ? "" : descricao.trim();
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getDataCriacaoFormatada() {
        return dataCriacao.format(FORMATO_DATA);
    }

    @Override
    public String toString() {
        String status = concluida ? "Concluída" : "Pendente";
        return "ID: " + id
                + " | Título: " + titulo
                + " | Descrição: " + descricao
                + " | Data: " + getDataCriacaoFormatada()
                + " | Status: " + status;
    }
}
