# 🌊 A Descida ao Abismo

Desafio Semanal 02 | Trilha Backend | Orc'estra Gamificação | **Desafio em dupla**

---

## 🌊 O Chamado

A **Estação Farol Cobalto**, um laboratório científico a três mil metros de profundidade, parou de transmitir há cinco dias. Dentro dela estão doze pesquisadores, e o ar dos tanques de reserva dura **sete noites**. Depois disso, não há mais o que resgatar.

O Instituto Oceanográfico de Cobalto tem uma única cápsula de descida pronta e uma regra mais antiga que a própria instituição: **ninguém desce sozinho**. Todo mergulho profundo é feito em dupla, porque no abismo quem não tem parceiro não tem ninguém para dizer "pare" antes da hora.

Hoje à tarde, a Capitã **Marlene Vidal**, Mestre de Mergulho, reuniu os aprendizes no cais e sorteou as plaquetas de identificação dentro de um balde. Ninguém escolheu ninguém.

"A sua dupla é a pessoa que tirou a plaqueta ao lado da sua," disse ela. "Não me interessa se vocês se conhecem. Interessa que vão passar sete noites dependendo um do outro."

---

## 🧭 O Briefing da Capitã

Marlene aponta para a cápsula, uma esfera de aço com o painel de comando completamente apagado.

"O casco chegou do estaleiro sem o sistema de combate. A placa-mãe está vazia, e quem vai programá-la são vocês. Lá embaixo, o que ataca a cápsula não é uma coisa só: há piranhas, leviatãs e coisas que ninguém catalogou. O **Sonar** não sabe o nome de nenhuma delas, e não precisa saber."

Ela tira de um cofre uma folha amarelada, as quatro **Leis do Abismo**, escritas pelos primeiros mergulhadores da instituição:

1. *"O Sonar é cego. Ele nunca pergunta o que é a criatura, só três coisas: se ela ainda vive, quanto dói o golpe dela e quanto dói o golpe que ela recebe. Quem tenta descobrir o nome da criatura para decidir o que fazer com ela perde a descida."*
2. *"O colete de um mergulhador tem três bolsos. Quando o quarto item chega, o colete rasga. A expedição exige carregar bem mais do que isso, e só um reajuste do colete permite levar o que vier."*
3. *"O manifesto do Instituto lista tudo por valor: o mais valioso primeiro. Quando dois itens valem o mesmo, vale o nome que vem primeiro na ordem de dicionário."*
4. *"A pena que escreve o código troca de mão a cada meia hora. Nenhum código é selado sem que a outra mão o leia e o comente."*

---

## 🎯 Sua missão

Vocês são as duas mãos do Pacto de Mergulho. Juntos, precisam concluir o sistema da cápsula:

1. Criar as duas criaturas do catálogo: a **Piranha Rápida** e o **Leviatã Nulo**.
2. Reajustar o **Colete** para carregar quantos itens vierem, e listá-los em ordem de manifesto.
3. Programar o **Sonar** (a classe `CapsulaDeDescida`) para conduzir o combate, sem nunca olhar o rosto de uma criatura.
4. Sobreviver à **Descida Final**, na correção.

---

## 🧰 O que já vem pronto e o que vocês constroem

### Já vem pronto (não editem)

| Classe | O que faz |
|---|---|
| `Mergulhador` | O herói. Começa com 100 de oxigênio, que nunca fica abaixo de 0 nem acima do máximo. Métodos: `getOxigenio()`, `getOxigenioMaximo()`, `estaVivo()`, `receberDano(int)`, `recuperar(int)` e `atacar(Random)`, que devolve um dano de 5 a 10 |
| `Criatura` | Classe abstrata com o contrato de toda criatura: `getNome()`, `getVida()`, `estaViva()`, `receberDano(int)` (nunca deixa a vida abaixo de 0) e o método abstrato `int atacar(Random random)` |
| `ResultadoMergulho` | Enum com `SOBREVIVEU` e `AFOGADO` |

