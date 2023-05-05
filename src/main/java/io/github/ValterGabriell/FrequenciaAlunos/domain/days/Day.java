package io.github.ValterGabriell.FrequenciaAlunos.domain.days;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDate date;
    private boolean justified;

    public Day(LocalDate date) {
        this.date = date;
    }

    public Day(LocalDate date, boolean justified) {
        this.date = date;
        this.justified = false;
    }

    public boolean isJustified() {
        return justified;
    }

    public void setJustified(boolean justified) {
        this.justified = justified;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return date != null && date.equals(((Day) o).date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
