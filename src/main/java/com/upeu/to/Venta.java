package com.upeu.to;

public class Venta {
    private Producto producto;
    private double monto;
    private Tarjeta tarjeta;


    public Venta(Producto producto,  double monto, Tarjeta tarjeta) {
        this.producto = producto;
        this.monto = monto;
        this.tarjeta = tarjeta;
    }

}
