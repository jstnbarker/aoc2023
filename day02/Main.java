import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static boolean gameIsSus(String game){
        int blueMax = 14;
        int greenMax = 13;
        int redMax = 12;

        String temp = "";
        for(int i = game.indexOf(':'); i < game.length(); i++){
            char c = game.charAt(i);
            if (c < '0') temp = "";
            else if (c <= '9'){
                temp+=c;
                int value = Integer.parseInt(temp);
                switch(game.charAt(i+2)){
                    case 'r':
                        if(value>redMax)
                            return true;
                        i+=5;
                        break;
                    case 'b':
                        if(value>blueMax)
                            return true;
                        i+=6;
                        break;
                    case 'g':
                        if(value>greenMax)
                            return true;
                        i+=7;
                        break;
                }
            }
        }
        return false;
    }

    public static int[] minCubes(String game){
        // red green blue
        int[] out = {0,0,0};

        String temp = "";
        for(int i = game.indexOf(':'); i < game.length(); i++){
            char c = game.charAt(i);
            if (c < '0') temp = "";
            else if (c <= '9'){
                temp+=c;
                int value = Integer.parseInt(temp);
                switch(game.charAt(i+2)){
                    case 'r':
                        if(value>out[0])
                            out[0] = value;
                        i+=5; // don't waste time checking remaining letters/spaces
                        break;
                    case 'g':
                        if(value>out[1])
                            out[1] = value;
                        i+=7;
                        break;
                    case 'b':
                        if(value>out[2])
                            out[2] = value;
                        i+=6;
                        break;
                }
            }
        }
        return out;
    }

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        try{
            in=new Scanner(new File(args[0]));
            int out = 0;
            int part2 = 0;
            for(int gameNumber = 1; in.hasNextLine(); gameNumber++){
                String currentLine = in.nextLine();
                if(!gameIsSus(currentLine)) {
                    out += gameNumber;
                }
                int product = 1;
                for(int val : minCubes(currentLine)){
                    product*=val;
                }
                part2+=product;
            }
            System.out.println("Part 1: " + out);
            System.out.println("Part 2: " + part2);
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
    }
}
