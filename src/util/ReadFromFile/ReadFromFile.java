package util.ReadFromFile;


import util.ErrorLog.ErrorLogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFromFile {

    public static String getAll(String filePath){
        String content = "";
        try{
            Path path = new File(filePath).toPath();
            content =  Files.readString(path);
        }catch (IOException e){
            ErrorLogger.log(e);
        }

        return content;
    }

}
