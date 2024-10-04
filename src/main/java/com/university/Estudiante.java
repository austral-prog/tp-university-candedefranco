package com.university;

public class Estudiante {
    private String nombre;
    private String curso;

    public Estudiante(String nombre, String curso) {
        this.nombre = nombre;
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCurso() {
        return curso;
    }
}

