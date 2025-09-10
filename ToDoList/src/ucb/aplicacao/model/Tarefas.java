package ucb.aplicacao.model;

import java.time.LocalDateTime;

public class Tarefas {
    private Long id;
    private String titulo;
    private String descricao;
    private boolean completa;
    private LocalDateTime dataCriacao;

    // Construtor para nova tarefa
    public Tarefas(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = LocalDateTime.now();
        this.completa = false;
    }
    
    // Construtor completo
    public Tarefas(Long p_id, String p_titulo, String p_descricao, LocalDateTime p_dataCriacao, boolean p_completa) {
        this.id = p_id; 
        this.titulo = p_titulo;
        this.descricao = p_descricao;
        this.dataCriacao = p_dataCriacao;
        this.completa = p_completa;
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isCompleta() {
        return completa;
    }

    public void setCompleta(boolean completa) {
        this.completa = completa;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}