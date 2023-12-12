import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.Vector;

public class Main {
    public static Vector<SpringRow> load(File file){
        Vector<SpringRow> out = new Vector<>();
        try{
            Scanner in = new Scanner(file);
            while(in.hasNextLine()){
                String currentLine = in.nextLine();
                int i = 0;
                for(; currentLine.charAt(i) != ' '; i++);
                String row = currentLine.substring(0,i);
                System.out.println(row);
                String[] temp = currentLine.splitWithDelimiters(",",10);
                for(String numer : temp) System.out.print(numer);
                System.out.println();

            }
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        return out;
    }

    public static void main(String[] args){
        load(new File(args[0]));
    }
}
