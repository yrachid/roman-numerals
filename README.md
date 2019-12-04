
# Conversor de numerais romanos

Enunciado do problema http://codingdojo.org/kata/RomanNumerals/

Tecnologias:
 - Gradle 5.2.1
 - Java 8
 - JUnit 4.12

Executando testes:

````shell script
./gradlew test
````

## Regras de negócio

### Não interpretar números maiores do que 3000

> There is no need to be able to convert numbers larger than about 3000.
>(The Romans themselves didn’t tend to go any higher)

## Notas de rodapé

Estas notas possivelmente choverão no molhado, mas deixei elas aqui de qualquer forma.

#### Gradle e Incremental Builds

A versão do Gradle utilizada executa o build incremental. Algumas etapas do build somente serão executadas se seus inputs e/ou outputs
apresentarem uma mudança no conteúdo, o que pode ser um comportamento estranho, como testes rodando instantaneamente, por exemplo.

Mais detalhes desse recurso: https://blog.gradle.org/introducing-incremental-build-support
