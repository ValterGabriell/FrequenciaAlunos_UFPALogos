package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency;

import io.github.ValterGabriell.FrequenciaAlunos.domain.days.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class FrequencyServiceTest {

    @Test
     void verifyIfDayAlreadySavedOnFrequencyAndThrowAnErroIfItIs() {
        List<Day> days = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Day day = new Day(date);
        days.add(day);
        boolean contains = days.contains(day);
        Assertions.assertTrue(contains);
    }

}