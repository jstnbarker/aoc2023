import java.util.*;
import java.io.*;

public class Main {
    public static Vector<String> load(File file){
        Scanner in;
        Vector<String> out = new Vector<>();
        try{
            in = new Scanner(file);
            while(in.hasNextLine()){
                out.add(in.nextLine());
            }
            return out;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return out;
    }

    public static Vector<String> expand(Vector<String> galaxy){
        Vector<String> out = new Vector<>();

        // check columns
        // select column
        for(int x = 0; x < galaxy.getFirst().length(); x++){
            boolean found = true;
            // iterate through column
            for(int y = 0; y < galaxy.size(); y++){
                if(galaxy.get(y).charAt(x) == '#'){
                    found=false;
                    break;
                }
            }
            if(found){
                for(int j = 0; j != galaxy.size(); j++){
                    StringBuilder newLine = new StringBuilder();
                    String line = galaxy.get(j);
                    for(int i = 0; i < line.length(); i++){
                        if(i==x) newLine.append("..");
                        else newLine.append(line.charAt(i));
                    }
                    galaxy.set(j,newLine.toString());
                }
                x++;
            }
        }

        // check rows
        for(String line : galaxy){
            if(!line.contains("#")){
                StringBuilder temp = new StringBuilder();
                for(int i = 0; i < line.length(); i++) temp.append('.');
                out.add(temp.toString());
                out.add(temp.toString());
            } else {
                out.add(line);
            }
        }
        return out;
    }

    public static Vector<int[]> findGalaxies(Vector<String> picture){
        Vector<int[]> out = new Vector<>();
        for(int y = 0; y < picture.size(); y++){
            String line = picture.get(y);
            for(int x = 0; x < picture.getFirst().length(); x++){
                if(line.charAt(x) == '#'){
                    int[] temp = {y, x};
                    out.add(temp);
                }
            }
        }
        return out;
    }

    public static Vector<int[]> expand2(Vector<int[]> coordinates, Vector<String> map, int offset){
        for(int x = map.getFirst().length()-1; x >=0 ; x--){
            boolean found = true;
            for(int y = 0; y < map.size(); y++){
                if(map.get(y).charAt(x) == '#'){
                    found=false;
                    break;
                }
            }
            if(found){
                for(int[] coord : coordinates){
                    if(coord[1]>x) coord[1]+=offset-1;
                }
            }
        }

        // check rows
        for(int i = map.size()-1; i >= 0; i--){
            String line = map.get(i);
            if(!line.contains("#")){
                for(int[] coord : coordinates){
                    if(coord[0]>i) coord[0]+=offset-1;
                }
            }
        }
        return coordinates;
    }

    public static void main(String[] args){
        Vector<String> picture = load(new File(args[0]));
        Vector<int[]> galaxyList = findGalaxies(expand(picture));

        int out = 0;
        for(int i = 0; i < galaxyList.size(); i++){
            for (int j = i+1; j < galaxyList.size(); j++){
                int[] a = galaxyList.get(i);
                int[] b = galaxyList.get(j);
                int distance=0;
                distance+=Math.abs(b[0]-a[0]);
                distance+=Math.abs(b[1]-a[1]);
                System.out.println("Galaxy #" + (i+1) + " to galaxy #" + (j+1) + " == " + distance);
                out+=distance;
            }
        }

        // 82000210 too low... nothing was wrong you just used the example input with 1000000 instead of 10
        System.out.println("Part 1: " + out);
        Vector<String> picture2 = load(new File(args[0]));
        Vector<int[]> list2 = findGalaxies(picture2);
        long out2 =0;
        galaxyList = expand2(list2, picture2, 1000000);
        for(int i = 0; i < galaxyList.size(); i++){
            for (int j = i+1; j < galaxyList.size(); j++){
                int[] a = galaxyList.get(i);
                int[] b = galaxyList.get(j);
                int distance=0;
                distance+=Math.abs(b[0]-a[0]);
                distance+=Math.abs(b[1]-a[1]);
                System.out.println("Galaxy #" + (i+1) + " to galaxy #" + (j+1) + " == " + distance);
                out2+=distance;
            }
        }
        System.out.println("Part 2: " + out2);
    }
}
