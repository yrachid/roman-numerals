
### InvalidInputFailure ao invés de InvalidInputException

Condições para uma entrada válida são parte da regra de negócio ao invés de uma interrupção de execução.

Uma classe comum ao invés de uma exception evita a criação desnecessária de stack trace

### Validacoes repetidas

Intencioalmente. Um objeto nao pode ser construido com um estado invalido, independentemente do contexto da construcao

### Uso de int ao inves de short

```shell script

        2 3 4 4
              - IV
            --- XL IV
          ----- CCC XL IV
        ------- MM CCC XL IV

        2 4 4 5
              - V
            --- XL V
          ----- CD XL V
        ------- MM CD XL V

        2 9 9 9
              - IX
            --- XC IX
          ----- CM XC IX
        ------- MM CM XC IX

        2 9 5 0
              -
            --- L
          ----- CM L
        ------- MM CM L

        2 9 0 1
              - I
            ---
          ----- CM I
        ------- MM CM I

        2 0 1 1
              - I
            --- X I
          -----
        ------- MM X I
```

