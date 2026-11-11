
package com.universidad.refactoringu11.inventario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventarioServiceTest {

    private InventarioService service;
    private Producto libro;

    @BeforeEach
    void setUp() {

        service = new InventarioService();

        libro = new Producto(
                1L,
                "Clean Code",
                TipoProducto.LIBRO,
                45.0,
                20,
                5
        );

        service.agregarProducto(libro);
    }

    @Test
    void entrada_incrementaStockYRetornaOk() {

        assertEquals(
                "OK_30",
                service.actualizarInventario(
                        1L, 10, "ENTRADA"
                )
        );

        assertEquals(30, libro.getStock());
    }

    @Test
    void salida_conStockSuficiente_descuentaYRetornaOk() {

        assertEquals(
                "OK_12",
                service.actualizarInventario(
                        1L, 8, "SALIDA"
                )
        );

        assertEquals(12, libro.getStock());
    }

    @Test
    void salida_conStockInsuficiente_retornaErrorYNoModificaStock() {

        assertEquals(
                "ERROR_STOCK_INSUFICIENTE",
                service.actualizarInventario(
                        1L, 50, "SALIDA"
                )
        );

        assertEquals(20, libro.getStock());
    }

    @Test
    void salida_dejaStockBajoElMinimo_activaReordenConCantidadCalculada() {

        assertEquals(
                "OK_BAJO_STOCK_REORDEN_12",
                service.actualizarInventario(
                        1L, 17, "SALIDA"
                )
        );

        assertEquals(3, libro.getStock());
    }

    @Test
    void tipoMovimientoInvalido_retornaError() {

        assertEquals(
                "ERROR_DATOS_INVALIDOS",
                service.actualizarInventario(
                        1L, 5, "TRANSFERENCIA"
                )
        );
    }

    @Test
    void productoInexistente_retornaError() {

        assertEquals(
                "ERROR_PRODUCTO_NO_EXISTE",
                service.actualizarInventario(
                        99L, 5, "ENTRADA"
                )
        );
    }
}
