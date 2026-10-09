package com.empresa.flota.main;

import com.empresa.flota.dao.GestorBackup;
import com.empresa.flota.dao.GestorConfiguracion;
import com.empresa.flota.dao.GestorInventarioJSON;
import com.empresa.flota.dao.GestorReportesXML;
import com.empresa.flota.model.Vehiculo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        // Rutas de los archivos
        Path carpetaConfig = Paths.get("datos/config");
        Path carpetaJson = Paths.get("datos/json");
        Path carpetaBackup = Paths.get("datos/backup");
        Path carpetaReports = Paths.get("datos/reports");

        try {

            // Crear las carpetas necesarias
            Files.createDirectories(carpetaConfig);
            Files.createDirectories(carpetaJson);
            Files.createDirectories(carpetaBackup);
            Files.createDirectories(carpetaReports);

            System.out.println("Directorios creados correctamente.");

        } catch (IOException e) {

            System.out.println(
                    "Error al crear los directorios: "
                    + e.getMessage());

            return;
        }

        // ARRAYLIST
        
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();

        Vehiculo v1 = new Vehiculo(
                "1234ABC",
                "Toyota",
                "Corolla",
                2020,
                85000,
                "Preventivo"
        );

        Vehiculo v2 = new Vehiculo(
                "5678DEF",
                "Ford",
                "Transit",
                2021,
                62000,
                "Correctivo"
        );

        Vehiculo v3 = new Vehiculo(
                "9012GHI",
                "Renault",
                "Clio",
                2019,
                95000,
                "Preventivo"
        );

        Vehiculo v4 = new Vehiculo(
                "3456JKL",
                "Mercedes",
                "Sprinter",
                2022,
                45000,
                "Revisión"
        );

        vehiculos.add(v1);
        vehiculos.add(v2);
        vehiculos.add(v3);
        vehiculos.add(v4);

        System.out.println("\n--- VEHÍCULOS ---");

        for (Vehiculo vehiculo : vehiculos) {
            System.out.println(vehiculo);
        }
        
        // HASHMAP
        
        HashMap<String, Vehiculo> mapaVehiculos = new HashMap<>();

        for (Vehiculo vehiculo : vehiculos) {
            mapaVehiculos.put(
                    vehiculo.getMatricula(),
                    vehiculo
            );
        }

        System.out.println("\n--- BÚSQUEDA POR MATRÍCULA ---");

        Vehiculo buscado = mapaVehiculos.get("1234ABC");

        if (buscado != null) {
            System.out.println("Vehículo encontrado:");
            System.out.println(buscado);
        }
        
        // HASHSET
        
        HashSet<String> categorias = new HashSet<>();

        for (Vehiculo vehiculo : vehiculos) {
            categorias.add(
                    vehiculo.getCategoriaMantenimiento()
            );
        }

        System.out.println("\n--- CATEGORÍAS ÚNICAS ---");

        for (String categoria : categorias) {
            System.out.println(categoria);
        }
        
        // CONFIGURACIÓN BINARIA

        double kilometrajeTotal = 0;

        for (Vehiculo vehiculo : vehiculos) {
            kilometrajeTotal += vehiculo.getKilometraje();
        }

        String rutaConfiguracion =
                "datos/config/empresa.dat";

        GestorConfiguracion.guardarConfiguracion(
                rutaConfiguracion,
                "Empresa Logística S.L.",
                "1.0",
                vehiculos.size(),
                kilometrajeTotal
        );

        GestorConfiguracion.leerConfiguracion(
                rutaConfiguracion
        );
        
        // JSON
        
        String rutaJSON =
                "datos/json/flota.json";

        GestorInventarioJSON.exportarJSON(
                vehiculos,
                rutaJSON
        );

        ArrayList<Vehiculo> vehiculosJSON =
                new ArrayList<>();

        HashMap<String, Vehiculo> mapaJSON =
                new HashMap<>();

        GestorInventarioJSON.importarJSON(
                rutaJSON,
                vehiculosJSON,
                mapaJSON
        );

        System.out.println(
                "\nVehículos cargados desde JSON: "
                + vehiculosJSON.size()
        );

        // BACKUP
        
        String rutaBackup =
                "datos/backup/flota_backup.ser";

        GestorBackup.guardarBackup(
                vehiculos,
                rutaBackup
        );

        ArrayList<Vehiculo> vehiculosBackup =
                GestorBackup.cargarBackup(
                        rutaBackup
                );

        System.out.println(
                "Vehículos recuperados del backup: "
                + vehiculosBackup.size()
        );
        
        // XML DOM
        
        String rutaXML =
                "datos/reports/informe_flota.xml";

        GestorReportesXML.generarXML(
                vehiculos,
                rutaXML
        );
        
        // XML SAX
        
        Set<String> categoriasXML =
                GestorReportesXML.leerXML(
                        rutaXML
                );

        System.out.println(
                "\n--- CATEGORÍAS EN XML ---"
        );

        for (String categoria : categoriasXML) {
            System.out.println(categoria);
        }
        
        // FIN
        
        System.out.println(
                "\n================================="
        );

        System.out.println(
                "PRÁCTICA FINALIZADA CORRECTAMENTE"
        );

        System.out.println(
                "================================="
        );
    }
}

        
    

      