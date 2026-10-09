package com.empresa.flota.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Gestiona la configuración global de la empresa
 * mediante un fichero binario de datos primitivos.
 */
public class GestorConfiguracion {

    /**
     * Guarda la configuración de la empresa en un fichero binario.
     *
     * @param ruta ruta del fichero donde se guardará la configuración
     * @param nombreEmpresa nombre de la empresa
     * @param version versión del software
     * @param totalVehiculos número total de vehículos activos
     * @param kilometraje kilometraje acumulado de la flota
     */
    public static void guardarConfiguracion(
            String ruta,
            String nombreEmpresa,
            String version,
            int totalVehiculos,
            double kilometraje) {

        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(ruta))) {

            dos.writeUTF(nombreEmpresa);
            dos.writeUTF(version);
            dos.writeInt(totalVehiculos);
            dos.writeDouble(kilometraje);

            System.out.println("Configuración guardada correctamente.");

        } catch (IOException e) {
            System.out.println(
                    "Error al guardar la configuración: "
                    + e.getMessage());
        }
    }

    /**
     * Lee la configuración almacenada en un fichero binario.
     *
     * @param ruta ruta del fichero que contiene la configuración
     */
    public static void leerConfiguracion(String ruta) {

        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(ruta))) {

            try {

                while (true) {

                    String nombreEmpresa = dis.readUTF();
                    String version = dis.readUTF();
                    int totalVehiculos = dis.readInt();
                    double kilometraje = dis.readDouble();

                    System.out.println("----- CONFIGURACIÓN -----");
                    System.out.println("Empresa: " + nombreEmpresa);
                    System.out.println("Versión: " + version);
                    System.out.println(
                            "Vehículos activos: " + totalVehiculos);
                    System.out.println(
                            "Kilometraje acumulado: " + kilometraje);
                }

            } catch (EOFException e) {

                System.out.println(
                        "Fin de la lectura del fichero.");
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al leer la configuración: "
                    + e.getMessage());
        }
    }
}
