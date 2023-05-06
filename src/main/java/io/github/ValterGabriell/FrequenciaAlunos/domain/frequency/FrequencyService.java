package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency;

import io.github.ValterGabriell.FrequenciaAlunos.domain.Validation;
import io.github.ValterGabriell.FrequenciaAlunos.domain.days.Days;
import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto.ResponseDaysThatStudentGoToClass;
import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto.ResponseValidateFrequency;
import io.github.ValterGabriell.FrequenciaAlunos.domain.sheet.SheetManipulation;
import io.github.ValterGabriell.FrequenciaAlunos.domain.sheet.dto.ResponseSheet;
import io.github.ValterGabriell.FrequenciaAlunos.domain.students.Student;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.ExceptionsValues;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.RequestExceptions;
import io.github.ValterGabriell.FrequenciaAlunos.infra.repository.FrequencyRepository;
import io.github.ValterGabriell.FrequenciaAlunos.infra.repository.StudentsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FrequencyService extends Validation {


    private final StudentsRepository studentsRepository;
    private final FrequencyRepository frequencyRepository;

    public FrequencyService(StudentsRepository studentsRepository, FrequencyRepository frequencyRepository) {
        this.studentsRepository = studentsRepository;
        this.frequencyRepository = frequencyRepository;
    }

    /**
     * method that validate student frequency
     *
     * @param studentId represent primary key of student table
     * @return response with the student frequency validated or erro while validation frequency
     * TODO: create validation to student only mark frequency once per day.
     */
    public ResponseValidateFrequency validateFrequency(String studentId) throws RequestExceptions {
        checkIfStudentCpfAreCorrect(studentId);
        Student student = validateIfStudentExistsAndReturnIfExist(studentsRepository, studentId);
        Frequency frequency = frequencyRepository.findById(student.getCpf()).get();
        Days currentDay = new Days(LocalDate.now());
        verifyIfDayAlreadySavedOnFrequencyAndThrowAnErroIfItIs(frequency, currentDay);

        frequency.getDaysList().add(currentDay);
        frequencyRepository.save(frequency);

        ResponseValidateFrequency responseValidateFrequency = new ResponseValidateFrequency();
        responseValidateFrequency.setMessage("Frequência para " + student.getUsername() + " válidada! - Dia: " + LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        return responseValidateFrequency;
    }

    /**
     * return list with days that specific student watched class
     *
     * @param studentId represent primary key of student table
     */
    public ResponseDaysThatStudentGoToClass getListOfDaysByFrequencyId(String studentId) throws RequestExceptions {
        checkIfStudentCpfAreCorrect(studentId);
        Student student = validateIfStudentExistsAndReturnIfExist(studentsRepository, studentId);
        Frequency frequency = frequencyRepository.findById(student.getCpf()).get();
        ResponseDaysThatStudentGoToClass responseDaysThatStudentGoToClass = new ResponseDaysThatStudentGoToClass();
        responseDaysThatStudentGoToClass.setStudentId(student.getCpf());
        responseDaysThatStudentGoToClass.setDaysListThatStudentGoToClass(frequency.getDaysList());
        return responseDaysThatStudentGoToClass;
    }

    public ResponseSheet createSheetForCurrentDay() {
        List<Student> students = studentsRepository.findAll();
        SheetManipulation sheetManipulation = new SheetManipulation();
        ResponseSheet responseSheet = new ResponseSheet();
        responseSheet.setSheetName("Planilha do dia " + LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ".xls");
        responseSheet.setSheetByteArray(sheetManipulation.createSheet(students));
        return responseSheet;
    }

    public ResponseSheet returnSheetForSpecifyDay(LocalDate date) {
        List<Student> students = studentsRepository
                .findAll()
                .stream()
                .filter(student -> frequencyRepository.findById(student.getCpf()).get().getDaysList().stream().anyMatch(_day -> _day.getDate().equals(date)))
                .collect(Collectors.toList());
        SheetManipulation sheetManipulation = new SheetManipulation();
        ResponseSheet responseSheet = new ResponseSheet();
        responseSheet.setSheetName("Planilha do dia " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ".xls");
        responseSheet.setSheetByteArray(sheetManipulation.createSheet(students, date));
        return responseSheet;
    }

    public ResponseValidateFrequency justifyAbsence(LocalDate date, String cpf) {
        Student student = studentsRepository.findById(cpf).orElseThrow(() -> new RequestExceptions(ExceptionsValues.USER_NOT_FOUND));
        Frequency frequency = frequencyRepository.findById(student.getCpf()).get();
        Days day = new Days(date);
        verifyIfDayAlreadySavedOnFrequencyAndThrowAnErroIfItIs(frequency, day);

        day.setJustified(true);
        frequency.getDaysList().add(day);
        frequencyRepository.save(frequency);

        ResponseValidateFrequency responseValidateFrequency = new ResponseValidateFrequency();
        responseValidateFrequency.setMessage("Frequência para " + student.getUsername() + " justificada! - Dia: " + LocalDate.now());
        return responseValidateFrequency;

    }

}