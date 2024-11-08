package com.university;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LectorCSV {

    public List<Estudiante> leerEstudiantes(String archivoRuta) {
        List<Estudiante> estudiantes = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoRuta))) {
            String linea;
            lector.readLine();

            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    String nombre = datos[2];
                    String curso = datos[1];
                    estudiantes.add(new Estudiante(nombre, curso));
                }
            }
        } catch (IOException e) {
            System.err.println("No se encontró archivo en el CSV");
        }

        return estudiantes;
    }

    public List<Evaluacion> leerEvaluaciones(String archivoRuta) {
        List<Evaluacion> evaluaciones = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoRuta))) {
            String linea;
            lector.readLine(); // Saltar encabezado

            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length >= 6) { // Verificar que hay suficientes columnas
                    String student = datos[0].trim();
                    String subject = datos[1].trim();
                    String evaluationType = datos[2].trim();
                    String evaluationName = datos[3].trim();
                    String grade = datos[5].trim();

                    try {
                        double gradeValue = Double.parseDouble(grade);
                        evaluaciones.add(new Evaluacion(subject, evaluationType, evaluationName, student, gradeValue));
                    } catch (NumberFormatException e) {
                        System.err.println("Valor de criterio no numérico en línea: " + linea);
                    }
                } else {
                    System.err.println("Línea con formato incorrecto o datos faltantes: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo archivo CSV: " + e.getMessage());
        }

        return evaluaciones;
    }



    public Map<String, Double> procesarEvaluaciones(String archivoPath) {
        Map<String, Double> notasFinales = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoPath))) {
            String linea;

            // Leer el archivo línea por línea
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en partes usando coma como delimitador
                String[] partes = linea.split(",");

                // Verificar que haya suficientes columnas en la línea
                if (partes.length < 4) {
                    System.err.println("Línea con formato incorrecto o datos faltantes: " + linea);
                    continue; // Saltar a la siguiente línea si la actual no tiene el formato esperado
                }

                String subjectName = partes[0];
                String criteriaType = partes[1];
                double criteriaValue;

                // Intentar parsear el valor de criterio a double
                try {
                    criteriaValue = Double.parseDouble(partes[2]);
                } catch (NumberFormatException e) {
                    System.err.println("Valor de criterio no numérico en línea: " + linea);
                    continue; // Saltar esta línea si el valor no es un número válido
                }

                // Procesar las evaluaciones a partir del índice 3 en adelante
                for (int i = 3; i < partes.length; i++) {
                    String evaluationName = partes[i];
                    // Aquí puedes aplicar lógica adicional si es necesario, dependiendo del tipo de criterio

                    // Para este ejemplo, guardaremos el valor en el mapa usando el nombre de la evaluación como clave
                    notasFinales.put(evaluationName, criteriaValue);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return notasFinales;
    }


    public List<CriterioEvaluacion> leerCriterios(String archivoRuta) {
        List<CriterioEvaluacion> criterios = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoRuta))) {
            String linea;
            lector.readLine(); // Saltar encabezado

            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                String subject = datos[0];
                String criteriaType = datos[1];
                double criteriaValue = Double.parseDouble(datos[2]);
                List<String> evaluationNames = Arrays.asList(Arrays.copyOfRange(datos, 3, datos.length));

                criterios.add(new CriterioEvaluacion(subject, criteriaType, criteriaValue, evaluationNames));
            }
        } catch (IOException e) {
            System.err.println("Error leyendo archivo CSV de criterios: " + e.getMessage());
        }

        return criterios;
    }

}










