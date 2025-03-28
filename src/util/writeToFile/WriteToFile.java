package util.writeToFile;


import util.errorLog.ErrorLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    public static void write(String path, boolean append, String s) {
        try {
            FileWriter fileWriter = new FileWriter(new File(path), append);
            fileWriter.write(s);
            fileWriter.close();
        } catch (IOException e) {
            ErrorLogger.log(e);
        }
    }
}

