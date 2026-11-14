# ToDoList

Gerenciador de tarefas em **Java**, executado no terminal. Permite criar, listar, atualizar, remover, pesquisar e concluir tarefas, tudo mantido em memória.

## Funcionalidades

| Opção | Ação |
|------:|------|
| 1 | Criar tarefa (título obrigatório, descrição opcional) |
| 2 | Listar tarefas (ID, título, descrição, data de criação e status) |
| 3 | Atualizar título e descrição de uma tarefa |
| 4 | Remover tarefa por ID |
| 5 | Pesquisar tarefa por ID |
| 6 | Marcar tarefa como concluída |
| 7 | Exibir o total de tarefas concluídas |
| 8 | Sair |

Entradas inválidas (texto onde se espera um número, título em branco) são tratadas sem derrubar o programa.

## Tecnologias

- Java 8 ou superior
- Apache Ant / NetBeans (`build.xml` e `nbproject/`)

## Estrutura

```
ToDoList/
└── ToDoList/                      # Projeto NetBeans
    ├── build.xml
    ├── nbproject/
    └── src/ucb/todolist/
        ├── app/AppToDoList.java       # Menu principal (main)
        ├── model/Tarefa.java          # Entidade com validação de título
        └── service/TarefaService.java # Regras de negócio (CRUD em memória)
```

## Como executar

No **NetBeans**, abra a pasta `ToDoList/` e execute o projeto (F6).

Pelo terminal, dentro de `ToDoList/`:

```bash
ant run
```

Ou compilando direto com o JDK:

```bash
javac -d out $(find src -name "*.java")
java -cp out ucb.todolist.app.AppToDoList
```

## Observações

- Os dados ficam apenas em memória: ao encerrar o programa, as tarefas são perdidas. Persistir em arquivo ou banco é o próximo passo natural.
- A pasta `ToDoList/build/` ainda está versionada de commits antigos. O `.gitignore` evita novos commits dela; para removê-la do repositório: `git rm -r --cached ToDoList/build`.

## Autor

[Iago Cordeiro Felix](https://github.com/IagoFel1x)
