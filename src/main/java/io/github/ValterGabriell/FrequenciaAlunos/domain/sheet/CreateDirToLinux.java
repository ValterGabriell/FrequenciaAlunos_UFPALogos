package io.github.ValterGabriell.FrequenciaAlunos.domain.sheet;

import java.io.File;

public class CreateDirToLinux extends CreateDirAbstraction {
    @Override
    public String createDirToSheet(String currentMonth, String userSystem) {
        //creating dir on desktop
        String filePath = "/home/" + userSystem + "/Desktop/" + currentMonth + " - planilha/";
        File directory = new File(filePath);
        directory.mkdirs();
        return filePath;
    }
}
