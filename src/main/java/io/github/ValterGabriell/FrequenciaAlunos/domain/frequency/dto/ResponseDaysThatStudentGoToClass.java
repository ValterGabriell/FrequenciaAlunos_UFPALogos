package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto;

import io.github.ValterGabriell.FrequenciaAlunos.domain.days.Days;

import java.util.List;

public class ResponseDaysThatStudentGoToClass {
    private String studentId;
    private List<Days> daysListThatStudentGoToClasses;

    public ResponseDaysThatStudentGoToClass() {
    }

    public ResponseDaysThatStudentGoToClass(String studentId, List<Days> daysListThatStudentGoToClasses) {
        this.studentId = studentId;
        this.daysListThatStudentGoToClasses = daysListThatStudentGoToClasses;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<Days> getDaysListThatStudentGoToClass() {
        return daysListThatStudentGoToClasses;
    }

    public void setDaysListThatStudentGoToClass(List<Days> daysListThatStudentGoToClasses) {
        this.daysListThatStudentGoToClasses = daysListThatStudentGoToClasses;
    }
}
