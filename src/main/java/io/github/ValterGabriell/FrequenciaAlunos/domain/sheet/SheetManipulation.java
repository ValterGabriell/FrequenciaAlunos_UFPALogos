package io.github.ValterGabriell.FrequenciaAlunos.domain.sheet;

import io.github.ValterGabriell.FrequenciaAlunos.domain.students.Student;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SheetManipulation implements ISheetManipulation {
    /**
     * create headers for sheet student - NAME, CPF, DATE, PRESENT
     *
     * @param sheetAlunos sheet
     */
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

    /**
     * fill the sheet with students data
     *
     * @param students    current student to insert on sheet
     * @param sheetAlunos sheet
     */
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

    /**
     * method to create sheet on current PC
     *
     * @param workbook     specify type to work with sheets
     * @param currentMonth current month as string
     * @param dayOfMonth   current month day as int
     * @return
     */
    private static void handleCreateSheet(HSSFWorkbook workbook, String currentMonth, int dayOfMonth) {
        try {
            //return system username
            String userSystem = System.getProperty("user.name");
            String osName = System.getProperty("os.name").toLowerCase();
            String filePath = "";
            if (osName.contains("windows")) {
                CreateDirToWindows createDirToWindows = new CreateDirToWindows();
                filePath = createDirToWindows.createDirToSheet(currentMonth, userSystem);
            } else if (osName.contains("linux")) {
                CreateDirToLinux createDirToLinux = new CreateDirToLinux();
                filePath = createDirToLinux.createDirToSheet(currentMonth, userSystem);
            }

            //creating sheet to current day
            FileOutputStream out = new FileOutputStream(filePath + "Dia_" + dayOfMonth + ".xls");
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

    @Override
    public void createSheet(List<Student> students) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        String currentMonth = LocalDate.now().getMonth().toString();
        int dayOfMonth = LocalDate.now().getDayOfMonth();
        HSSFSheet sheetAlunos = workbook.createSheet(dayOfMonth + " " + currentMonth + " - PRESENÇA");

        createHeadersOfColumns(sheetAlunos);
        createColumnsWithFields(students, sheetAlunos);
        handleCreateSheet(workbook, currentMonth, dayOfMonth);
    }

    @Override
    public void createSheet(List<Student> students, LocalDate localDate) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        String currentMonth = localDate.getMonth().toString();
        int dayOfMonth = localDate.getDayOfMonth();
        HSSFSheet sheetAlunos = workbook.createSheet(dayOfMonth + " " + currentMonth + " - PRESENÇA");

        createHeadersOfColumns(sheetAlunos);
        createColumnsWithFields(students, sheetAlunos);
        handleCreateSheet(workbook, currentMonth, dayOfMonth);
    }

}
