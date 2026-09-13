# Activitat 2 - Unit Testing, Cobertura, Mutation Testing i Mockito

Aquest projecte serveix com a plantilla base (*starter*) per a la pràctica de proves unitàries a Java. L'objectiu principal és aprendre i aplicar les tècniques fonamentals de verificació de programari: disseny de casos de prova amb **JUnit 5**, mesura de cobertura de codi amb **JaCoCo**, avaluació de la qualitat dels tests mitjançant **Mutation Testing (PIT)** i aïllament de dependències amb **Mockito**.

---

## 🎯 Objectius d'aprenentatge

1. **JUnit 5 bàsic i parametritzat**:
   - Escriure tests unitaris assertius (`assertEquals`, `assertTrue`, `assertThrows`, etc.).
   - Utilitzar proves parametritzades (`@ParameterizedTest`, `@CsvSource`, `@ValueSource`) per evitar duplicació de codi de test.
2. **Mesura i anàlisi de cobertura (JaCoCo)**:
   - Comprendre la cobertura de línia (*Line Coverage*) i de branca (*Branch Coverage*).
   - Identificar camins d'execució no coberts i condicions límit.
3. **Mutation Testing (PIT)**:
   - Entendre el concepte de *mutant* (canvis sintàctics automàtics al codi font) i com avaluar l'eficàcia dels tests per "matar" aquests mutants (*Mutation Score*).
4. **Dobles de prova i aïllament amb Mockito**:
   - Aprendre a aïllar una classe de les seves dependències externes mitjançant *mocks*.
   - Configurar respostes fictícies (*stubbing* amb `when(...).thenReturn(...)`).
   - Verificar interaccions i comportaments (`verify(...)`, `verifyNoInteractions(...)`).

---

## 📂 Estructura del projecte

```text
activitat2-unit-testing/
├── pom.xml                                    # Configuració Maven (JUnit 5, Mockito, JaCoCo, PIT)
├── .gitignore                                 # Fitxers exclosos de Git (target, IDEs, etc.)
├── README.md                                  # Guia del projecte
└── src/
    ├── main/java/cat/uvic/testing/            # Classes de negoci (producció)
    │   ├── Calculator.java                    # Lògica bàsica per a proves unitàries i parametritzades
    │   ├── DescompteService.java              # Lògica condicional per a cobertura de branques i PIT
    │   ├── StockRepository.java               # Interfície de dependència (accés a dades d'estoc)
    │   └── ComandaService.java                # Servei amb dependència injectable per a Mockito
    └── test/java/cat/uvic/testing/            # Paquet on l'alumnat implementarà els tests
```

---

## 📦 Classes del domini

### 1. `Calculator.java`
Conté operacions matemàtiques bàsiques (`suma`, `resta`, `multiplica`, `divideix` i `potencia`).
* **Aspectes a testejar:**
  * Resultats correctes per a valors positius, negatius i zero.
  * Llançament d'excepcions (`IllegalArgumentException`) en dividir per zero o en demanar potències amb exponent negatiu.
  * Ús de proves parametritzades (`@ParameterizedTest`).

### 2. `DescompteService.java`
Calcula el preu final aplicant regles de descompte segons l'import de la compra i si el client és *Premium* (20% per a compres $\ge 100$, 10% per a compres estàndard $\ge 100$, 0% per a imports $< 100$).
* **Aspectes a testejar:**
  * Validació d'imports invàlids (negatius).
  * Anàlisi de valors límit (per exemple: `99.99`, `100.0`, `100.01`).
  * Cobertura del 100% de branques i condicions booleanes compostes.

### 3. `StockRepository.java` & `ComandaService.java`
Representen una arquitectura desacoblada: `ComandaService` necessita comprovar si hi ha estoc d'un producte abans de permetre la compra, delegant aquesta comprovació a `StockRepository`.
* **Aspectes a testejar amb Mockito:**
  * Crear un mock de `StockRepository` i injectar-lo a `ComandaService`.
  * Simular retorn `true` / `false` del repositori i verificar el comportament de `potComprar`.
  * Comprovar que si el producte és `null` o buit, es llança `IllegalArgumentException` i **no es crida mai** al repositori (`verifyNoInteractions`).
  * Verificar que es fa exactament la crida esperada al repositori (`verify`).

---

## 📝 Tasques a realitzar per l'alumnat

L'alumnat ha de crear els següents fitxers dins de `src/test/java/cat/uvic/testing/`:

1. **`CalculatorTest.java`**: Tests unitaris i parametritzats per a totes les operacions i excepcions de `Calculator`.
2. **`DescompteServiceTest.java`**: Bateria de tests per cobrir tots els camins de decisió i resistir el mutation testing de `DescompteService`.
3. **`ComandaServiceTest.java`**: Tests amb Mockito utilitzant `@ExtendWith(MockitoExtension.class)`, `@Mock`, `@InjectMocks`, `when()` i `verify()`.

---

## 🚀 Comandes d'execució

### 1. Executar els tests unitaris
```bash
mvn test
```

### 2. Executar tests i generar l'informe de cobertura JaCoCo
```bash
mvn clean test
```
* **Ubicació de l'informe:** Obre al navegador el fitxer:
  ```text
  target/site/jacoco/index.html
  ```

### 3. Executar Mutation Testing amb PIT
```bash
mvn org.pitest:pitest-maven:mutationCoverage
```
* **Ubicació de l'informe:** Obre l'últim informe generat a la carpeta:
  ```text
  target/pit-reports/YYYYMMDDHHMM/index.html
  ```

---

## 💻 Requisits de l'entorn

* **Java JDK:** 17 o superior.
* **Apache Maven:** 3.8 o superior.
* **IDE recomanat:** IntelliJ IDEA, Eclipse o Visual Studio Code (amb l'extensió *Extension Pack for Java*).
