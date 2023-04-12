package io.github.ValterGabriell.FrequenciaAlunos.domain.QRCode;

import com.google.zxing.WriterException;
import io.github.ValterGabriell.FrequenciaAlunos.domain.students.StudentValidation;
import io.github.ValterGabriell.FrequenciaAlunos.excpetion.StudentNotFoundException;
import io.github.ValterGabriell.FrequenciaAlunos.infra.repository.StudentsRepository;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class QRCodeService extends StudentValidation {

    private final StudentsRepository studentsRepository;

    public QRCodeService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public BufferedImage generateQRCode(String studentId) throws WriterException, StudentNotFoundException {
        validateIfStudentExistsAndReturnIfExist(studentsRepository, studentId);
        return QRCodeGenerate.generateQRCodeImage(studentId, 150, 150);
    }

}
