import java.util.Vector;
import java.util.Scanner;
import java.io.File;
public class Main {
    public static Vector<Vector<Integer>> extract(File file){
        Scanner in;
        try{
            in = new Scanner(file);
        } catch (Exception e) {
            System.out.println(e);
        }

        Vector<Vector<Integer>> races = new Vector<>();
        while(in.hasNextLine()){
            String currentLine = in.nextLine();
            Vector<Integer> numbers = new Vector<Integer>();
            for(int i = 0; i < currentLine.length(); i++){

            }
            races.add(numbers);
        }
    }

    public static void main(String[] args){

    }
}
