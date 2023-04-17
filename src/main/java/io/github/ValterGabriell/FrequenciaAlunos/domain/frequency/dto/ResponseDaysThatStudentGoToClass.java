package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto;

import io.github.ValterGabriell.FrequenciaAlunos.domain.days.Days;

import java.util.List;

public class ResponseDaysThatStudentGoToClass {
    private String studentId;
    private List<Days> daysListThatStudentGoToClass;

    public ResponseDaysThatStudentGoToClass() {
    }

    public ResponseDaysThatStudentGoToClass(String studentId, List<Days> daysListThatStudentGoToClass) {
        this.studentId = studentId;
        this.daysListThatStudentGoToClass = daysListThatStudentGoToClass;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<Days> getDaysListThatStudentGoToClass() {
        return daysListThatStudentGoToClass;
    }

    public void setDaysListThatStudentGoToClass(List<Days> daysListThatStudentGoToClass) {
        this.daysListThatStudentGoToClass = daysListThatStudentGoToClass;
    }
}
