package io.github.ValterGabriell.FrequenciaAlunos.domain.sheet;

import io.github.ValterGabriell.FrequenciaAlunos.domain.students.Student;

import java.time.LocalDate;
import java.util.List;

public interface ISheetManipulation {
    //this class should appear
    void createSheet(List<Student> students);

    void createSheet(List<Student> students, LocalDate localDate);
}
