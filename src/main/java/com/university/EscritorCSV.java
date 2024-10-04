package com.university;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class EscritorCSV {

    public void escribirResultados(String archivoRuta, Map<String, Integer> conteoEstudiantes) {
        try (FileWriter escritor = new FileWriter(archivoRuta)) {
            escritor.append("Nombre_Estudiante,Cantidad_Cursos\n");

            for (Map.Entry<String, Integer> entrada : conteoEstudiantes.entrySet()) {
                escritor.append(entrada.getKey()).append(",").append(String.valueOf(entrada.getValue())).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV");
        }
    }
}

