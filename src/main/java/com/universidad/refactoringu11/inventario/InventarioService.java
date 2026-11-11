
package com.universidad.refactoringu11.inventario;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventarioService {

    private final List<Producto> productos = new ArrayList<>();
    private final List<String> movimientos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public String actualizarInventario(
            Long productoId,
            int cantidad,
            String tipoMovimiento
    ) {

        // 1. Validacion de entrada
        if (productoId == null || cantidad == 0
                || (!tipoMovimiento.equals("ENTRADA")
                && !tipoMovimiento.equals("SALIDA"))) {

            return "ERROR_DATOS_INVALIDOS";
        }

        // 2. Busqueda del producto
        Producto producto = null;

        for (Producto p : productos) {
            if (p.getId().equals(productoId)) {
                producto = p;
                break;
            }
        }

        if (producto == null) {
            return "ERROR_PRODUCTO_NO_EXISTE";
        }

        // 3. Actualizacion del stock
        int nuevoStock;

        if (tipoMovimiento.equals("ENTRADA")) {
            nuevoStock = producto.getStock() + cantidad;
        } else {
            nuevoStock = producto.getStock() - cantidad;

            if (nuevoStock < 0) {
                return "ERROR_STOCK_INSUFICIENTE";
            }
        }

        producto.setStock(nuevoStock);

        // 4. Calculo de reorden
        boolean necesitaReorden =
                nuevoStock < producto.getStockMinimo();

        int cantidadReorden = necesitaReorden
                ? (producto.getStockMinimo() * 3) - nuevoStock
                : 0;

        // 5. Registro y alerta
        String registro = tipoMovimiento
                + " de " + cantidad
                + " unidades de " + producto.getNombre()
                + " -> stock final: " + nuevoStock;

        movimientos.add(registro);
        System.out.println(registro);

        if (necesitaReorden) {

            System.out.println(
                    "ALERTA: " + producto.getNombre()
                            + " bajo stock minimo. Reordenar "
                            + cantidadReorden + " unidades."
            );

            return "OK_BAJO_STOCK_REORDEN_" + cantidadReorden;
        }

        return "OK_" + nuevoStock;
    }
}
