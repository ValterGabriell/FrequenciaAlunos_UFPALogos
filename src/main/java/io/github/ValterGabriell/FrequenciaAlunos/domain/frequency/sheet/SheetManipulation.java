package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.sheet;

import io.github.ValterGabriell.FrequenciaAlunos.domain.days.Days;
import io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.Frequency;
import io.github.ValterGabriell.FrequenciaAlunos.domain.students.Student;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SheetManipulation {
    private static final String fileName = "C:/teste/teste.xls";

    private static Student createStudent(String cpf) {
        Student student = new Student();
        student.setCpf(cpf);

        Frequency frequency = new Frequency();
        frequency.setId(student.getCpf());

        Days days = new Days();
        days.setDate(LocalDate.now());
        days.setFrequency(frequency);
        List<Days> daysList = new ArrayList<>();
        daysList.add(days);

        frequency.setDaysList(daysList);

        student.setFrequency(frequency);
        return student;
    }

    public void createSheet(List<Student> students) throws FileNotFoundException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheetAlunos = workbook.createSheet(LocalDate.now().getDayOfMonth() + " " + LocalDate.now().getMonth().toString() + " - PRESENÇA");

        List<String> columnTitle = new ArrayList<>();
        columnTitle.add("ALUNO ID");
        columnTitle.add("ALUNO NOME");
        columnTitle.add("DIA");
        columnTitle.add("PRESENTE");

        Row row = sheetAlunos.createRow(0);
        int cellnumber = 0;
        for (String value : columnTitle) {
            Cell cell = row.createCell(cellnumber++);
            cell.setCellValue(value);
        }

        int rownumber = 1;
        int columnnumber = 0;
        for (Student student : students) {
            Row _row = sheetAlunos.createRow(rownumber++);

            Cell cellCpf = _row.createCell(columnnumber++);
            cellCpf.setCellValue(student.getCpf());

            Cell cellName = _row.createCell(columnnumber++);
            cellName.setCellValue(student.getUsername());

            Cell cellDate = _row.createCell(columnnumber++);
            cellDate.setCellValue(LocalDate.now().toString());

            Cell cellOk = _row.createCell(columnnumber++);
            cellOk.setCellValue("OK");

            columnnumber = 0;
        }


        try {
            FileOutputStream out = new FileOutputStream(new File(SheetManipulation.fileName));
            workbook.write(out);
            out.close();
            System.out.println("Arquivo Excel criado com sucesso!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro na edição do arquivo!");
        }
    }

    private List<Student> mockStudents() {
        Student student = createStudent("03856573232");
        Student student1 = createStudent("03856573231");
        Student student2 = createStudent("03856573233");

        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student1);
        list.add(student2);

        return list;
    }
}
