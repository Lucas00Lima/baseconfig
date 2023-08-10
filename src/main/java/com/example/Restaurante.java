package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Restaurante {
    private static String DATABASE = "db000";
    private static String BACKUP_LOCATION_RES = "C:\\SomaPDV\\Bancos\\dbrestaurante.sql";
    private static String BACKUP_LOCATION_SER = "C:\\SomaPDV\\Bancos\\dbservico.sql";
    private static String MYSQL_PATH_DUMP = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump.exe";
    private static String MYSQL_PATH = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql.exe";
    private static String root = "root";
    private static String password = "@soma+";

    public void restaurante() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(MYSQL_PATH_DUMP, "--user=" + root,
                    "--password=" + password, DATABASE, "-r", BACKUP_LOCATION_SER);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Backup concluído");
            } else {
                System.out.println("Deu algo de errado com o seu backup");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        try {
            ProcessBuilder dropProcessBuilder = new ProcessBuilder(MYSQL_PATH, "--user=" + root,
                    "--password=" + password, "--execute=DROP DATABASE " + DATABASE);
            Process dropProcess = dropProcessBuilder.start();
            int dropExitCode = dropProcess.waitFor();
            if (dropExitCode == 0) {
                System.out.println("Drop concluído");
            } else {
                System.out.println("Deu algo de errado com o seu drop");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        try {
            ProcessBuilder createProcessBuilder = new ProcessBuilder(MYSQL_PATH, "--user=" + root,
                    "--password=" + password, "--execute=CREATE DATABASE " + DATABASE);
            Process creaProcess = createProcessBuilder.start();
            int createExitCode = creaProcess.waitFor();
            if (createExitCode == 0) {
                System.out.println("Create concluído");
            } else {
                System.out.println("Deu algo de errado com o seu Create");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        try {
            String content = new String(Files.readAllBytes(Paths.get(BACKUP_LOCATION_RES)));
            ProcessBuilder insertBuilder = new ProcessBuilder(MYSQL_PATH, "--user=" + root, "--password=" + password,
                    DATABASE);
            Process insertProcess = insertBuilder.start();
            insertProcess.getOutputStream().write(content.getBytes());
            insertProcess.getOutputStream().close();
            int insertExitCode = insertProcess.waitFor();
            if (insertExitCode == 0) {
                System.out.println("Insert concluído");
            } else {
                System.out.println("Deu algo de errado com o seu Insert");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void renameFiles() {
        String filePath = "C:\\SomaPDV\\customization000.txt";
        String newFileName = "customizationServico.txt";
        File file = new File(filePath);
        File newFile = new File(file.getParent(), newFileName);
        boolean success = file.renameTo(newFile);

        String filePath1 = "C:\\SomaPDV\\customizationRestaurante.txt";
        String newFileName1 = "customization000.txt";
        File file1 = new File(filePath1);
        File newFile1 = new File(file1.getParent(), newFileName1);
        boolean success1 = file1.renameTo(newFile1);
        if (success && success1) {
            System.out.println("Importe de custom concluido");
        } else {
            System.out.println("Houve algum erro no seu importe de Custom");
        }
    }

    public boolean exist() {
        String filePath = "C:\\SomaPDV\\customizationServico.txt";
        Path path = Paths.get(filePath);
        boolean existe = Files.exists(path);
        if (existe == true) {
            return true;
        }
        return false;
    }
}