### O que vocês constroem (⭐ arquivos que vocês editam)

**`PiranhaRapida`** (estende `Criatura`)

- Vida inicial: 20. Nome: `"Piranha Rápida"`.
- `atacar` devolve a **soma de dois ataques** no mesmo turno. Cada ataque causa de 1 a 4 de dano, sorteado com o `Random` recebido (`random.nextInt(4) + 1`).

**`LeviataNulo`** (estende `Criatura`)

- Vida inicial: 80. Nome: `"Leviatã Nulo"`.
- `atacar` causa de 15 a 25 de dano (`random.nextInt(11) + 15`), um único ataque por turno.
- O **primeiro golpe recebido o atravessa**: a primeira chamada de `receberDano` não altera a vida, qualquer que seja o valor. Da segunda chamada em diante, o dano é aplicado normalmente. Uma criatura que "ainda não existe" não pode ser ferida.

**`Item`**

- Tem `nome` e `valor` (inteiro). Já vem com construtor e getters.
- Deve implementar `Comparable<Item>`: o item de **maior valor vem primeiro**. Em empate de valor, vem primeiro o nome que vier antes na ordem de `String.compareTo`.

**`Colete`**

- `void adicionar(Item item)`: guarda o item. **Sem limite de quantidade.**
- `int quantidade()`: quantos itens há no colete.
- `List<Item> listarPorValor()`: devolve uma **nova** lista ordenada pela ordem natural de `Item`. A ordem interna do colete **não pode mudar**.
- No começo, o colete usa `Item[3]` e quebra no quarto item. O trabalho de vocês é reajustá-lo. No final, **não pode sobrar nenhum array** dentro dele.
- Itens nunca são `null`.

**`CapsulaDeDescida`** (o Sonar)

- Construtor: `CapsulaDeDescida(Random random)`. Vocês guardam esse `Random` e o usam em todo o combate. **Nunca criem um `new Random()` próprio.**
- Método: `ResultadoMergulho descer(Mergulhador mergulhador, List<Criatura> criaturas)`.
- A lista recebida nunca é `null` e **não deve ser alterada** (nada de remover criaturas mortas ou reordenar).
- Regras do combate, nesta ordem exata:
  1. Se nenhuma criatura da lista estiver viva no início, devolva `SOBREVIVEU`.
  2. Enquanto o mergulhador estiver vivo e houver criatura viva, repita o turno:
     1. O mergulhador golpeia a **primeira criatura viva** da lista: `criatura.receberDano(mergulhador.atacar(random))`.
     2. Se ainda houver criatura viva, cada criatura viva ataca, **na ordem da lista**: `mergulhador.receberDano(criatura.atacar(random))`. Se o mergulhador morrer, o turno é interrompido e as criaturas restantes não atacam.
  3. Ao final, devolva `SOBREVIVEU` se não restou criatura viva, ou `AFOGADO` se o oxigênio do mergulhador chegou a zero.
- A ordem dos sorteios importa: primeiro o `atacar` do mergulhador, depois o de cada criatura, na ordem da lista.

> 🔒 **A Lei do Sonar na prática:** na `CapsulaDeDescida`, e em qualquer outra classe de vocês, é proibido usar `instanceof`, `getClass()`, conversão de tipo (cast) de `Criatura` ou comparar `getNome()` para decidir comportamento. O Sonar só pode usar `estaViva()`, `atacar(random)` e `receberDano(int)`. A única exceção é `instanceof` dentro de um método `equals`, se vocês o escreverem.

---

## 🐉 As provas

Para ganhar o **Selo do Pacto de Mergulho**, o código de vocês precisa passar em **todas** as provas:

