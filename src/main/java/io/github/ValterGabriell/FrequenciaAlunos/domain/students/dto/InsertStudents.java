package io.github.ValterGabriell.FrequenciaAlunos.domain.students.dto;

import io.github.ValterGabriell.FrequenciaAlunos.domain.students.Student;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.ExceptionsValues;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.RequestExceptions;

public class InsertStudents {
    private String cpf;

    private String username;

    public InsertStudents(String cpf, String username) {
        this.cpf = cpf;
        this.username = username;
    }

    public InsertStudents() {
    }

    public boolean usernameIsNull() {
        boolean isUsernameNotNull = !getUsername().isEmpty() && !getUsername().isBlank();
        if (!isUsernameNotNull){
            throw new RequestExceptions(ExceptionsValues.USERNAME_NULL);
        }
        return true;
    }

    public boolean usernameHasToBeMoreThan2Chars() {
        boolean isUsernameLenghtOk = getUsername().length() > 2;
        if (!isUsernameLenghtOk){
            throw new RequestExceptions(ExceptionsValues.USERNAME_ILLEGAL_LENGHT);
        }
        return true;
    }

    public boolean isCpfHave11chars() {

        boolean isCpfLenghtOk = getCpf().length() == 11;
        if (!isCpfLenghtOk){
            throw new RequestExceptions(ExceptionsValues.ILLEGAL_CPF_LENGTH);
        }
        return true;
    }

    public Student toModel() {
        return new Student(this.cpf, this.username);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
