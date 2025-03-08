package util.ReadFromFile;


import util.ErrorLog.ErrorLogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFromFile {
    private File filePath;
    ErrorLogger logger = new ErrorLogger();

    public ReadFromFile(String filePath) {
        this.filePath = new File(filePath);
    }

    public String getAll(){
        String content = "";
        try{
            Path path = filePath.toPath();
            content =  Files.readString(path);
        }catch (IOException e){
            logger.log(e);
        }

        return content;
    }

}
