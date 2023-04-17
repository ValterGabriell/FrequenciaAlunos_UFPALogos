package io.github.ValterGabriell.FrequenciaAlunos.domain.students;

import io.github.ValterGabriell.FrequenciaAlunos.excpetion.ExceptionsValues;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.RequestExceptions;
import io.github.ValterGabriell.FrequenciaAlunos.infra.repository.StudentsRepository;

import java.util.Optional;

public class StudentValidation {

    public static Student validateIfStudentExistsAndReturnIfExist(StudentsRepository studentsRepository, String studentId) {

        Optional<Student> student = studentsRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new RequestExceptions(ExceptionsValues.USER_NOT_FOUND + " " + studentId);
        }
        return student.get();
    }

}
