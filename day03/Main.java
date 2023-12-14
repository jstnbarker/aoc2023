import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File input = new File("/home/jstn/workspace/advent2023/gear-ratios/src/input");

        Engine engine = new Engine(new File("/home/jstn/workspace/advent2023/gear-ratios/src/given"));
        engine = new Engine(input);
    }
}