import java.util.HashMap;
import java.util.Vector;

public class Plumber{

    Vector<String> pipeMap;

    public Plumber(Vector<String> n){
        this.pipeMap=n;
    }

    private int[] findStart(){
        int[] s = new int[2];
        for(int j = 0; j != pipeMap.size(); j++){
            String row = pipeMap.get(j);
            for(int i = 0; i != row.length(); i++){
                if(row.charAt(i)=='S'){
                    s[0] = i;
                    s[1] = j;
                }
            }
        }
        return s;
    }

    private char getChar(int[] position){
        return pipeMap.get(position[1]).charAt(position[0]);
    }

    public int length(){
        int[] was;
        int[] am;
        int[] start = findStart();
        was = start.clone();
        am = findLegalPipe(start);
        int length = 1;

        while(getChar(am) != 'S'){
            int[] temp = was.clone();
            was=am.clone();
            am=next(temp,am);
            length+=1;
        }
        return length;
    }

    private int[] next(int[] previous, int[] current){
        int[][] out = {current.clone(), current.clone()};

        switch(getChar(current)){
            case '-':
                out[0][0]=current[0]-1;
                out[1][0]=current[0]+1;
                break;
            case '|':
                out[0][1]=current[1]-1;
                out[1][1]=current[1]+1;
                break;
            case 'F':
                out[0][0]=current[0]+1;
                out[1][1]=current[1]+1;
                break;
            case 'L':
                out[0][0]=current[0]+1;
                out[1][1]=current[1]-1;
                break;
            case '7':
                out[0][0]=current[0]-1;
                out[1][1]=current[1]+1;
                break;
            case 'J':
                out[0][0]=current[0]-1;
                out[1][1]=current[1]-1;
                break;
        }

        if(out[0][0]==previous[0]  && out[0][1] == previous[1])
            return out[1];
        return out[0];
    }

    // returns position of first found connecting pipe
    private int[] findLegalPipe(int[] start){
        int[][] neighbors = {
                {start[0],start[1]-1}, // north
                {start[0]+1, start[1]}, // east
                {start[0], start[1]+1}, // south
                {start[0]+1, start[1]} // west
        };
        char[][] legals = {
                { '|', 'F', '7' }, // north
                { '7', '-', 'J' }, // east
                { 'L', '|', 'J' }, // south
                { 'L', '-', 'F' } // west
        };

        for(int i = 0; i < 4; i++)
            if(validator(legals[i],neighbors[i]))
                return neighbors[i];
        return new int[0];
    }

    private boolean validator(char[] legals, int[] position){
        for(char c : legals)
            if(c == getChar(position))
                return true;
        return false;
    }
}

