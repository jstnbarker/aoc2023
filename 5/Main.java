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

    public static long hashSeed(long z, Vector<Vector<Long>> map){
        for(int i = 0; i != map.size(); i++){
            Vector<Long> temp = map.get(i);
            long drs = temp.get(0);
            long srs = temp.get(1);
            long len = temp.get(2);

            if(srs <= z && z < srs+len) return drs+(z-srs);
        }
        return z;
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

    public static long calculateRange(long x, long offset, Vector<Vector<Vector<Long>>> maps){
        long minimum = Long.MAX_VALUE;
        for(long i = x; i < x+offset; i++){
            if(i%10000000==0) {
                System.out.println(i + " / " + (x+offset));
            }
            for(Vector<Vector<Long>> map : maps){
                long temp = hashSeed(i,map);
                if(temp<minimum) minimum = temp;
            }
        }
        return minimum;
    }

    public static void main(String args[]){
    File file = new File("/home/jstn/workspace/advent2023/seed/input");
        Vector<Vector<Vector<Long>>> maps = load(file);
        Vector<Long> seeds = maps.get(0).get(0);
        maps.remove(0);

        System.out.println(calculateRange(seeds.get(0), seeds.get(1), maps));
    }
}

