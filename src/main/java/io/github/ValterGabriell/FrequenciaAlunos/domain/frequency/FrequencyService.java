package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency;

import io.github.ValterGabriell.FrequenciaAlunos.domain.days.Days;
import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto.ResponseDaysThatStudentGoToClass;
import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto.ResponseValidateFrequency;
import io.github.ValterGabriell.FrequenciaAlunos.domain.students.Student;
import io.github.ValterGabriell.FrequenciaAlunos.domain.students.StudentValidation;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.ExceptionsValues;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.RequestExceptions;
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

    /**
     * method that validate student frequency
     *
     * @param studentId
     * @return response with the student frequency validated or erro while validation frequency
     * TODO: create validation to student only mark frequency once per day.
     */
    public ResponseValidateFrequency validateFrequency(String studentId) throws RequestExceptions {
        if (studentId.length() != 11) {
            throw new RequestExceptions(ExceptionsValues.ILLEGAL_CPF_LENGTH);
        }

        ResponseValidateFrequency responseValidateFrequency = new ResponseValidateFrequency();
        ArrayList<Days> arrayOfDay = new ArrayList<>();
        Days days = new Days();

        Student student = validateIfStudentExistsAndReturnIfExist(studentsRepository, studentId);
        Frequency frequency = student.getFrequency();

        days.setFrequency(frequency);
        days.setDate(LocalDate.now());
        arrayOfDay.add(days);
        frequency.setDaysList(arrayOfDay);
        studentsRepository.save(student);

        responseValidateFrequency.setMessage("Frequência para " + student.getUsername() + " válidada! - Dia: " + LocalDate.now());
        return responseValidateFrequency;
    }

    /**
     * return list with days that specific student watched class
     *
     * @param studentId
     */
    public ResponseDaysThatStudentGoToClass getListOfDaysByFrequencyId(String studentId) throws RequestExceptions {
        if (studentId.length() != 11) {
            throw new RequestExceptions(ExceptionsValues.ILLEGAL_CPF_LENGTH);
        }

        Student student = validateIfStudentExistsAndReturnIfExist(studentsRepository, studentId);
        ResponseDaysThatStudentGoToClass responseDaysThatStudentGoToClass = new ResponseDaysThatStudentGoToClass();
        responseDaysThatStudentGoToClass.setStudentId(student.getCpf());
        responseDaysThatStudentGoToClass.setDaysListThatStudentGoToClass(student.getFrequency().getDaysList());
        return responseDaysThatStudentGoToClass;
    }

}
