package cat.uvic.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComandaServiceTest {

    private StockRepository stockRepository;
    private ComandaService service;

    @BeforeEach
    void setUp() {
        stockRepository = mock(StockRepository.class);
        service = new ComandaService(stockRepository);
    }

    @Test
    void potComprar_retornaTrue_quanHiHaStock() {
        when(stockRepository.teStock("llapis")).thenReturn(true);

        assertTrue(service.potComprar("llapis"));
    }

    @Test
    void potComprar_retornaFalse_quanNoHiHaStock() {
        when(stockRepository.teStock("llapis")).thenReturn(false);

        assertFalse(service.potComprar("llapis"));
    }

    @Test
    void potComprar_llencaExcepcio_quanProducteEsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.potComprar(null));
        verifyNoInteractions(stockRepository);
    }

    @Test
    void potComprar_llencaExcepcio_quanProducteEsBuit() {
        assertThrows(IllegalArgumentException.class,
                () -> service.potComprar("   "));
        verifyNoInteractions(stockRepository);
    }

    @Test
    void potComprar_missatgeDeLExcepcio() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.potComprar(""));
        assertEquals("Producte invàlid", ex.getMessage());
    }
}