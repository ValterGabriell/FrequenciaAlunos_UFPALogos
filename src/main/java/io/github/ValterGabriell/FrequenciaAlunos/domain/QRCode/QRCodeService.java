package io.github.ValterGabriell.FrequenciaAlunos.domain.QRCode;

import com.google.zxing.WriterException;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.ExceptionsValues;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.RequestExceptions;
import io.github.ValterGabriell.FrequenciaAlunos.infra.repository.StudentsRepository;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

import static io.github.ValterGabriell.FrequenciaAlunos.domain.students.StudentValidation.validateIfStudentExistsAndReturnIfExist;

@Service
public class QRCodeService {

    private final StudentsRepository studentsRepository;

    public QRCodeService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    /**
     * Method that create and returns qrcode with student id
     * @param studentId
     * @return qrcode generated
     * @throws WriterException
     */
    public BufferedImage generateQRCode(String studentId) throws WriterException, RequestExceptions {
        if (studentId.length() != 11) {
            throw new RequestExceptions(ExceptionsValues.ILLEGAL_CPF_LENGTH);
        }
        validateIfStudentExistsAndReturnIfExist(studentsRepository, studentId);
        return QRCodeGenerate.generateQRCodeImage(studentId, 150, 150);
    }

}
