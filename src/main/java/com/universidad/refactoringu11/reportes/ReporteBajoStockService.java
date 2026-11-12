
package com.universidad.refactoringu11.reportes;

import com.universidad.refactoringu11.inventario.InventarioService;
import com.universidad.refactoringu11.inventario.NotificadorBajoStock;
import com.universidad.refactoringu11.inventario.Producto;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteBajoStockService
        implements NotificadorBajoStock {

    private final InventarioService inventarioService;

    private final List<String> notificaciones = new ArrayList<>();

    public ReporteBajoStockService(
            @Lazy InventarioService inventarioService
    ) {

        this.inventarioService = inventarioService;
    }

    @Override
    public void notificar(
            Producto producto,
            int cantidadReorden
    ) {

        String alerta = producto.getNombre()
                + " | Stock actual: " + producto.getStock()
                + " | Cantidad a reordenar: " + cantidadReorden;

        notificaciones.add(alerta);
    }

    public String generarReporte() {

        StringBuilder reporte = new StringBuilder();

        reporte.append("REPORTE DE BAJO STOCK\n");

        List<Producto> productosBajoStock =
                inventarioService.obtenerProductosBajoStock();

        if (productosBajoStock.isEmpty()) {

            reporte.append("No hay productos bajo stock minimo.\n");

        } else {

            for (Producto producto : productosBajoStock) {

                reporte.append("Producto: ")
                        .append(producto.getNombre())
                        .append(" | Stock: ")
                        .append(producto.getStock())
                        .append(" | Stock minimo: ")
                        .append(producto.getStockMinimo())
                        .append("\n");
            }
        }

        reporte.append("\nNOTIFICACIONES REGISTRADAS\n");

        if (notificaciones.isEmpty()) {

            reporte.append("No existen notificaciones registradas.\n");

        } else {

            for (String notificacion : notificaciones) {

                reporte.append(notificacion).append("\n");
            }
        }

        return reporte.toString();
    }
}