| Prova | Nome | O que testa |
|---|---|---|
| 1 | O Fôlego | O `Mergulhador` respeita os limites de oxigênio (já passa desde o começo) |
| 2 | A Piranha | Dois ataques por turno, cada um de 1 a 4, vida 20 |
| 3 | O Leviatã | Dano de 15 a 25 e o primeiro golpe recebido o atravessa |
| 4 | O Colete | Carregar 5 itens ou mais sem estourar, sem alterar a ordem interna |
| 5 | O Manifesto | `listarPorValor()` em ordem de valor, com desempate por nome |
| 6 | A Cápsula | Combate completo com as regras do Sonar, `Random` injetado e lista intacta |
| ??? | **A Descida Final** | Cenários que vocês ainda não viram, executados na correção |

**Não existe vitória parcial.** Passar em cinco de seis é derrota. E passar em todas as visíveis ainda não garante o selo: a Descida Final só acontece na correção.

### 🌑 A Descida Final

Na sétima noite, a cápsula chega ao fundo e o Sonar registra uma assinatura que não consta em nenhum catálogo. Marlene avisou, antes de vocês descerem:

"O abismo não consulta o catálogo. Se o seu Sonar só funciona para as criaturas que vocês conhecem, ele não funciona."

Na correção, o assessor executa o seu Sonar contra criaturas que vocês nunca viram, em listas de tamanhos e ordens diferentes, e também confere o `Colete` com muito mais itens do que o seu teste usa. O código ainda passa por uma verificação que procura os atalhos proibidos da Lei do Sonar.

> 💡 A Descida Final existe para premiar quem construiu um Sonar que funciona para o desconhecido, não quem decorou as criaturas que conhece.

---

## ⏰ Prazo

**Entrega até:** (o assessor informa a data e o horário)

O que vale é o conteúdo que estiver no Pull Request até esse horário. Atraso desconta pontos (veja a seção de pontuação).

### 📆 Roteiro sugerido (não é regra)

| Noite | O que fazer |
|---|---|
| 1 | Primeiro encontro da dupla, convite do fork, ver as provas falhando, combinar quem digita primeiro |
| 2 | Primeira sessão de pair programming. **Fim da noite 2: prazo para avisar se o parceiro sumiu** (veja abaixo) |
| 3 e 4 | `PiranhaRapida`, `LeviataNulo` e `CapsulaDeDescida` |
| 5 | `Item` e `Colete` |
| 6 | Segunda sessão de pair programming, revisão cruzada, `ENTREGA.md`, PR aberto |
| 7 | Margem para ajustes. Entrega no horário combinado |

---

## 🗺️ Conhecendo o seu projeto

O projeto já vem **pronto e configurado**, na estrutura padrão de projetos Java com Maven, a mesma do desafio anterior e dos projetos de backend da EJ.

```
desafio02/
├── README.md                          # este arquivo (não edite)
├── ENTREGA.md                         # o relatório (CADA UM preenche a sua seção)
├── pom.xml                            # configuração do projeto (não mexa)
├── .gitattributes                     # finais de linha do Maven Wrapper (não mexa)
├── mvnw, mvnw.cmd, .mvn/              # Maven Wrapper: roda o Maven sem instalar nada
├── docs/
│   └── evidencias/                    # coloquem aqui o print dos testes
└── src/
    ├── main/java/com/orcestra/descidaaoabismo/
    │   ├── Criatura.java              # contrato das criaturas (não edite)
    │   ├── Mergulhador.java           # o herói (não edite)
    │   ├── ResultadoMergulho.java     # SOBREVIVEU ou AFOGADO (não edite)
    │   ├── CapsulaDeDescida.java      # ⭐ VOCÊS EDITAM AQUI
    │   ├── PiranhaRapida.java         # ⭐ VOCÊS EDITAM AQUI
    │   ├── LeviataNulo.java           # ⭐ VOCÊS EDITAM AQUI
    │   ├── Item.java                  # ⭐ VOCÊS EDITAM AQUI
    │   ├── Colete.java                # ⭐ VOCÊS EDITAM AQUI
    │   └── App.java                   # para experimentar na mão
    └── test/java/com/orcestra/descidaaoabismo/
        ├── MergulhadorTest.java       # as provas visíveis (não alterem)
        ├── CriaturasTest.java
        ├── ColeteTest.java
        ├── CapsulaDeDescidaTest.java
        └── DublesDeTeste.java         # apoio dos testes (não alterem)
```

