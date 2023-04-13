package io.github.ValterGabriell.FrequenciaAlunos.domain.students;

import io.github.ValterGabriell.FrequenciaAlunos.excpetion.StudentNotFoundException;
import io.github.ValterGabriell.FrequenciaAlunos.infra.repository.StudentsRepository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class StudentValidation {

    public static Student validateIfStudentExistsAndReturnIfExist(StudentsRepository studentsRepository, String studentId){
        Optional<Student> student = studentsRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new StudentNotFoundException(HttpStatus.NOT_FOUND, "Estudante com id " + studentId + " não foi encontrado");
        }
        return student.get();
    }

}
