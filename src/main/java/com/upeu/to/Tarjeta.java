package com.upeu.to;

import java.util.UUID;

public class Tarjeta {

    private String nombre;
    private String numero;
    private Double saldo;

    // Constructor
    public Tarjeta(String numero, String nombre, Double saldo) {
        this.numero = numero;
        this.nombre = nombre;
        this.saldo = saldo;

    }

    //COnstrustor de objetos
    public Tarjeta() {
        numero = UUID.randomUUID().toString(); // numero random
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {

        this.nombre = nombre;
    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String toString() {
        return "Tarjeta{" +
                "nombre='" + nombre + '\'' +
                ", numero='" + numero + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
