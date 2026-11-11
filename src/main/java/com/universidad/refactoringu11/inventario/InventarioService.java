
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

        if (!datosValidos(productoId, cantidad, tipoMovimiento)) {
            return "ERROR_DATOS_INVALIDOS";
        }

        Producto producto = buscarProducto(productoId);

        if (producto == null) {
            return "ERROR_PRODUCTO_NO_EXISTE";
        }

        Integer nuevoStock = calcularNuevoStock(
                producto, cantidad, tipoMovimiento
        );

        if (nuevoStock == null) {
            return "ERROR_STOCK_INSUFICIENTE";
        }

        producto.setStock(nuevoStock);

        return finalizarActualizacion(
                producto, cantidad, tipoMovimiento, nuevoStock
        );
    }

    private boolean datosValidos(
            Long productoId,
            int cantidad,
            String tipoMovimiento
    ) {

        return productoId != null
                && cantidad != 0
                && (tipoMovimiento.equals("ENTRADA")
                || tipoMovimiento.equals("SALIDA"));
    }

    private Producto buscarProducto(Long productoId) {

        for (Producto producto : productos) {

            if (producto.getId().equals(productoId)) {
                return producto;
            }
        }

        return null;
    }

    private Integer calcularNuevoStock(
            Producto producto,
            int cantidad,
            String tipoMovimiento
    ) {

        int nuevoStock;

        if (tipoMovimiento.equals("ENTRADA")) {
            nuevoStock = producto.getStock() + cantidad;
        } else {
            nuevoStock = producto.getStock() - cantidad;

            if (nuevoStock < 0) {
                return null;
            }
        }

        return nuevoStock;
    }

    private String finalizarActualizacion(
            Producto producto,
            int cantidad,
            String tipoMovimiento,
            int nuevoStock
    ) {

        boolean necesitaReorden =
                nuevoStock < producto.getStockMinimo();

        int cantidadReorden = calcularCantidadReorden(
                producto, nuevoStock, necesitaReorden
        );

        registrarMovimiento(
                producto, cantidad, tipoMovimiento, nuevoStock
        );

        if (necesitaReorden) {

            emitirAlerta(producto, cantidadReorden);

            return "OK_BAJO_STOCK_REORDEN_" + cantidadReorden;
        }

        return "OK_" + nuevoStock;
    }

    private int calcularCantidadReorden(
            Producto producto,
            int nuevoStock,
            boolean necesitaReorden
    ) {

        if (!necesitaReorden) {
            return 0;
        }

        return (producto.getStockMinimo() * 3) - nuevoStock;
    }

    private void registrarMovimiento(
            Producto producto,
            int cantidad,
            String tipoMovimiento,
            int nuevoStock
    ) {

        String registro = tipoMovimiento
                + " de " + cantidad
                + " unidades de " + producto.getNombre()
                + " -> stock final: " + nuevoStock;

        movimientos.add(registro);

        System.out.println(registro);
    }

    private void emitirAlerta(
            Producto producto,
            int cantidadReorden
    ) {

        System.out.println(
                "ALERTA: " + producto.getNombre()
                        + " bajo stock minimo. Reordenar "
                        + cantidadReorden + " unidades."
        );
    }
}
