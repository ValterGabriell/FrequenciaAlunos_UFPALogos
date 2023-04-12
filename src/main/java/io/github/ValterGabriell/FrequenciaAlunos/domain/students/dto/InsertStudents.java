package io.github.ValterGabriell.FrequenciaAlunos.domain.students.dto;

import io.github.ValterGabriell.FrequenciaAlunos.domain.students.Student;
import jakarta.annotation.Nonnull;

public class InsertStudents {
    @Nonnull
    private String cpf;
    @Nonnull
    private String username;

    public InsertStudents(String cpf, String username) {
        this.cpf = cpf;
        this.username = username;
    }

    public InsertStudents() {
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
