import java.awt.font.NumericShaper;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //List<String> list = Arrays.asList("agdf","dfg","srhd","adfgsdf");
        //int max = 7;
        //System.out.println(list.stream().reduce((acc, text) -> String.format("%s, %s", acc, text)).orElse(""));
        new FileService("service").fileIndex(Paths.get("./assets"));
    }
}