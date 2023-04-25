package io.github.ValterGabriell.FrequenciaAlunos.domain.sheet;

import java.io.File;

public class CreateDirToLinux extends CreateDir {
    @Override
    public String createDirToSheet(String currentMonth, String userSystem) {
        //creating dir on desktop
        String filePath = "/home/" + userSystem + "/Área de Trabalho/" + currentMonth + " - planilha/";
        File directory = new File(filePath);
        directory.mkdirs();
        return filePath;
    }
}
