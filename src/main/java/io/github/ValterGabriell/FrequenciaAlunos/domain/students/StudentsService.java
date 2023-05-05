package io.github.ValterGabriell.FrequenciaAlunos.domain.students;

import io.github.ValterGabriell.FrequenciaAlunos.domain.Validation;
import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.Frequency;
import io.github.ValterGabriell.FrequenciaAlunos.domain.students.dto.InsertStudents;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.RequestExceptions;
import io.github.ValterGabriell.FrequenciaAlunos.infra.repository.StudentsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static io.github.ValterGabriell.FrequenciaAlunos.excpetion.ExceptionsValues.STUDENT_ALREADY_SAVED;

@Service
public class StudentsService extends Validation {
    private final StudentsRepository studentsRepository;

    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Student insertStudentIntoDatabase(InsertStudents request) {
        boolean present = studentsRepository.findById(request.getCpf()).isPresent();
        if (present) {
            throw new RequestExceptions(STUDENT_ALREADY_SAVED);
        }
        Student student = request.toModel();
        if (request.usernameIsNull()
                && request.isFieldHasNumberExcatlyOfChars(request.getCpf(), 11)
                && request.usernameHasToBeMoreThan2Chars()
                && request.fieldContainsOnlyLetters(request.getUsername())) {
            Frequency frequency = new Frequency();
            frequency.setDaysList(new ArrayList<>());
            frequency.setId(request.getCpf());
            student.setFrequency(frequency);
            studentsRepository.save(student);
        }
        return student;
    }

    public List<Student> getAllStudentsFromDatabase() {
        return studentsRepository.findAll();
    }

    public void deleteStudent(String studentId) {
        Student student = validateIfStudentExistsAndReturnIfExist(studentsRepository, studentId);
        studentsRepository.delete(student);
    }
}