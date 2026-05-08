package utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventarioAlmacenTest {

    @Test
    void calcularTotalInventario() {
        InventarioAlmacen inventario = new InventarioAlmacen();
        assertEquals(4,inventario.calcularAjusteCategoria("premium",20));
        assertEquals(-1,inventario.calcularAjusteCategoria("basica",20));
        assertEquals(0,inventario.calcularAjusteCategoria("",20));
    }


}