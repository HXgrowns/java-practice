import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

        File file = new File("test.txt");
        System.out.println(file);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("hello,world");
        }
    }

}
