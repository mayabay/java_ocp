package pkg_9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesTest1 {

    public static void main(String[] args) throws IOException {
        FilesTest1 ft1 = new FilesTest1();
        ft1.test1();    
    }

    private void test1() throws IOException {
        Path p = Paths.get(".");
        Files.list(p)
            .forEach(System.out::println);
    }

}