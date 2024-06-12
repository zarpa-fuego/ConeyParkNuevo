package com.upeu.controller;

import com.upeu.to.Venta;
import com.upeu.utils.TablaDinamica;

import java.util.List;

public class VentaController {


    public void imprimirVentas(List<Venta> ventas) throws IllegalAccessException {
        TablaDinamica tablaDinamica = new TablaDinamica();
        tablaDinamica.imprimirTabla(ventas);
    }

}
