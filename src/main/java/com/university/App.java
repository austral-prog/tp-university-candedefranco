package com.university;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        LectorCSV lectorCSV = new LectorCSV();
        EscritorCSV escritorCSV = new EscritorCSV();
        try {
            // Leer y procesar estudiantes
            List<Estudiante> estudiantes = lectorCSV.leerEstudiantes("src/main/resources/students.csv");
            AgregadorEstudiantes agregador = new AgregadorEstudiantes();
            Map<String, Integer> conteoEstudiantes = agregador.agregarEstudiantesPorCurso(estudiantes);
            escritorCSV.escribirResultados("src/main/resources/solution.csv", conteoEstudiantes);
            System.out.println("Conteo de estudiantes por curso: " + conteoEstudiantes);

            // Leer y procesar evaluaciones de "TP Final" y otras en input_2.csv
            List<Evaluacion> evaluacionesTPFinal = lectorCSV.leerEvaluaciones("src/main/resources/input_2.csv");
            escritorCSV.escribirEvaluacionesTPFinal("src/main/resources/solution2.csv", evaluacionesTPFinal);
            System.out.println("Evaluaciones 'TP Final': " + evaluacionesTPFinal);

            // Procesar y escribir evaluaciones según consigna tres en input_3.csv
            Map<String, Double> resultadosConsignaTres = lectorCSV.procesarEvaluaciones("src/main/resources/input_3.csv");
            escritorCSV.escribirArchivoAprobados("src/main/resources/solution3.csv", evaluacionesTPFinal);
            System.out.println("Resultados de las evaluaciones para la consigna tres: " + resultadosConsignaTres);

        } catch (Exception e) {
            System.err.println("Error en la ejecución: " + e.getMessage());
            e.printStackTrace();
        }
    }
}




