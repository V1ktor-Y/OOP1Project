package util.WriteToFile;


import util.ErrorLog.ErrorLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    private File filePath;
    private FileWriter fileWriter;
    private boolean append;
    private ErrorLogger logger;

    public WriteToFile(String path, boolean append){
        this.append = append;
        filePath = new File(path);
        logger = new ErrorLogger();
        try {
            fileWriter = new FileWriter(filePath, append);
        }
        catch (IOException e){
            logger.log(e);
            e.printStackTrace();
        }
    }

    public void write(String s){
        try{
            fileWriter.write(s);
            fileWriter.close();
        }catch (IOException e){
            logger.log(e);
        }
    }

    public void changeDestination(String newPath){
        try {
            fileWriter = new FileWriter(newPath, append);
        }catch (IOException e) {
            logger.log(e);
        }
    }

    public void changeAppendMethod(boolean append){
        this.append = append;
    }
}