| Pasta ou arquivo | O que é |
|---|---|
| `src/main/java` | O **código do programa**. É aqui que vocês trabalham |
| `src/test/java` | Os **testes** que verificam o código de vocês. Eles são o abismo |
| `com/orcestra/descidaaoabismo` | O **pacote**. Em Java, as pastas refletem o nome do pacote |
| `pom.xml` | A "receita" do projeto: versão do Java e bibliotecas usadas. Já está pronto |
| `target/` | Não existe no começo. Aparece depois que o Maven roda. É código compilado e relatórios, **gerado automaticamente**. Nunca vai para o GitHub |

---

## ⌨️ Comandos que vocês vão usar

Rodem todos dentro da pasta `desafio02/`. Use `./mvnw` no Linux e no macOS. No Windows, use `.\mvnw.cmd` (PowerShell) ou `mvnw.cmd` (Prompt de Comando).

| Comando | O que faz |
|---|---|
| `./mvnw test` | Compila tudo e roda os testes. **É o mergulho de vocês** |
| `./mvnw compile` | Só compila o código de `src/main` |
| `./mvnw clean` | Apaga a pasta `target/` |
| `./mvnw clean test` | Apaga o que estava compilado e roda tudo do zero |

Para rodar o `App` (o campo de treino) depois de compilar:

```bash
./mvnw compile
java -cp target/classes com.orcestra.descidaaoabismo.App
```

> ⏳ **Na primeira vez**, o Maven baixa algumas bibliotecas. Precisa de internet e pode levar alguns minutos. Nas próximas, é rápido.
>
> 🔕 Linhas `WARNING` sobre `System::load` ou `sun.misc.Unsafe` vêm do próprio Maven com JDKs mais novos. Podem ser ignoradas.

**Pré-requisito:** ter o **JDK 21** instalado. Para conferir, rodem `java -version`. Se algo não funcionar, falem com o assessor no canal da trilha antes do prazo, não no dia da entrega.

**Usando uma IDE?** Abram o projeto **como projeto Maven**: no IntelliJ, abram o `pom.xml`; no Eclipse, usem *File > Import > Existing Maven Projects*; no VS Code, abram a pasta com a extensão Java instalada. **Não criem nem movam pastas dentro de `src/`**: o Maven só enxerga `src/main/java` e `src/test/java`. Arquivos como `.classpath`, `.project` e `.settings/` são gerados pela IDE e não devem ir para o GitHub.

---

## 🤝 O Pacto de Mergulho: como a dupla trabalha

Este é o primeiro desafio em dupla da trilha, então o fluxo de Git muda em relação ao anterior: **a dupla inteira usa um único fork, uma única branch e abre um único Pull Request.**

| Papel | Quem é |
|---|---|
| **Dono do fork** | Quem tiver o primeiro nome primeiro na ordem alfabética (se empatar, o sobrenome) |
| **Parceiro** | O outro membro da dupla |

- **Pena alternada.** Em cada sessão de trabalho em conjunto, quem digita troca a cada **30 minutos**. Quem não digita lê, questiona e aponta riscos.
- **Mínimo de duas sessões de 1 hora** em que os dois trabalham juntos (call com compartilhamento de tela, VS Code Live Share ou presencial). Registrem no `ENTREGA.md` a data e quem digitou primeiro.
- **Cada um commita com o próprio nome.** Antes de começar, confiram `git config user.name` e `git config user.email`. Os commits são a prova de quem fez o quê, então não commitem tudo por uma só pessoa.
- **Revisão cruzada.** Cada integrante deixa **ao menos 2 comentários substantivos** no PR, na aba **Files changed**, em código que o **parceiro** digitou. Um comentário substantivo aponta um risco, propõe uma alternativa ou pergunta o motivo de uma decisão. "Ficou bom" não conta.
- **Cada um precisa saber explicar qualquer linha** do projeto, inclusive as que o parceiro digitou.

