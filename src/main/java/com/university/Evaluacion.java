package com.university;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Evaluacion {
    private final String subject;
    private final String evaluationType; // Nuevo atributo
    private final String evaluationName;
    private final String studentName;
    private final double grade;

    public Evaluacion(String subject, String evaluationType, String evaluationName, String studentName, double grade) {
        this.subject = subject;
        this.evaluationType = evaluationType;
        this.evaluationName = evaluationName;
        this.studentName = studentName;
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public String getEvaluationType() { // Nuevo método getter
        return evaluationType;
    }

    public String getEvaluationName() {
        return evaluationName;
    }

    public String getStudentName() {
        return studentName;
    }

    public double getGrade() {
        return grade;
    }



    public Map<String, Map<String, Boolean>> evaluarEstudiantes(List<Evaluacion> evaluaciones, List<CriterioEvaluacion> criterios) {
        Map<String, Map<String, Boolean>> resultados = new HashMap<>();

        for (CriterioEvaluacion criterio : criterios) {
            String materia = criterio.getSubject();
            List<String> evaluacionesRequeridas = criterio.getEvaluationNames();

            // Filtrar las evaluaciones de los estudiantes para la materia y evaluaciones especificadas
            Map<String, List<Evaluacion>> evaluacionesPorEstudiante = evaluaciones.stream()
                    .filter(e -> e.getSubject().equals(materia) && evaluacionesRequeridas.contains(e.getEvaluationName()))
                    .collect(Collectors.groupingBy(Evaluacion::getStudentName));

            for (Map.Entry<String, List<Evaluacion>> entry : evaluacionesPorEstudiante.entrySet()) {
                String estudiante = entry.getKey();
                List<Evaluacion> evaluacionesEstudiante = entry.getValue();
                boolean aprobado = verificarAprobacion(evaluacionesEstudiante, criterio);

                resultados.computeIfAbsent(estudiante, k -> new HashMap<>()).put(materia, aprobado);
            }
        }

        return resultados;
    }

    // Verifica la aprobación de acuerdo al tipo de criterio
    private boolean verificarAprobacion(List<Evaluacion> evaluaciones, CriterioEvaluacion criterio) {
        switch (criterio.getCriteriaType()) {
            case "AVERAGE_ABOVE_VALUE":
                double promedio = evaluaciones.stream().mapToDouble(Evaluacion::getGrade).average().orElse(0.0);
                return promedio > criterio.getCriteriaValue();
            case "MAX_ABOVE_VALUE":
                double max = evaluaciones.stream().mapToDouble(Evaluacion::getGrade).max().orElse(0.0);
                return max > criterio.getCriteriaValue();
            case "MIN_ABOVE_VALUE":
                double min = evaluaciones.stream().mapToDouble(Evaluacion::getGrade).min().orElse(0.0);
                return min > criterio.getCriteriaValue();
            default:
                return false;
        }
    }

}



}
