package com.university;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

public class AgregadorEstudiantesTest {

    @Test
    public void pruebaAgregarEstudiantesPorCurso() {
        LectorCSV lector = new LectorCSV();
        List<Estudiante> estudiantes = lector.leerEstudiantes("src/main/resources/input.csv");

        AgregadorEstudiantes agregador = new AgregadorEstudiantes();
        Map<String, Integer> conteoEstudiantes = agregador.agregarEstudiantesPorCurso(estudiantes);


        assertEquals(1, conteoEstudiantes.get("Olivia Red"));
    }
}
