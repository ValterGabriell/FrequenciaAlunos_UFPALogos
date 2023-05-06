package io.github.ValterGabriell.FrequenciaAlunos.domain.QRCode;

import com.google.zxing.WriterException;
import io.github.ValterGabriell.FrequenciaAlunos.domain.QRCode.dto.QrCodeMessage;
import io.github.ValterGabriell.FrequenciaAlunos.domain.Validation;
import io.github.ValterGabriell.FrequenciaAlunos.domain.students.Student;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.ExceptionsValues;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.RequestExceptions;
import io.github.ValterGabriell.FrequenciaAlunos.infra.repository.StudentsRepository;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class QRCodeService extends Validation {

    private final StudentsRepository studentsRepository;

    public QRCodeService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    /**
     * Method that create and returns qrcode with student id
     * @param studentId representing the student id to put data on qrcode
     * @return qrcode generated
     * @throws WriterException
     */
    public BufferedImage generateQRCode(String studentId) throws WriterException, RequestExceptions {
        if (studentId.length() != 11) {
            throw new RequestExceptions(ExceptionsValues.ILLEGAL_CPF_LENGTH);
        }
        Student student = validateIfStudentExistsAndReturnIfExist(studentsRepository, studentId);
        QrCodeMessage qrm = new QrCodeMessage(student.getUsername(), student.getCpf());
        return QRCodeGenerate.generateQRCodeImage(qrm, 150, 150);
    }

}