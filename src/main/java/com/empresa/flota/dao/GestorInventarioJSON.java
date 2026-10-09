package com.empresa.flota.dao;

import com.empresa.flota.model.Vehiculo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Gestiona la exportación e importación de la flota
 * utilizando el formato JSON y la biblioteca Gson.
 */
public class GestorInventarioJSON {

    /**
     * Exporta la lista de vehículos a un fichero JSON.
     *
     * @param vehiculos lista de vehículos
     * @param ruta ruta del fichero JSON
     */
    public static void exportarJSON(
            ArrayList<Vehiculo> vehiculos,
            String ruta) {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(ruta)) {

            gson.toJson(vehiculos, writer);

            System.out.println(
                    "Inventario exportado correctamente.");

        } catch (IOException e) {

            System.out.println(
                    "Error al exportar JSON: "
                    + e.getMessage());
        }
    }

    /**
     * Importa los vehículos desde un fichero JSON.
     * Los datos se cargan en un ArrayList y en un HashMap.
     *
     * @param ruta ruta del fichero JSON
     * @param lista lista de vehículos
     * @param mapa mapa de vehículos por matrícula
     */
    public static void importarJSON(
            String ruta,
            ArrayList<Vehiculo> lista,
            HashMap<String, Vehiculo> mapa) {

        Gson gson = new Gson();

        try (FileReader reader = new FileReader(ruta)) {

            Type tipoLista =
                    new TypeToken<ArrayList<Vehiculo>>() {
                    }.getType();

            ArrayList<Vehiculo> vehiculos =
                    gson.fromJson(reader, tipoLista);

            lista.clear();
            mapa.clear();

            for (Vehiculo vehiculo : vehiculos) {

                // Guardar en ArrayList
                lista.add(vehiculo);

                // Guardar en HashMap usando la matrícula
                mapa.put(
                        vehiculo.getMatricula(),
                        vehiculo);
            }

            System.out.println(
                    "Inventario importado correctamente.");

        } catch (IOException e) {

            System.out.println(
                    "Error al importar JSON: "
                    + e.getMessage());
        }
    }
}
