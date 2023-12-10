import java.util.Vector;
import java.util.Scanner;
import java.io.File;
public class Main {
    public static Vector<Integer> extractNumbers(String line, int begin, int end){
        Vector<Integer> out = new Vector<>();
        line+=" ";
        String temp = "";
        for(int i = begin; i <= end; i++){
            if(line.charAt(i) == ' ') {
                if (!temp.isEmpty()) {
                    out.add(Integer.valueOf(temp));
                }
                temp = "";
            }
            else temp+=line.charAt(i);
        }
        return out;
    }
    public static Vector<Vector<Integer>> extract(File file){
        try{
            Scanner in = new Scanner(file);
            Vector<Vector<Integer>> races = new Vector<>();
            while(in.hasNextLine()){
                String currentLine = in.nextLine();
                int start = currentLine.indexOf(':');
                Vector<Integer> numbers = extractNumbers(currentLine, start+1, currentLine.length());
                races.add(numbers);
            }
            return races;
        } catch (Exception e) {
            System.out.println(e);
        }
        return new Vector<>();
    }

    public static void main(String[] args){
        File file = new File(args[0]);
        Vector<Vector<Integer>> races = extract(file);
        Marginalizer solver = new Marginalizer(races.get(0), races.get(1));
        System.out.print("Part 1: ");
        System.out.println(solver.solve());
        System.out.println("----");
        System.out.print("Part 2: ");
        System.out.println(solver.solve2());

    }
}
