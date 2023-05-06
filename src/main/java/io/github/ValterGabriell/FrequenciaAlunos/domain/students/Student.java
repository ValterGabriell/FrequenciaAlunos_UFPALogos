package io.github.ValterGabriell.FrequenciaAlunos.domain.students;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.Frequency;
import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @Column(name = "cpf", nullable = false)
    private String cpf;
    @Column(name = "nome", nullable = false)
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @Transient
    private Frequency frequency;

    public Student(String cpf, String username) {
        this.cpf = cpf;
        this.username = username;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Student() {
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
