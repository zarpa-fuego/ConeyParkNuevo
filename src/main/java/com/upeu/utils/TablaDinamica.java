package com.upeu.utils;

import java.lang.reflect.Field;
import java.util.List;

public class TablaDinamica {

    public void imprimirTabla(List<?> objetos) throws IllegalAccessException {
        if (objetos.isEmpty()) {
            System.out.println("No hay datos para mostrar.");
            return;
        }

        // Obtener la clase de los objetos
        Class<?> clazz = objetos.get(0).getClass();

        // Obtener los nombres de los campos
        Field[] fields = clazz.getDeclaredFields();

        // Calcular el ancho máximo de cada columna
        int[] columnWidths = new int[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true); // Acceso a los campos privados
            columnWidths[i] = getVisibleLength(fields[i].getName());
        }

        for (Object obj : objetos) {
            for (int i = 0; i < fields.length; i++) {
                String value = String.valueOf(fields[i].get(obj));
                columnWidths[i] = Math.max(columnWidths[i], getVisibleLength(value));
            }
        }

        // Imprimir la fila de encabezados
        for (int i = 0; i < fields.length; i++) {
            System.out.print(padRight(Colors.ANSI_BLUE + fields[i].getName() + Colors.ANSI_RESET, columnWidths[i] + 2));
        }
        System.out.println();

        // Imprimir una línea separadora
        for (int i = 0; i < fields.length; i++) {
            System.out.print(padRight("", columnWidths[i], '-') + "  ");
        }
        System.out.println();

        // Imprimir las filas de datos
        for (Object obj : objetos) {
            for (int i = 0; i < fields.length; i++) {
                String value = String.valueOf(fields[i].get(obj));
                System.out.print(padRight(value, columnWidths[i] + 2));
            }
            System.out.println();
        }
    }

    private String padRight(String text, int length) {
        int visibleLength = getVisibleLength(text);
        int paddingLength = length - visibleLength;
        StringBuilder sb = new StringBuilder(text);
        while (paddingLength > 0) {
            sb.append(' ');
            paddingLength--;
        }
        return sb.toString();
    }

    private String padRight(String text, int length, char padChar) {
        int visibleLength = getVisibleLength(text);
        int paddingLength = length - visibleLength;
        StringBuilder sb = new StringBuilder(text);
        while (paddingLength > 0) {
            sb.append(padChar);
            paddingLength--;
        }
        return sb.toString();
    }

    private int getVisibleLength(String text) {
        return text.replaceAll("\u001B\\[[;\\d]*m", "").length();
    }
}
