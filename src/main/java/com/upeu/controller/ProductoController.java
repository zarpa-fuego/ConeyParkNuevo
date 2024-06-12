package com.upeu.controller;

import com.upeu.to.Producto;
import com.upeu.utils.TablaDinamica;

import java.util.List;

public class ProductoController {


    public Producto buscarProductoPorId(List<Producto> productos, int idProducto) {

        for (Producto producto : productos) {
            if (producto.getIdProducto() == idProducto) {
                return producto;
            }
        }
        return null; // Retorna null si no se encuentra el producto
    }

    public void imprimirProductos(List<Producto> productos) throws IllegalAccessException {
        TablaDinamica tablaDinamica = new TablaDinamica();
        tablaDinamica.imprimirTabla(productos);
    }
}
