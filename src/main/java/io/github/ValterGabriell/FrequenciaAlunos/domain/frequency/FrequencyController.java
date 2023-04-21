package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency;

import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto.ResponseDaysThatStudentGoToClass;
import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto.ResponseValidateFrequency;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.RequestExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;

@RestController
@RequestMapping("frequency")
public class FrequencyController {
    private final FrequencyService frequencyService;


    public FrequencyController(FrequencyService frequencyService) {
        this.frequencyService = frequencyService;
    }

    @PostMapping(params = {"studentId"})
    public ResponseEntity<ResponseValidateFrequency> validateFrequency(@RequestParam String studentId) throws RequestExceptions {
        ResponseValidateFrequency responseValidadeFrequency = frequencyService.validateFrequency(studentId);
        return new ResponseEntity<>(responseValidadeFrequency, HttpStatus.OK);
    }

    @PostMapping(params = {"studentId", "date"})
    public ResponseEntity<ResponseValidateFrequency> justifyAbsence(@RequestParam String studentId, @RequestParam LocalDate date) throws RequestExceptions {
        ResponseValidateFrequency responseValidadeFrequency = frequencyService.justifyAbsence(date, studentId);
        return new ResponseEntity<>(responseValidadeFrequency, HttpStatus.OK);
    }

    @GetMapping(params = {"studentId"})
    public ResponseEntity<ResponseDaysThatStudentGoToClass> getListOfDays(@RequestParam String studentId) throws RequestExceptions {
        ResponseDaysThatStudentGoToClass listOfDaysByFrequencyId = frequencyService.getListOfDaysByFrequencyId(studentId);
        return new ResponseEntity<>(listOfDaysByFrequencyId, HttpStatus.OK);
    }

    @GetMapping(value = "sheet")
    public ResponseEntity createSheet() throws FileNotFoundException {
        frequencyService.createSheetForCurrentDay();
        return ResponseEntity.status(HttpStatus.OK).body("OK!");
    }

    @GetMapping(value = "sheet", params = {"date"})
    public ResponseEntity getSheetForSpecifyDay(@RequestParam LocalDate date) throws FileNotFoundException {
        frequencyService.returnSheetForSpecifyDay(date);
        return ResponseEntity.status(HttpStatus.OK).body("OK!");
    }
}
