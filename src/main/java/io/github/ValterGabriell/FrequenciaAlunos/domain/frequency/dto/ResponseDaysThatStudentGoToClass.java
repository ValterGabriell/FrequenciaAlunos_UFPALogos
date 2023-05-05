package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto;

import io.github.ValterGabriell.FrequenciaAlunos.domain.days.Day;

import java.util.List;

public class ResponseDaysThatStudentGoToClass {
    private String studentId;
    private List<Day> dayListThatStudentGoToClasses;

    public ResponseDaysThatStudentGoToClass() {
    }

    public ResponseDaysThatStudentGoToClass(String studentId, List<Day> dayListThatStudentGoToClasses) {
        this.studentId = studentId;
        this.dayListThatStudentGoToClasses = dayListThatStudentGoToClasses;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<Day> getDaysListThatStudentGoToClass() {
        return dayListThatStudentGoToClasses;
    }

    public void setDaysListThatStudentGoToClass(List<Day> dayListThatStudentGoToClasses) {
        this.dayListThatStudentGoToClasses = dayListThatStudentGoToClasses;
    }
}
