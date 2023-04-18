package io.github.ValterGabriell.FrequenciaAlunos.domain.frequency.sheet;

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

    private static void createHeadersOfColumns(HSSFSheet sheetAlunos) {
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
    }

    private static void createColumnsWithFields(List<Student> students, HSSFSheet sheetAlunos) {
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
    }

    private static void handleCreateSheet(HSSFWorkbook workbook) {
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

    public void createSheet(List<Student> students) throws FileNotFoundException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheetAlunos = workbook.createSheet(LocalDate.now().getDayOfMonth() + " " + LocalDate.now().getMonth().toString() + " - PRESENÇA");

        createHeadersOfColumns(sheetAlunos);
        createColumnsWithFields(students, sheetAlunos);
        handleCreateSheet(workbook);
    }

    public void createSheet(List<Student> students, LocalDate localDate) throws FileNotFoundException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheetAlunos = workbook.createSheet(localDate.getDayOfMonth() + " " + localDate.getMonth().toString() + " - PRESENÇA");

        createHeadersOfColumns(sheetAlunos);
        createColumnsWithFields(students, sheetAlunos);
        handleCreateSheet(workbook);
    }

}
