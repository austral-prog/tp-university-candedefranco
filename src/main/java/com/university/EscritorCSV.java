package com.university;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EscritorCSV {

    public void escribirResultados(String archivoRuta, Map<String, Integer> conteoEstudiantes) {
        // Obtener una lista de los nombres de los estudiantes
        List<String> nombresEstudiantes = new ArrayList<>(conteoEstudiantes.keySet());

        // Ordenar los nombres alfabéticamente
        Collections.sort(nombresEstudiantes);

        try (FileWriter escritor = new FileWriter(archivoRuta)) {
            escritor.append("Nombre_Estudiante,Cantidad_Cursos\n");

            // Escribir las entradas ordenadas alfabéticamente en el CSV
            for (String nombre : nombresEstudiantes) {
                Integer cantidadCursos = conteoEstudiantes.get(nombre);
                escritor.append(nombre).append(",").append(String.valueOf(cantidadCursos)).append("\n"); //cambiar
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV");
        }
    }

    public void escribirEvaluacionesTPFinal(String archivoRuta, List<Evaluacion> evaluaciones) {
        try (FileWriter escritor = new FileWriter(archivoRuta)) {
            escritor.append("Subject_Name,Evaluation_Name,Student_Name,Grade\n");

            evaluaciones.sort(Comparator.comparing(Evaluacion::getSubject).thenComparing(Evaluacion::getEvaluationName).thenComparing(Evaluacion::getStudentName));


            for (Evaluacion evaluacion : evaluaciones) {
                escritor.append(evaluacion.getSubject()).append(",")
                        .append(evaluacion.getEvaluationName()).append(",")
                        .append(evaluacion.getStudentName()).append(",")
                        .append(String.valueOf(evaluacion.getGrade())).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV");
        }

    }

    public void escribirEvaluacionesConsignaTres(String filePath, Map<String, Double> resultados) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Evaluation_Name,Final_Grade\n");  // Encabezado del archivo

            for (Map.Entry<String, Double> entry : resultados.entrySet()) {
                String evaluationName = entry.getKey();
                Double finalGrade = entry.getValue();

                writer.write(evaluationName + "," + finalGrade + "\n");  // Escribir cada línea en el archivo
            }

            System.out.println("Archivo de resultados generado exitosamente en: " + filePath);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo CSV: " + e.getMessage());
        }
    }

    public void escribirArchivoAprobados(String archivoRuta, List<Evaluacion> evaluaciones) {
        try (FileWriter escritor = new FileWriter(archivoRuta)) {
            escritor.write("Subject,Evaluation Name,Student,Grade,Result\n");

            for (Evaluacion evaluacion : evaluaciones) {
                String resultado = evaluacion.getGrade() >= 4.0 ? "Aprobado" : "Desaprobado";
                escritor.write(evaluacion.getSubject() + "," +
                        evaluacion.getEvaluationName() + "," +
                        evaluacion.getStudentName() + "," +
                        evaluacion.getGrade() + "," +
                        resultado + "\n");
            }

            System.out.println("Archivo de aprobados generado exitosamente en: " + archivoRuta);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo CSV de aprobados: " + e.getMessage());
        }
    }


}





