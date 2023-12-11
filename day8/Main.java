import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static Navigator load(File in){
        Scanner fis;
        Vector<Node> forks = new Vector<>();
        String path = "";
        try{
            fis = new Scanner(in);
            path = fis.nextLine(); // get path
            fis.nextLine(); // eat blank line
            while(fis.hasNextLine()){
                String currentLine = fis.nextLine();
                forks.add(new Node(
                        currentLine.substring(0,3), // id
                        currentLine.substring(7,10), // left
                        currentLine.substring(12,15) // right
                ));
            }
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        return new Navigator(forks,path);
    }
    public static void main(String args[]){
        Navigator nav = load(new File(args[0]));
        //System.out.println(nav.length());
        long start = System.currentTimeMillis();
        System.out.println(nav.part2());
        System.out.println((System.currentTimeMillis()-start)/1000);
    }
}