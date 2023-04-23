package io.github.ValterGabriell.FrequenciaAlunos.domain.students;

import io.github.ValterGabriell.FrequenciaAlunos.domain.students.dto.InsertStudents;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.ExceptionsValues;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.RequestExceptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentsServiceTest {

    private InsertStudents studentUsernameTest;
    private InsertStudents studentCpfTest;

    @BeforeEach
    public void setUp() {
        studentUsernameTest = new InsertStudents("0", "ana");
        studentCpfTest = new InsertStudents("00000000000", "");
    }

    @Test
    @DisplayName("A username should be not null and return true when it is")
    void isUsernameNotNull_ReturnTrue_WhenUsernameIsNotNull() {
        Assertions.assertTrue(studentUsernameTest.usernameIsNull());
    }

    @Test
    @DisplayName("A username should have only letters and no numbers")
    void isUsernameOnlyLetters() {
        Assertions.assertTrue(studentUsernameTest.usernameHasContainsOnlyLetters());
    }

    @Test
    @DisplayName("A username should have more than 2 characters and return true when it is")
    void isUsernameBiggerThan2Chars() {
        Assertions.assertTrue(studentUsernameTest.usernameHasToBeMoreThan2Chars());
    }

    @Test
    @DisplayName("cpf should have exactly 11 characters and return true when it is")
    void cpfLenght() {
        Assertions.assertTrue(studentCpfTest.isCpfHave11chars());
    }

    @Test
    @DisplayName("show throw RequestException with message when cpf are diferent than 11")
    void shoudExceptionWhenCpfIsWrongLenght() {
        studentCpfTest.setCpf("123");
        Assertions.assertThrows(RequestExceptions.class, () -> studentCpfTest.isCpfHave11chars());
    }

    @Test
    @DisplayName("show throw RequestException with message when username is less than 11")
    void shoudExceptionWhenUsernameIsWrongLenght() {
        studentUsernameTest.setUsername("as");
        Assertions.assertThrows(RequestExceptions.class, () -> studentCpfTest.usernameHasToBeMoreThan2Chars());
    }

    @Test
    @DisplayName("show throw RequestException with message when username is null")
    void shoudExceptionWhenUsernameIsNull() {
        studentCpfTest.setUsername(" ");
        Assertions.assertThrows(RequestExceptions.class, () -> studentCpfTest.usernameIsNull(), ExceptionsValues.USERNAME_NULL);
    }


}