package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FrequencyServiceTest {

    @Test
    public void returnTrueIfStudentIdIsStringWithSizeEleven() {
        String studentId = "sadfafqwfsa";
        int length = studentId.length();
        assertEquals(11, length);
    }

}