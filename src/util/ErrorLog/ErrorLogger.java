package util.ErrorLog;


import java.io.FileWriter;
import java.io.IOException;

public class ErrorLogger {
    public void log(Exception e) {
        try {
            FileWriter errorWriter = new FileWriter(".\\LocalErros.txt", true);
            errorWriter.write("Error message:" + e.getMessage() + "\n");
            errorWriter.close();
        } catch (IOException ioException) {
            System.out.println("Failed to Log error");
            ioException.printStackTrace();
        }
    }
}
