# Entrega: Semana 01, A Prova dos Elementos

**Nome:**
**Data:**

## O que foi implementado

- [x] Ilusão 1: A Ilusão Comum
- [x] Ilusão 2: A Ilusão Gêmea
- [x] Ilusão 3: A Ilusão Solitária
- [x] Ilusão 4: A Ilusão do Vazio
- [ ] Bônus: `MeusTestesTest.java` com 3 ou mais testes meus
- [ ] Bônus: segunda solução com Streams

## Resultado dos testes
```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.orcestra.provadoselementos.ArenaDoArquivistaTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.067 s -- in com.orcestra.provadoselementos.ArenaDoArquivistaTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  10.260 s
[INFO] Finished at: 2026-10-26T14:08:21-03:00
[INFO] ------------------------------------------------------------------------
```
## Decisões que tomei

Utilizei condicionais para validar se a lista estava vazia, em primeiro lugar, para evitar melhorar o processo de otimização. Depois, criei uma variável para guardar o valor de saída e comparei o tamanho dos itens para armazenar o resultado. Por fim, utilizei a função **compareTo** para verificar a ordem dos itens do mesmo tamanho. 

## Dificuldades

O que me travou foi apenas a sintaxe da linguagem, porém a lógica do código eu já sabia.

## Perguntas de reflexão

**1. Qual ilusão te enganou na primeira tentativa e o que o resultado dela te ensinou?**

A Ilusao Gemea

**2. Por que o resultado de `compareTo` serve para ordenar em dicionário? O que ele devolve para `"Cinzas"` e `"Pedras"`?**

Ele devolve -1, pois cinzas vem primeiro que pedras no dicionário.

**3. O que acontece com a pasta `target/` quando você roda `./mvnw clean`? Por que ela não vai para o GitHub?**

Ela é apagada, além disso, ela não vai pro github, pois está no gitignore.