### 🌫️ Se um dos mergulhadores sumir

Se até o **fim da segunda noite** o seu parceiro não tiver respondido ao convite ou à primeira mensagem, avise o assessor no canal da trilha. Depois da confirmação, você segue sozinho em um **Pacto de Uma Mão**: a Lei da Pena Alternada e o critério de colaboração deixam de valer, e a sua nota é calculada sobre o que resta (veja a pontuação). Ninguém é punido por isso. Quem sumiu sem avisar responde ao assessor.

---

## 🧭 Como trabalhar, passo a passo

O fluxo base é o do `README.md` da raiz (fork, `upstream`, sincronizar, branch, PR). Aqui estão as diferenças para a dupla.

1. **Combinem o primeiro encontro** e leiam este README juntos. Definam quem é o dono do fork pela regra acima.
2. **O dono do fork** faz o fork (se ainda não tiver, seção 3 do README da raiz), configura o `upstream` e sincroniza com a `main` original (seção 4.1). Depois convida o parceiro: no GitHub, abra o **seu fork**, vá em **Settings > Collaborators > Add people** e adicione o parceiro. Quem tem um fork do desafio anterior não precisa dele agora.
3. **O dono cria e envia a branch do desafio**, para o parceiro encontrá-la:

   ```bash
   git checkout main
   git pull origin main
   git checkout -b desafio02
   git push -u origin desafio02
   cd desafio02
   ```

4. **O parceiro aceita o convite** (chega por e-mail e nas notificações do GitHub) e clona **o fork do dono**, não o repositório original e não o fork dele mesmo:

   ```bash
   git clone https://github.com/<dono-do-fork>/<repositorio>.git
   cd <repositorio>
   git checkout desafio02
   cd desafio02
   ```

   Nunca trabalhem na `main` do fork. Todo o trabalho acontece na branch `desafio02`.

5. **Vejam o mergulho perdido.** Dentro de `desafio02/`, rodem `./mvnw test`. As provas 2 a 6 devem aparecer com **Failures** e **Errors**, e só o Fôlego passa. No começo, a linha final é `Tests run: 32, Failures: 7, Errors: 18`. Isso é esperado.
6. **Abram os arquivos ⭐** e leiam os comentários `TODO`. Eles resumem as regras.
7. **Resolvam uma prova por vez.** Mexam um pouco, rodem `./mvnw test`, leiam o resultado, ajustem.
8. **Antes de cada sessão de trabalho, rodem `git pull`. Ao terminar, `git add .`, `git commit` e `git push`.** Se os dois mexerem no mesmo arquivo ao mesmo tempo, vai dar conflito. Combinem quem mexe em qual arquivo.
9. **Façam um commit a cada prova vencida**, no padrão `tipo: descrição` (por exemplo, `feat: implementa a piranha rapida`).
10. **Testem na mão** editando o `App.java` com cenários de vocês: uma lista vazia, uma lista só com piranhas, um mergulhador quase sem oxigênio.
11. **Quando tudo estiver verde**, salvem um print do terminal em `docs/evidencias/`.
12. **Preencham o `ENTREGA.md`.** Cada um escreve **a própria seção**, em commit próprio, para não gerar conflito. Responda individualmente as perguntas de reflexão.
13. **O dono abre o Pull Request**, seguindo a seção 4.5 do README da raiz:

    | Campo | Valor correto |
    |---|---|
    | base repository | `<organizacao>/<repositorio>` (o **original**) |
    | base | `main` |
    | head repository | `<dono-do-fork>/<repositorio>` |
    | compare | `desafio02` |

    **Título:** `[Desafio 02] Nome Sobrenome e Nome Sobrenome` (os dois nomes). No Pacto de Uma Mão, só o seu nome.

    Antes de confirmar, abram a aba **Files changed** e conferam que só aparecem arquivos da pasta `desafio02/`.

