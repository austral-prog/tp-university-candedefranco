package com.university;

import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        LectorCSV lector = new LectorCSV();
        List<Estudiante> estudiantes = lector.leerEstudiantes("src/main/resources/students.csv");

        AgregadorEstudiantes agregador = new AgregadorEstudiantes();
        Map<String, Integer> conteoEstudiantes = agregador.agregarEstudiantesPorCurso(estudiantes);

        EscritorCSV escritor = new EscritorCSV();
        escritor.escribirResultados("src/main/resources/resultado.csv", conteoEstudiantes);
    }
}

