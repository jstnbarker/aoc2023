import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.util.Vector;

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

    // returns a vector containing vectors for both the hand and the winning numbers
    public static Vector<Vector<Integer>> getNumbers(String line){
        Vector<Vector<Integer>> numbers = new Vector<>();
        int yourHand = line.indexOf(':');
        int winners = line.indexOf('|');
        numbers.add(extractNumbers(line, yourHand+1, winners));
        numbers.add(extractNumbers(line, winners+1, line.length()));
        return numbers;
    }
    public static int cardValue(String line){
        Vector<Vector<Integer>> card = getNumbers(line);

        int value = 0;
        for(int i = 0; i < card.get(0).size(); i++){
            for(int j = 0; j < card.get(1).size(); j++){
                if(card.get(0).get(i) == card.get(1).get(j)){
                    value=value<<1;
                    if(value==0) value=1;
                }
            }
        }
        return value;
    }


    public static void main(String[] args) {
        File input = new File("/home/jstn/workspace/advent2023/scratchcards/src/cards");
        Vector<int[]> x = new Vector<>();
        try{
            Scanner in = new Scanner(input);
            int out = 0;
            while(in.hasNextLine()){
                int temp = cardValue(in.nextLine());
            }
            System.out.println(out);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}