import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static Vector<Long> extractNumbers(String line, int begin, int end){
        Vector<Long> out = new Vector<>();
        line+=" ";
        String temp = "";
        for(int i = begin; i <= end; i++){
            if(line.charAt(i) == ' ') {
                if (!temp.isEmpty()) {
                    out.add(Long.valueOf(temp));
                }
                temp = "";
            }
            else temp+=line.charAt(i);
        }
        return out;
    }

    public static Vector<Vector<Vector<Long>>> load(File file){
        Vector<Vector<Vector<Long>>> out = new Vector<>();
        Scanner in;
        try{
            in = new Scanner(file);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return out;
        }

        int currentMap = 0;
        while(in.hasNextLine()){
            String currentLine = in.nextLine();
            if(currentLine.contains(":")){
                out.add(new Vector<>());
                currentMap++;
            }
            else if(currentLine.charAt(0)>='0'){
                while(!currentLine.isEmpty()){
                    out.get(currentMap-1).add(extractNumbers(currentLine,0,currentLine.length()));
                    if(!in.hasNextLine()) break;
                    else currentLine=in.nextLine();
                }
            }
        }
        return out;
    }

    public static void main(String args[]){
    File file = new File(args[0]);
        Vector<Vector<Vector<Long>>> maps = load(file);
        Vector<Long> seeds = maps.get(0).get(0);
        maps.remove(0);
        Navigator x = new Navigator(seeds, maps);
        long start = System.currentTimeMillis();
        System.out.println(x.findMinimumLocation());
        long end = System.currentTimeMillis();
        long runtime = end-start;
        System.out.println(runtime/1000);
    }
}

