package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency;

import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto.ResponseDaysThatStudentGoToClass;
import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.dto.ResponseValidateFrequency;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.RequestExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(params = {"studentId"})
    public ResponseEntity<ResponseDaysThatStudentGoToClass> getListOfDays(@RequestParam String studentId) throws RequestExceptions {
        ResponseDaysThatStudentGoToClass listOfDaysByFrequencyId = frequencyService.getListOfDaysByFrequencyId(studentId);
        return new ResponseEntity<>(listOfDaysByFrequencyId, HttpStatus.OK);
    }
}
