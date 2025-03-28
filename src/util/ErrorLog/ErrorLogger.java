package util.ErrorLog;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class ErrorLogger {
    public static void log(Exception e) {
        try {
            FileWriter errorWriter = new FileWriter(".\\LocalErros.txt", true);
            errorWriter.write("[" + Calendar.getInstance().getTime() +"]" + e.getMessage() + "\n");
            errorWriter.close();
        } catch (IOException ioException) {
            System.out.println("Failed to Log error");
            ioException.printStackTrace();
        }
    }
}