14. **Os dois comentam no PR**, cumprindo a revisão cruzada. Colem este checklist na descrição do PR e marquem os itens:

    ```
    - [ ] Branch `desafio02` e título do PR no padrão
    - [ ] Files changed só com arquivos de desafio02/
    - [ ] ./mvnw test com Failures: 0 e Errors: 0 (print em docs/evidencias/)
    - [ ] Sem target/ nem arquivos de IDE
    - [ ] Cada um com ao menos 3 commits no próprio nome
    - [ ] Cada um com ao menos 2 comentários no código do parceiro
    - [ ] Cada um preencheu a sua seção do ENTREGA.md
    - [ ] Sem instanceof, getClass(), cast de Criatura ou getNome() para decidir comportamento
    ```

**Não façam o merge.** O assessor vai revisar, comentar e fechar o PR, e isso é normal: o PR é a entrega. Se o assessor pedir ajustes, façam novos commits na mesma branch e `git push`. O PR se atualiza sozinho, não abram outro.

### 🔎 Como ler o resultado do `./mvnw test`

Quando uma prova derrota vocês, o Maven mostra algo parecido com isto (o formato exato varia):

```
[ERROR] Failures:
[ERROR]   CapsulaDeDescidaTest.sonarConduzCombate:NN O Sonar devolveu o resultado errado ==> expected: <SOBREVIVEU> but was: <AFOGADO>
[ERROR] Errors:
[ERROR]   ColeteTest.carregaCincoItens:NN ArrayIndexOutOfBounds Index 3 out of bounds for length 3
[ERROR] Tests run: NN, Failures: X, Errors: Y, Skipped: 0
```

- `expected` é o que o teste esperava. `but was` é o que o **código de vocês devolveu**.
- O nome do teste diz qual prova acertou vocês.
- **Errors** é diferente de **Failures**: significa que o código quebrou com uma exceção. Role para cima e leia a mensagem. O `ArrayIndexOutOfBounds` acima é exatamente o colete rasgando.
- **Olhem sempre a linha `Tests run:`.** A vitória nas provas visíveis é `Failures: 0, Errors: 0`.
- `BUILD SUCCESS` **sozinho não prova nada**. Se aparecer `No tests to run` ou `Tests run: 0`, o Maven não encontrou os arquivos. Confiram se eles estão em `src/main/java` e `src/test/java`, dentro das pastas `com/orcestra/descidaaoabismo`.

> ⚠️ A prova do **Fôlego passa por acaso desde o início**, porque o `Mergulhador` já vem pronto. Esse "verde" não prova nada. Não comemorem ainda.

---

## 📜 Pergaminho de Dicas (abram só se travarem)

<details>
<summary>Dica 1: a Piranha e o Leviatã</summary>

Cada criatura estende `Criatura` e chama `super(nome, vida)` no construtor. A Piranha só precisa implementar `atacar`. O Leviatã também precisa **sobrescrever** `receberDano`: pense em uma variável que lembra se o primeiro golpe já foi absorvido, e em quando chamar o `super.receberDano(...)`.

</details>

<details>
<summary>Dica 2: o loop do Sonar</summary>

Quebrem o turno em dois métodos auxiliares: um que acha a primeira criatura viva da lista e outro que diz se ainda há alguma viva. O Sonar não precisa saber o que a criatura é, só chamar `estaViva()`, `atacar(random)` e `receberDano(int)`.

</details>

<details>
<summary>Dica 3: o colete que rasga</summary>

Leiam a mensagem do `ArrayIndexOutOfBounds`. O problema não é o limite de 3 em si, é que a estrutura de dados escolhida tem tamanho fixo. Qual classe de `java.util` cresce sozinha?

</details>

<details>
<summary>Dica 4: a ordem do manifesto</summary>

`compareTo` devolve negativo, zero ou positivo. Para colocar o **maior** valor primeiro, pense em qual dos dois operandos vai à esquerda na comparação. Para listar sem mexer na ordem interna, trabalhem sobre uma **cópia** da lista.

</details>

Se continuarem travados por mais de 40 minutos, escrevam a dúvida no canal da trilha com o que já tentaram.

