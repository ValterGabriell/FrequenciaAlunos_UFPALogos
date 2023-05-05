package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto;

import io.github.ValterGabriell.FrequenciaAlunos.domain.days.Days;

import java.util.List;

public class ResponseDaysThatStudentGoToClass {
    private String studentId;
    private List<Days> dayListThatStudentGoToClasses;

    public ResponseDaysThatStudentGoToClass() {
    }

    public ResponseDaysThatStudentGoToClass(String studentId, List<Days> dayListThatStudentGoToClasses) {
        this.studentId = studentId;
        this.dayListThatStudentGoToClasses = dayListThatStudentGoToClasses;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<Days> getDaysListThatStudentGoToClass() {
        return dayListThatStudentGoToClasses;
    }

    public void setDaysListThatStudentGoToClass(List<Days> dayListThatStudentGoToClasses) {
        this.dayListThatStudentGoToClasses = dayListThatStudentGoToClasses;
    }
}