package com.upeu.controller;

import com.upeu.to.Producto;
import com.upeu.to.Tarjeta;
import com.upeu.utils.Colors;
import com.upeu.utils.TablaDinamica;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TarjetaController {
    TablaDinamica tablaDinamica;

    public Tarjeta crearTarjeta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Colors.ANSI_GREEN + "Creando Tarjeta" + Colors.ANSI_RESET);

        Tarjeta tarjeta = new Tarjeta();
        System.out.print(Colors.ANSI_BLUE + "Ingrese el nombre: " + Colors.ANSI_RESET);
        tarjeta.setNombre(scanner.nextLine());
        System.out.print(Colors.ANSI_BLUE + "Ingrese el monto inicial: " + Colors.ANSI_RESET);
        tarjeta.setSaldo(scanner.nextDouble());
        System.out.println(Colors.ANSI_GREEN + "Tarjeta creada" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + tarjeta.toString() + Colors.ANSI_RESET);

        return tarjeta;

    }

    public void imprimirTarjeta(List<Tarjeta> tarjetas) throws IllegalAccessException {
        tablaDinamica = new TablaDinamica();
        tablaDinamica.imprimirTabla(tarjetas);
    }

    public List<Tarjeta> eliminarTarjetaPorNumero(String numero, List<Tarjeta> tarjetaList) throws IllegalAccessException {
        Iterator<Tarjeta> iterator = tarjetaList.iterator();
        while (iterator.hasNext()) {
            Tarjeta tarjeta = iterator.next();
            if (tarjeta.getNumero().equals(numero)) {
                iterator.remove();
                break; // Salir del bucle una vez encontrada y eliminada la tarjeta
            }
        }

        return tarjetaList;
    }

    public Tarjeta buscarTarjetaPorNumero(List<Tarjeta> tarjetas, String numero) {
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta.getNumero().equals(numero)) {
                return tarjeta;
            }
        }
        return null; // Retorna null si no se encuentra el producto
    }

    public List<Tarjeta> actualizarSaldo(List<Tarjeta> tarjetas, Tarjeta tarjetaSeleccionada) {

        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta.getNumero().equals(tarjetaSeleccionada.getNumero())) {
                tarjeta.setSaldo(tarjetaSeleccionada.getSaldo());
            }
        }
        return tarjetas; // Retorna null si no se encuentra el producto
    }

}
