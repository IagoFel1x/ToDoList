# ToDoList

Gerenciador de tarefas em **Java**, executado no terminal (console). Permite criar, listar, atualizar, remover, pesquisar e concluir tarefas, tudo mantido em memória.

## Funcionalidades

| Opção | Ação |
|------:|------|
| 1 | Criar tarefa (título e descrição) |
| 2 | Listar tarefas (ID, título, descrição, data de criação e status) |
| 3 | Atualizar título e descrição de uma tarefa |
| 4 | Remover tarefa por ID |
| 5 | Pesquisar tarefa por ID |
| 6 | Marcar tarefa como concluída |
| 7 | Exibir o total de tarefas concluídas |
| 8 | Sair |

## Tecnologias

- Java (JDK 8 ou superior)
- Apache Ant / NetBeans (arquivos `build.xml` e `nbproject/`)

## Estrutura

```
ToDoList/
├── AppToDoList.java      # Menu principal (classe com o método main)
├── TarefaService.java    # Regras de negócio: CRUD e contagem de tarefas
├── model/                # Entidade Tarefas
└── ToDoList/             # Projeto NetBeans (build.xml, nbproject, src/ucb)
```

## Como executar

A forma mais simples é abrir a pasta `ToDoList/` no **NetBeans** e executar o projeto (F6).

Pelo terminal, a partir da pasta do projeto:

```bash
ant run
```

## Observações

- Os dados ficam apenas em memória: ao encerrar o programa, as tarefas são perdidas.
- Os nomes de pacote do código estão inconsistentes (`ucb.aplicatvo`, `ucb.aplicativo` e `ucb.aplicacao`). Unificar em um único pacote é a próxima melhoria sugerida.

## Autor

[Iago Cordeiro Felix](https://github.com/IagoFel1x)
