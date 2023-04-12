package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency;

import io.github.ValterGabriell.FrequenciaAlunos.domain.days.Days;
import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto.ResponseDaysThatStudentGoToClass;
import io.github.ValterGabriell.FrequenciaAlunos.domain.students.Student;
import io.github.ValterGabriell.FrequenciaAlunos.domain.students.StudentValidation;
import io.github.ValterGabriell.FrequenciaAlunos.infra.repository.StudentsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class FrequencyService extends StudentValidation {


    private final StudentsRepository studentsRepository;

    public FrequencyService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Student validateFrequency(String studentId) {
        Student student = validateIfStudentExistsAndReturnIfExist(studentsRepository, studentId);
        Frequency frequency = student.getFrequency();

        ArrayList<Days> arrayOfDay = new ArrayList<>();
        Days days = new Days();
        days.setFrequency(frequency);
        days.setDate(LocalDate.now());
        arrayOfDay.add(days);

        frequency.setDaysList(arrayOfDay);
        studentsRepository.save(student);

        return student;
    }

    public ResponseDaysThatStudentGoToClass getListOfDaysByFrequencyId(String studentId) {
        Student student = validateIfStudentExistsAndReturnIfExist(studentsRepository, studentId);
        ResponseDaysThatStudentGoToClass responseDaysThatStudentGoToClass = new ResponseDaysThatStudentGoToClass();
        responseDaysThatStudentGoToClass.setStudentId(student.getCpf());
        responseDaysThatStudentGoToClass.setDaysListThatStudentGoToClass(student.getFrequency().getDaysList());
        responseDaysThatStudentGoToClass.setFrequencyId(student.getCpf());
        return responseDaysThatStudentGoToClass;
    }

}
