package io.github.ValterGabriell.FrequenciaAlunos.domain.students.dto;

import io.github.ValterGabriell.FrequenciaAlunos.excpetion.ExceptionsValues;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.RequestExceptions;

public abstract class FieldValidation{
    public boolean fieldContainsOnlyLetters(String field) {
        String regex = "^[a-zA-Z]+$";
        boolean isUsernameLenghtOk = field.matches(regex);
        if (!isUsernameLenghtOk) {
            throw new RequestExceptions(ExceptionsValues.USERNAME_ILLEGAL_CHARS);
        }
        return true;
    }

    public boolean isFieldHasNumberExcatlyOfChars(String field, int charNumber) {
        boolean isCpfLenghtOk = field.length() == charNumber;
        if (!isCpfLenghtOk) {
            throw new RequestExceptions(ExceptionsValues.ILLEGAL_CPF_LENGTH);
        }
        return true;
    }

    abstract boolean validateIfIsEmpty(String field, String exceptionMessage) throws RequestExceptions;
}
