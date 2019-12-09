# Conversor de numerais

![](https://github.com/yrachid/roman-numerals/workflows/number-converter-ci/badge.svg)

Enunciado do problema http://codingdojo.org/kata/RomanNumerals/

### Tecnologias

 - Gradle 5.2.1
 - Java 8
 - JUnit 4.12

### Executando testes

__Testes unitários:__

```bash
./gradlew test
```

__Testes funcionais:__

```bash
./gradlew functionalTest convertAllRomanNumbersTest
```

__Testes "semi-automatizados":__

Os testes "semi-automatizados" executarão a aplicação passando alguns parâmetros que cobrem múltiplos cenários. Eles não irão, entretanto,
verificar o resultado das conversões. Os testes podem ser executados com ou sem __Docker__:

```bash

# Converte todos os valores romanos (do 1 ao 3000) para arabico
./gradlew romanToArabic
./gradlew romanToArabicDocker

# Converte todos os valores arabicos (do 1 ao 3000) para romano
./gradlew arabicToRoman
./gradlew arabicToRomanDocker

# Executa diversas conversoes e passa algumas entradas invalidas
./gradlew miscConversions
./gradlew miscConversionsDocker
```

__Teste de conversão romano -> arábico:__

Este teste executa conversões de todos os números romanos de 1 a 3000. Ele falhará se encontrar alguma conversão que estiver incorreta. Ele
foi muito útil durante o desenvolvimento, quando o algoritmo estava convertendo 360 números incorretamente:

```bash
./gradlew convertAllRomanNumbersTest
```

### Integração Contínua

Os testes acima estão executando a cada push através do Github Actions. Um dos resultados [pode ser conferido aqui](https://github.com/yrachid/roman-numerals/commit/86c18b28167306585260d87e2edd1961ebc1790a/checks?check_suite_id=349293362#step:7:23)

### Executando a aplicação

#### Docker

```bash
docker build -t number-converter . && docker run number-converter app.jar 1 10 20 1999 2000 X MCM XXVIII lizard 0
```

#### Jar

```bash

./gradlew clean build && java -jar app/build/libs/*.jar 1 10 20 1999 2000 X MCM XXVIII lizard 0
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

### Lógica de conversão arábico -> romano

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

### Lógica de conversão romano -> arábico

Inicialmente, tentei implementar um algoritmo que percorresse os algarismos de dois em dois, somando-os ou subtraindo-os de acordo com a
convenção romana. Entretanto, este algoritmo não funciona dependendo do número (ele falhou em 360 das 3000 conversões).

A solução final foi calcular separadamente os algarismos que devem ser somados dos que devem ser subtraídos, evitando uma má interpretação
das posições dos algarismos (como aconteceu na primeira tentativa). O pseudo-código abaixo é implementado pela classe
`RomanToArabicNumberConverter`:

```python

def converter():
  valor_romano = [M, M, C, D, X, L, I, V]

  '''
    Encontra oos pares que devem ser subtraídos, armazenando
    tambem os seus indices, para que eles possam ser ignorados
    pela outra metade do algoritmo que soma os demais valores:
    {
      "indices": [2, 3, 4, 5, 6, 7],
      "algarismos":  [C, D, X, L, I, V]
    }
  '''

  pares_de_subtracao = encontra_pares_de_subtracao()

  '''
    Demais algarismos que nao foram selecionados pela funcao anterior. Basicamente, todos os algarismos cujos indices
    nao pertencem a pares_de_subtracao.indices. Neste caso:
    [M, M]
  '''
  algarismos_de_soma = encontra_pares_de_soma(pares_de_subtracao)

  subtotal_subtracao = (

    # Soma os resultados das subtracoes
    somar_resultados(

      '''
        Subtrai algarismo_direita - algarismo_esquerda,
        gerando uma nova lista com os resultados
      '''
      subtrair_pares(pares_de_subtracao)
    )
  )

  subtotal_soma = somar(algarismos_de_soma)

  return subtotal_subtracao + subtotal_soma
```

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

./scripts/build-and-run.sh 1 10 20 1999 2000 X MCM XVIII

# Saidas do Gradle e/ou Docker ...

Received Args: [1, 10, 20, 1999, 2000, X, MCM, XXVIII]

1       :       I
10      :       X
20      :       XX
1999    :       MCMXCIX
2000    :       MM
X       :       0010
MCM     :       1900
XXVIII  :       0028
```

### Conversão

Algumas das regras adotadas na implementação da conversão:

### Não interpretar números maiores do que 3000

Conforme descrito no enunciado:

> There is no need to be able to convert numbers larger than about 3000.
> (The Romans themselves didn’t tend to go any higher)

### Não interpretar números menores do que 1

Dado que este conceito não é representado pelos numerais romanos.

