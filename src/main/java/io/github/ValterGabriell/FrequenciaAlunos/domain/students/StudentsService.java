package io.github.ValterGabriell.FrequenciaAlunos.domain.students;

import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.Frequency;
import io.github.ValterGabriell.FrequenciaAlunos.domain.students.dto.InsertStudents;
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
