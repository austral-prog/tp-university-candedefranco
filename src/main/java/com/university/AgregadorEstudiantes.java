package com.university;

import java.util.*;

public class AgregadorEstudiantes {

    public Map<String, Integer> agregarEstudiantesPorCurso(List<Estudiante> estudiantes) {
        Map<String, Set<String>> conteoCursosEstudiantes = new HashMap<>();

        for (Estudiante estudiante : estudiantes) {
            String nombre = estudiante.getNombre();
            String curso= estudiante.getCursos();
            conteoCursosEstudiantes.putIfAbsent(nombre, new HashSet<>());
            conteoCursosEstudiantes.get(nombre).add(curso);
        }

        Map<String, Integer> contarCursos = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : conteoCursosEstudiantes.entrySet()) {
            contarCursos.put(entry.getKey(), entry.getValue().size());
        }

        return contarCursos;
    }
}
