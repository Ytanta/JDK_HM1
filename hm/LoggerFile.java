package hm;

import java.io.*;
import java.io.IOException;

public class LoggerFile {

    //запись файла
    public static void writeLog(String text) throws IOException {

        try(FileWriter writer = new FileWriter("logger.txt", true))
        {
            writer.write(text);
            writer.flush();
        }
    }

    public static String readLog() {
//        try (FileReader reader = new FileReader("logger.txt")){
//            System.out.println(reader.read());
//        }
        String line;
        StringBuilder content = new StringBuilder();

        String filePath = "logger.txt";
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}