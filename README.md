# Activitat 2 - Unit Testing & Mutation Testing

Projecte base per a l'activitat de JUnit 5, JaCoCo, PIT i introducció a Mockito.

## Classes incloses

- `Calculator.java`
- `DescompteService.java`
- `ComandaService.java`
- `StockRepository.java`

`Calculator` i `DescompteService` són les classes principals de l'activitat.

`ComandaService` i `StockRepository` serveixen únicament per a l'exercici introductori de Mockito.

## Els alumnes han de crear

- `CalculatorTest.java`
- `DescompteServiceTest.java`
- `ComandaServiceTest.java` (exercici breu de Mockito)

No s'inclouen tests resolts per evitar donar la solució de l'activitat.

## Comandes

Executar tests:

```bash
mvn test
```

Executar tests i generar cobertura JaCoCo:

```bash
mvn clean test
```

Informe JaCoCo:

```text
target/site/jacoco/index.html
```

Executar Mutation Testing amb PIT:

```bash
mvn org.pitest:pitest-maven:mutationCoverage
```

Informe PIT:

```text
target/pit-reports/
```

## Requisit recomanat

Java 17 o superior i Maven.
