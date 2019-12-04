
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

Casos de teste diversos:

```java
 assertThat(convert(900), equalTo("CM"));
 assertThat(convert(2011), equalTo("MMCMI"));
 assertThat(convert(2301), equalTo("MMCCCI"));
 assertThat(convert(2901), equalTo("MMCMI"));
 assertThat(convert(2950), equalTo("MMCML"));
 assertThat(convert(2999), equalTo("MMCMXCIX"));
 assertThat(convert(3000), equalTo("MMM"));
```

