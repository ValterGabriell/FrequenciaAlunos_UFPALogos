package io.github.ValterGabriell.FrequenciaAlunos.domain.sheet;

import java.io.File;

public class CreateDirToWindows extends CreateDirAbstraction {
    public static String createDirToSheetOnLinux(String currentMonth, String userSystem) {
        //creating dir on desktop
        String filePath = "/home/" + userSystem + "/Desktop/" + currentMonth + " - planilha/";
        File directory = new File(filePath);
        directory.mkdirs();
        return filePath;
    }

    @Override
    public String createDirToSheet(String currentMonth, String userSystem) {
        //creating dir on desktop
        String filePath = "C:\\Users\\" + userSystem + "\\Desktop\\" + currentMonth + " - planilha\\";
        File directory = new File(filePath);
        directory.mkdirs();
        return filePath;
    }
}
