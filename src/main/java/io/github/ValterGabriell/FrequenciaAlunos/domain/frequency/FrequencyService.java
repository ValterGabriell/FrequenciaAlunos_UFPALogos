package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency;

import io.github.ValterGabriell.FrequenciaAlunos.domain.days.Days;
import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto.ResponseDaysThatStudentGoToClass;
import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto.ResponseValidateFrequency;
import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.sheet.SheetManipulation;
import io.github.ValterGabriell.FrequenciaAlunos.domain.students.Student;
import io.github.ValterGabriell.FrequenciaAlunos.domain.students.StudentValidation;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.ExceptionsValues;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.RequestExceptions;
import io.github.ValterGabriell.FrequenciaAlunos.infra.repository.StudentsRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class FrequencyService extends StudentValidation {


    private final StudentsRepository studentsRepository;

    public FrequencyService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    /**
     * method that validate student frequency
     *
     * @param studentId represent primary key of student table
     * @return response with the student frequency validated or erro while validation frequency
     * TODO: create validation to student only mark frequency once per day.
     */
    public ResponseValidateFrequency validateFrequency(String studentId) throws RequestExceptions {
        if (studentId.length() != 11) {
            throw new RequestExceptions(ExceptionsValues.ILLEGAL_CPF_LENGTH);
        }
        Student student = validateIfStudentExistsAndReturnIfExist(studentsRepository, studentId);
        Frequency frequency = student.getFrequency();

        Days currentDays = new Days();
        currentDays.setFrequency(frequency);
        currentDays.setDate(LocalDate.now());

        /**
         * throw erro if try validate same day twice
         */
        frequency.getDaysList().forEach(days -> {
            if (days.getDate().equals(LocalDate.now())) {
                throw new RequestExceptions(ExceptionsValues.STUDENT_ALREADY_VALIDATED);
            }
        });

        frequency.getDaysList().add(currentDays);

        studentsRepository.save(student);

        ResponseValidateFrequency responseValidateFrequency = new ResponseValidateFrequency();
        responseValidateFrequency.setMessage("Frequência para " + student.getUsername() + " válidada! - Dia: " + LocalDate.now());
        return responseValidateFrequency;
    }

    /**
     * return list with days that specific student watched class
     *
     * @param studentId represent primary key of student table
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

    public void createSheet() throws FileNotFoundException {
        List<Student> students = studentsRepository.findAll();
        SheetManipulation sheetManipulation = new SheetManipulation();
        sheetManipulation.createSheet(students);
    }

}
