package io.github.ValterGabriell.FrequenciaAlunos.domain.students;

import io.github.ValterGabriell.FrequenciaAlunos.domain.students.dto.InsertStudents;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
public class StudentsController {

    private final StudentsService service;


    public StudentsController(StudentsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Student> insertStudentsIntoDatabase(@RequestBody InsertStudents request) {
        Student student = service.insertStudentIntoDatabase(request);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
}
