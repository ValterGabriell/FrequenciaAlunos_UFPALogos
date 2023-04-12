package io.github.ValterGabriell.FrequenciaAlunos.excpetion;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

public class StudentNotFoundException extends HttpClientErrorException {

    public StudentNotFoundException(HttpStatusCode statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
