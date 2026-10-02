package cat.uvic.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTest {

    private Calculator calculadora;

    @BeforeEach
    void setup(){
        calculadora = new Calculator();
    }

    // Suma

    @ParameterizedTest(name = "suma({0}, {1}) = {2}")
    @CsvSource({
            "1, 2, 3",
            "0, 0, 0",
            "-4, -2, -6",
            "-4, 6, 2",
            "4, -6, -2"
    })
    void sumaDonaElResultatEsperat(int a, int b, int esperat) {
        assertEquals(esperat, calculadora.suma(a, b));
    }

    // Resta

    @Test
    void restaDosNumeros() {
        double resultat = calculadora.resta(2, 2);
        assertEquals(0, resultat);
    }

    @Test
    void restaDosNumerosNegatius() {
        double resultat = calculadora.resta(-2, -2);
        assertEquals(0, resultat);
    }

    @Test
    void restaPositiuINegatiu() {
        double resultat = calculadora.resta(2, -2);
        assertEquals(4, resultat);
    }

    @Test
    void restaNegatiuIPositiu() {
        double resultat = calculadora.resta(-2, 2);
        assertEquals(-4, resultat);
    }

    // Multiplicació

    @Test
    void multiplicaPerZeroDonaZero() {
        double resultat = calculadora.multiplica(5, 0);
        assertEquals(0, resultat);
    }

    @Test
    void multiplicaDosNegatiusDonaPositiu() {
        double resultat = calculadora.multiplica(-2, -3);
        assertEquals(6, resultat);
    }

    // Divisió

    @Test
    void divideixDosNumerosPositius() {
        double resultat = calculadora.divideix(5, 2);
        assertEquals(2.5, resultat);
    }

    @Test
    void divideixZeroEntreNumeroDonaZero() {
        double resultat = calculadora.divideix(0, 5);
        assertEquals(0, resultat);
    }

    @Test
    void divideixNegatiuEntrePositiuDonaNegatiu() {
        double resultat = calculadora.divideix(-5, 2);
        assertEquals(-2.5, resultat);
    }

    @Test
    void divideixEntreZeroLlancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> calculadora.divideix(5, 0));
    }

    // Potència

    @Test
    void potenciaAmbExponentZeroDonaUn() {
        double resultat = calculadora.potencia(5, 0);
        assertEquals(1, resultat);
    }

    @Test
    void potenciaDeZeroAmbExponentPositiuDonaZero() {
        double resultat = calculadora.potencia(0, 3);
        assertEquals(0, resultat);
    }

    @Test
    void potenciaDeBaseNegativaAmbExponentSenarDonaNegatiu() {
        double resultat = calculadora.potencia(-2, 3);
        assertEquals(-8, resultat);
    }

    @Test
    void potenciaAmbExponentNegatiuLlancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> calculadora.potencia(2, -1));
    }

}
