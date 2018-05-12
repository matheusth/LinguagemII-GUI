package com.company.com.company.file;

import java.io.*;

public class FileManipulation {

    public static void write(String path, String write) throws IOException {
        File f = new File(path);
        String writer = "";
        if (f.exists()) {
            writer += readFile(path);
        }
        writer += write;
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(f.getPath()));
        buffWrite.append(writer + "\n");
        buffWrite.close();

    }

    public static void delete(String path, String cpf) throws IOException {


        String buffer = "";
        String aux;
        String[] lines = readFile(path).split("\n");

        for (String line : lines) {
            aux = line.split(" | ")[0];
            if (!aux.equals(cpf))
                buffer += line + "\n";
        }
        File originFile = new File(path);
        FileWriter fw = new FileWriter(originFile);
        fw.write(buffer);

        fw.close();
    }

    public static String readFile(String path) throws IOException {
        File originFile = new File(path);
        if (!originFile.exists()) {
            return "";
        }
        FileReader fis = new FileReader(originFile);
        BufferedReader buffReader = new BufferedReader(fis);
        String buffer = "";
        String line = "";
        String bigString = "";

        while ((line = buffReader.readLine()) != null) {
            buffer += line + "\n";
        }
        fis.close();
        return buffer;
    }


}
