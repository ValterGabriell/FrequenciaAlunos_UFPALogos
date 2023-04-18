package io.github.ValterGabriell.FrequenciaAlunos.domain.days;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.Frequency;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Days {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Frequency frequency;

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Days() {
    }


    @JsonIgnore
    public String getId() {
        return id;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
