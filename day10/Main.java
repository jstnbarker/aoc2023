import java.io.FileNotFoundException;
import java.util.Vector;
import java.util.Scanner;
import java.io.File;
public class Main {
    public Vector<String> load(File file){
        Vector<String> out = new Vector<>();
        try{
            Scanner in = new Scanner(file);
            while(in.hasNextLine()){
                out.add(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return out;
    }


    public static void main(String[] args){

    }
}
