package io.github.ValterGabriell.FrequenciaAlunos.domain.students.dto;

import io.github.ValterGabriell.FrequenciaAlunos.domain.students.Student;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.ExceptionsValues;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.RequestExceptions;

public class InsertStudents extends FieldValidation {
    private String cpf;

    private String username;

    public InsertStudents(String cpf, String username) {
        this.cpf = cpf;
        this.username = username;
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

    @Override
    public boolean validateIfIsEmpty(String field, String e) {
        boolean isFieldNotNull = !field.isEmpty() || field.isBlank();
        if (!isFieldNotNull) {
            throw new RequestExceptions(e);
        }
        return true;
    }

    public boolean usernameIsNull() {
        return validateIfIsEmpty(getUsername(), ExceptionsValues.USERNAME_NULL);
    }

    public boolean cpfIsNull() {
        return validateIfIsEmpty(getCpf(), ExceptionsValues.CPF_NULL);
    }

    public boolean usernameHasToBeMoreThan2Chars() {
        return validateIfIsEmpty(getUsername(), ExceptionsValues.USERNAME_ILLEGAL_LENGHT);
    }


    public Student toModel() {
        return new Student(this.cpf, this.username);
    }
}
