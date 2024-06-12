package com.upeu;

import com.upeu.controller.ProductoController;
import com.upeu.controller.TarjetaController;
import com.upeu.controller.VentaController;
import com.upeu.to.Producto;
import com.upeu.to.Tarjeta;
import com.upeu.to.Venta;
import com.upeu.utils.Colors;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    List<Producto> productos;


    public static void main(String[] args) throws IllegalAccessException, IOException {

        Main self = new Main();
        Scanner scanner = new Scanner(System.in);

        self.initData();
        List<Tarjeta> tarjetaList = new ArrayList<>();
        List<Venta> ventaList = new ArrayList<>();
        Tarjeta tarjeta = new Tarjeta("123", "Rodrigo", 100.0); // probar
        tarjetaList.add(tarjeta);


        imprimirLogo();
        //instance.categorias.get(0).printProducts();
        int opc = 0;
        do {

            try {
                printMainMenu();

                opc = scanner.nextInt();

                switch (opc) {
                    case 1: {
                        do {
                            try {
                                printMenuTarjeta();
                                opc = scanner.nextInt();
                                TarjetaController tarjetaController = new TarjetaController();
                                switch (opc) {
                                    case 0: {
                                        System.out.println(Colors.ANSI_YELLOW + "Regresando al menu principal" + Colors.ANSI_RESET);
                                    }
                                    break;
                                    case 1: {
                                        Tarjeta tarjeta2 = tarjetaController.crearTarjeta();

                                        tarjetaList.add(tarjeta2);
                                    }
                                    break;
                                    case 2: {
                                        tarjetaController.imprimirTarjeta(tarjetaList);
                                    }
                                    break;
                                    case 3: {

                                        System.out.println(Colors.ANSI_GREEN + "Eliminar Tarjeta" + Colors.ANSI_RESET);
                                        System.out.print(Colors.ANSI_GREEN + "Ingrese número de tarjeta" + Colors.ANSI_RESET);
                                        String numero = scanner.next();
                                        tarjetaController.eliminarTarjetaPorNumero(numero, tarjetaList);
                                        tarjetaController.imprimirTarjeta(tarjetaList);

                                    }
                                    break;
                                    default:
                                        System.out.println(Colors.ANSI_RED + "Opción Invalida" + Colors.ANSI_RESET);

                                }

                            } catch (Exception e) {
                                System.out.println(Colors.ANSI_RED + e.getMessage() + Colors.ANSI_RESET);
                            }

                        } while (opc != 0);

                    }
                    break;
                    case 2: {
                        do {
                            printMenuVenta();
                            ProductoController productoController;
                            TarjetaController tarjetaController;
                            opc = scanner.nextInt();
                            switch (opc) {
                                case 1: {
                                    System.out.printf(Colors.ANSI_GREEN + "Crear Venta" + Colors.ANSI_RESET);
                                    //imprimir los producos
                                    productoController = new ProductoController();
                                    tarjetaController = new TarjetaController();
                                    productoController.imprimirProductos(self.productos);
                                    System.out.printf("Seleccione Producto");
                                    int idProducto = scanner.nextInt();
                                    Producto productoSelecionado = productoController.buscarProductoPorId(self.productos, idProducto);
                                    System.out.println("Prodcuto Selccionado: " + productoSelecionado.getNombre());
                                    System.out.println("Ingrese numero de tarjeta");
                                    String numTarjeta = scanner.next();
                                    Tarjeta tarjetaBusqueda = tarjetaController.buscarTarjetaPorNumero(tarjetaList, numTarjeta);
                                    System.out.println("Tarjeta Seleccionada: " + tarjetaBusqueda.getNombre());
                                    Venta venta = new Venta(productoSelecionado, productoSelecionado.getPrecio(), tarjetaBusqueda);
                                    ventaList.add(venta);
                                    tarjetaBusqueda.setSaldo(tarjetaBusqueda.getSaldo() - productoSelecionado.getPrecio());
                                   // saldo = saldo-gasto
                                    tarjetaList = tarjetaController.actualizarSaldo(tarjetaList, tarjetaBusqueda);


                                }
                                break;
                                case 2: {
                                    VentaController ventaController = new VentaController();
                                    ventaController.imprimirVentas(ventaList);
                                }
                            }
                        } while (opc != 0);
                    }
                    break;
                    default:
                        System.out.println(Colors.ANSI_RED + "Opción Invalida" + Colors.ANSI_RESET);

                }

            } catch (Exception e) {
                scanner.next();// limpiar dato de error
                System.out.println(Colors.ANSI_RED + e.getMessage() + Colors.ANSI_RESET);
            }

        } while (opc != 4);

    }

    public void initData() {
        Producto p1 = new Producto(1, "Carros Chocones", "Juegos", 5.0);
        Producto p2 = new Producto(2, "Motos", "Juegos", 5.0);
        Producto p3 = new Producto(3, "DOOM", "Juegos", 2.0);
        Producto p4 = new Producto(4, "Galletas Chomp", "Comida", 2.0);
        Producto p5 = new Producto(5, "Agua", "Comida", 2.0);

        productos = new ArrayList<>();
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        productos.add(p5);

    }

    public static void printMainMenu() {
        System.out.println(Colors.ANSI_GREEN + "╔══════════════════════════════╗" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "║          Menú Principal      ║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "╠══════════════════════════════╣" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "║    Gestion Tarjeta    1      ║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "║    Gestión Ventas     2      ║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "║    Gestion Producto   3      ║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "║    Salir              4      ║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "╚══════════════════════════════╝" + Colors.ANSI_RESET);
        System.out.print(Colors.ANSI_BLUE + "ingrese opción: " + Colors.ANSI_RESET);

    }

    public static void printMenuVenta() {
        System.out.println(Colors.ANSI_GREEN + "╔══════════════════════════════╗" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "║          Menú Venta          ║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "╠══════════════════════════════╣" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "║    Crear Venta         1     ║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "║    Ver Ventas          2     ║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "║    Volver              0     ║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "╚══════════════════════════════╝" + Colors.ANSI_RESET);
        System.out.print(Colors.ANSI_BLUE + "ingrese opción: " + Colors.ANSI_RESET);

    }

    public static void printMenuTarjeta() {
        System.out.println(Colors.ANSI_GREEN + "╔══════════════════════════════╗" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "║          Menú Tarjeta        ║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "╠══════════════════════════════╣" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "║    Crear Tarjetas    1       ║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "║    Ver Tarjetas      2       ║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "║    Eliminar Tarjeta  3       ║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "║    Volver            0       ║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_GREEN + "╚══════════════════════════════╝" + Colors.ANSI_RESET);
        System.out.print(Colors.ANSI_BLUE + "ingrese opción: " + Colors.ANSI_RESET);

    }

    private static void imprimirLogo() throws IOException {
        String ASCII_CHARS = "@%#*+=-:. ";
        // Cargar el archivo de recursos
        InputStream inputStream = Main.class.getResourceAsStream("/coneypark.jpeg");
        if (inputStream == null) {
            throw new IOException("No se pudo encontrar el archivo coneypark.jpeg");
        }
        BufferedImage originalImage = ImageIO.read(inputStream);

        // Redimensionar la imagen a 800x600
        int newWidth = 200;
        int newHeight = 100;
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();

        // Convertir la imagen redimensionada a ASCII
        StringBuilder asciiArt = new StringBuilder();
        for (int y = 0; y < resizedImage.getHeight(); y++) {
            for (int x = 0; x < resizedImage.getWidth(); x++) {
                Color color = new Color(resizedImage.getRGB(x, y));
                int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                int charIndex = (gray * (ASCII_CHARS.length() - 1)) / 255;
                asciiArt.append(ASCII_CHARS.charAt(charIndex));
            }
            asciiArt.append("\n");
        }
        System.out.println(asciiArt);
    }


}
