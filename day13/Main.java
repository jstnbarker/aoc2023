import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
public class Main {
    public static Vector<MirrorMap> load(File file){
        try{
            Scanner in = new Scanner(file);
            Vector<String> map = new Vector<>();
            Vector<MirrorMap> out = new Vector<>();
            while(in.hasNextLine()){
                String currentLine = in.nextLine();
                if(currentLine.isEmpty()){
                    out.add(new MirrorMap(map));
                    map = new Vector<>();
                }
                else map.add(currentLine);
            }
            return out;
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return new Vector<>();
    }

    // 29835 too low

    public static void main(String[] args){
        Vector<MirrorMap> x = load(new File(args[0]));
        int out = 0;
        for(MirrorMap m : x){
            out+=m.solve();
        }
        System.out.println(out);
    }
}
