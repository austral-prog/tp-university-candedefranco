package com.university;

import java.util.List;

public class CriterioEvaluacion {
    private String subject;
    private String criteriaType;
    private double criteriaValue;
    private List<String> evaluationNames;

    public CriterioEvaluacion(String subject, String criteriaType, double criteriaValue, List<String> evaluationNames) {
        this.subject = subject;
        this.criteriaType = criteriaType;
        this.criteriaValue = criteriaValue;
        this.evaluationNames = evaluationNames;
    }

    public String getSubject() {
        return subject;
    }

    public String getCriteriaType() {
        return criteriaType;
    }

    public double getCriteriaValue() {
        return criteriaValue;
    }

    public List<String> getEvaluationNames() {
        return evaluationNames;
    }
}

