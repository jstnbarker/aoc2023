import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static Vector<Node> load(File in){
        Scanner fis;
        try{
            fis = new Scanner(in);
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        while(fis.hasNextLine()){

        }

    }
    public static void main(String args[]){
        Vector<Node> intersections = load(new File(args[0]));
    }
}