---

## 📏 Regras

- **Proibido:**
  - alterar `Criatura.java`, `Mergulhador.java`, `ResultadoMergulho.java` e os arquivos de teste fornecidos
  - escrever respostas fixas para os casos que vocês veem (como `if (criaturas.size() == 2) return ResultadoMergulho.SOBREVIVEU;`)
  - usar `instanceof`, `getClass()`, cast de `Criatura` ou comparar `getNome()` para decidir comportamento (exceto `instanceof` dentro de um `equals`)
  - criar um `new Random()` próprio no lugar do injetado
  - alterar a lista de criaturas recebida
  - deixar qualquer array dentro do `Colete`
  - versionar a pasta `target/`
  - alterar arquivos fora da pasta `desafio02/`, nem a `main` do fork
- **Permitido:**
  - `ArrayList`, `List.sort`, `Collections.sort`, `Comparable`, laços, `if` e métodos auxiliares
  - criar **arquivos novos** para os seus próprios testes e criaturas (veja os bônus), sem mexer nos fornecidos
- **Ferramentas de IA:** podem ser usadas para tirar dúvida de conceito. Pedir o código pronto tira justamente o que este desafio quer treinar, e a Descida Final mostra quando isso aconteceu.
- **Os PRs são públicos.** Todos os membros conseguem ver o código uns dos outros. Copiar a solução de outra dupla não vale, e os testes ocultos e as perguntas de reflexão existem justamente para isso.

---

## 🏆 Recompensas e pontuação

O **Selo do Pacto de Mergulho** só é concedido se o código passar nas 6 provas visíveis **e** em toda a Descida Final. O selo é **da dupla**. Quem o ganha avança na história para o próximo capítulo.

Além do selo, cada integrante soma **Pontos de Glória** para o ranking da EJ. A nota base vai até **100 pontos** e os bônus somam até **5 pontos**, para um máximo de **105**.

### Nota base (100 pontos)

| Critério | Pontos | Avaliado |
|---|---|---|
| Selo conquistado (todas as provas e a Descida Final) | 50 | pela dupla |
| Qualidade do código | 20 | pela dupla |
| Colaboração (commits de cada um, comentários cruzados no PR, sessões de pair programming registradas) | 10 | individual |
| Estrutura e entrega (branch e título do PR no padrão, só a pasta `desafio02/` alterada, commits no padrão, sem `target/`) | 10 | pela dupla |
| `ENTREGA.md` preenchido e perguntas de reflexão | 10 | individual |
| **Total da nota base** | **100** | |

**Como os 20 pontos de qualidade são distribuídos:**

| Item | Pontos |
|---|---|
| Nomes claros de variáveis e métodos | 5 |
| Sem código repetido (métodos auxiliares quando fizer sentido) | 5 |
| Legibilidade e indentação | 5 |
| Sem atalhos proibidos (respostas fixas, checagem de tipo, alterar a lista recebida) | 5 |

### Bônus (até 5 pontos)

| Bônus | Pontos |
|---|---|
| Arquivo novo `MeusTestesTest.java` com 3 ou mais testes seus, em `src/test` | 3 |
| Uma terceira criatura criada pela dupla, com teste próprio, funcionando no Sonar **sem alterar** a `CapsulaDeDescida` | 2 |
| **Total dos bônus** | **5** |

**Nota máxima: 105 pontos (100 da base + 5 de bônus).**

- **Pacto de Uma Mão:** o critério de colaboração (10 pontos) é dispensado, e a nota é calculada sobre os 90 pontos restantes, convertida para 100.
- **Atraso na entrega:** desconto de 10 pontos.
- **Código idêntico ao de outra dupla:** zera o `ENTREGA.md` e a qualidade do código, e abre conversa individual.
- Todo membro recebe **3 pontos fortes e 3 pontos de melhoria** como comentários no Pull Request.

---

Boa descida, mergulhadores. O ar da Farol Cobalto não espera. 🌊
