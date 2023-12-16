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

    public static void main(String[] args){
        Vector<String> picture = expand(load(new File(args[0])));
        Vector<int[]> galaxyList = findGalaxies(picture);

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
        System.out.println(out);
    }
}
