package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.ValterGabriell.FrequenciaAlunos.domain.days.Day;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Frequency {
    @Id
    private String id;
    @OneToMany(targetEntity = Day.class, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Day> dayList;

    public void setId(String id) {
        this.id = id;
    }

    public List<Day> getDaysList() {
        return dayList;
    }

    public void setDaysList(List<Day> dayList) {
        this.dayList = dayList;
    }

    public Frequency() {
    }
}
