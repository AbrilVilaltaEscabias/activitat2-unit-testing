package cat.uvic.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComandaServiceTest {

    @Mock
    StockRepository stock;

    @InjectMocks
    ComandaService service;

    @Test
    void potComprarQuanHiHaStock() {
        when(stock.teStock("P01")).thenReturn(true);

        assertTrue(service.potComprar("P01"));
    }

    @Test
    void noPotComprarQuanNoHiHaStock() {
        when(stock.teStock("P02")).thenReturn(false);

        assertFalse(service.potComprar("P02"));
    }

    @Test
    void consultaElStockDelProducteIndicat() {
        when(stock.teStock("P03")).thenReturn(true);

        service.potComprar("P03");

        verify(stock).teStock("P03");
    }

    @Test
    void consultaElRepositoriNomesUnaVegada() {
        when(stock.teStock("P04")).thenReturn(true);

        service.potComprar("P04");

        verify(stock, times(1)).teStock("P04");
        verifyNoMoreInteractions(stock);
    }

    @Test
    void cadaProducteEsConsultaIndependentment() {
        when(stock.teStock("P01")).thenReturn(true);
        when(stock.teStock("P02")).thenReturn(false);

        assertTrue(service.potComprar("P01"));
        assertFalse(service.potComprar("P02"));
    }

    @Test
    void producteSenseConfigurarRetornaFalsePerDefecte() {
        // Mockito retorna false per defecte als mètodes boolean no configurats.
        // Amb MockitoExtension en mode estricte no cal cap stubbing aquí.
        assertFalse(service.potComprar("DESCONEGUT"));
        verify(stock).teStock("DESCONEGUT");
    }
}