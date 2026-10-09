package com.empresa.flota.dao;

import com.empresa.flota.model.Vehiculo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Gestiona las copias de seguridad de la flota
 * mediante serialización nativa de Java.
 */
public class GestorBackup {

    /**
     * Guarda la lista de vehículos en un archivo binario.
     *
     * @param vehiculos lista de vehículos que se quiere guardar
     * @param ruta ruta del archivo de backup
     */
    public static void guardarBackup(ArrayList<Vehiculo> vehiculos, String ruta) {

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ruta))) {

            oos.writeObject(vehiculos);

            System.out.println("Backup guardado correctamente en: " + ruta);

        } catch (IOException e) {
            System.out.println("Error al guardar el backup: " + e.getMessage());
        }
    }

    /**
     * Carga una lista de vehículos desde un archivo binario.
     *
     * @param ruta ruta del archivo de backup
     * @return lista de vehículos recuperada
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Vehiculo> cargarBackup(String ruta) {

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ruta))) {

            ArrayList<Vehiculo> vehiculos =
                    (ArrayList<Vehiculo>) ois.readObject();

            System.out.println("Backup cargado correctamente desde: " + ruta);

            return vehiculos;

        } catch (ClassNotFoundException e) {

            System.out.println("Error: no se encuentra la clase Vehiculo.");
            return new ArrayList<>();

        } catch (IOException e) {

            System.out.println("Error al cargar el backup: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
