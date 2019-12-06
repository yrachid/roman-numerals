# Conversor de numerais

![](https://github.com/yrachid/roman-numerals/workflows/number-converter-ci/badge.svg)

Enunciado do problema http://codingdojo.org/kata/RomanNumerals/

### Tecnologias
 - Gradle 5.2.1
 - Java 8
 - JUnit 4.12

### Executando testes

```bash
./gradlew test functionalTest
```

### Executando a aplicação

Dentro da pasta `scripts` existem dois scripts que automatizam a construção e execução da aplicação de duas maneiras: Como Docker ou sem.
Ambos recebem um número variável de parâmetros que serão os valores a serem convertidos pela aplicação.

Caso nenhum parâmetro seja passado aos scripts, eles utilizarão os valores pré-definidos no arquivo `scripts/test-values`.

#### Docker

```bash

# Utilizando valores pre-definidos
./scripts/docker-build-and-run.sh

# Utilizando valores especificados
./scripts/docker-build-and-run.sh 1 10 20 1999 2000
```

Para executar com Docker sem o script:

```bash
docker build -t number-converter .

docker run number-converter app.jar 1 10 20 1999 2000
```

#### Jar

De maneira similar à execução com Docker, basta utilizar o script:

```bash
# Utilizando valores pre-definidos
./scripts/build-and-run.sh

# Utilizando valores especificados
./scripts/build-and-run.sh 1 10 20 1999 2000
```

Para executar sem o script:

```bash

./gradlew clean build

java -jar app/build/libs/*.jar 1 10 20 1999 2000
```

## Decisões de implementação

Algumas notas à respeito de alguns aspectos da solução (onde alguns podem causar alguma estranheza).

### TDD e git log

A solução foi desenvolvida utilizando TDD, o que ajudou significativamente a definir o desenho da solução. Cada commit documentou
detalhadamente as mudanças introduzidas, podendo também servir como documentação. Para ver a evolução das mudanças em detalhe, pode-se
executar:

```bash
git log
```

### Lógica de conversão

A lógica de conversão consiste em converter cada posição do número arábico invidividualmente, concatenando os resultados:

```shell script
2 3 4 4
      - IV
    -   XL
  -     CCC
-       MM

Resultado: MM + CCC + XL + IV = MMCCCXLIV
```

Por se tratar de um sistema também posicional, cada posição de um valor arábico representará uma magnitude de valor diferente (milhar,
centena, dezena e unidade). Para cada uma destas magnitudes, devem haver algarismos romanos equivalentes. Entretanto, além de traduzir os
algarismos de maneira direta, a solução precisou considerar os significados adicionais das posições do sistema romano, como por exemplo,
quando um número menor está a esquerda de um maior, os dois devem ser subtraídos, não somados, noção tal que não existe no sistema arábico,
de modo que uma conversão direta não se faz possível.

A classe `PivotBasedPositionalConverter` utiliza uma lógica de "pivôs" que conseguem traduzir essa diferença posicional entre um sistema e
outro, independente da magnitude do valor.

### InvalidParameterFailure

Nos casos onde a interpretação de uma entrada falha, uma classe wrapper de erro é criada ao invés de lançar-se uma exceção. Esssa decisão
foi tomada por três motivos principais:

 - Exceções são ações interruptivas do fluxo de execução, e somente deveriam ser utilizadas em caso de erros "inesperados"
 - A classe wrapper permite tratar entradas inválidas como um caso de negócio de maneira mais confortável
 - Exceções constroem stack traces. Construir stack trace é algo custoso e desnecessário nesta situação.

### Uso de int em vez de short

Os valores primitivos de algumas classes poderiam utilizar short ao invés de int, dado que a aplicação deve suportar um intervalo limitado
de valores (e o sistema romano não deve mudar no futuro). Essa decisão seria mais em torno de legibilidade/documentação do código do que de
performance ou economia de espaço. Entretanto, não pareceu ser tão produtivo.

## Regras de negócio

### Entrada

#### Modo de entrada

A aplicação é de formato linha de comando. Ela receberá as entradas através dos argumentos passados ao jar, conforme descrito na seção
relacionada à execução da aplicação. A aplicação tentará converter cada um dos argumentos recebidos, exibindo os resultados de cada
conversão na saída, independentemente de êxito ou falha.

#### Múltiplos valores de entrada

A aplicação suporta um número variável de argumentos de entrada.

### Resultados

Independentemente da maneira como a aplicação é executada, para uma entrada `1 10 20 lizard`, a saída deve ser similar à seguinte:

```bash

./scripts/build-and-run.sh 1 10 20 lizard

# Saidas do Gradle e/ou Docker ...

Received args: [1, 10, 20]

1       : I
10      : X
20      : XX
lizard  : Invalid Input: input must be an integer number.
```

### Conversão

Algumas das regras adotadas na implementação da conversão:

### Não interpretar números maiores do que 3000

Conforme descrito no enunciado:

> There is no need to be able to convert numbers larger than about 3000.
> (The Romans themselves didn’t tend to go any higher)

### Não interpretar números menores do que 1

Dado que este conceito não é representado pelos numerais romanos.

