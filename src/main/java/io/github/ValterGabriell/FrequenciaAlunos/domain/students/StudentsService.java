package io.github.ValterGabriell.FrequenciaAlunos.domain.students;

import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.Frequency;
import io.github.ValterGabriell.FrequenciaAlunos.domain.students.dto.InsertStudents;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.ExceptionsValues;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.RequestExceptions;
import io.github.ValterGabriell.FrequenciaAlunos.infra.repository.StudentsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsService {
    private final StudentsRepository studentsRepository;

    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Student insertStudentIntoDatabase(InsertStudents request) {

        if (request.getCpf().length() != 11) {
            throw new RequestExceptions(ExceptionsValues.ILLEGAL_CPF_LENGTH);
        }
        Student student = request.toModel();
        Frequency frequency = new Frequency();
        frequency.setDaysList(new ArrayList<>());
        frequency.setId(request.getCpf());
        student.setFrequency(frequency);
        studentsRepository.save(student);
        return student;
    }

    public List<Student> getAllStudentsFromDatabase() {
        return studentsRepository.findAll();
    }
}
