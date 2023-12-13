import java.util.Vector;

public class Plumber{

    Vector<String> pipeMap;

    public Plumber(Vector<String> n){
        this.pipeMap=n;
    }
    public int solve (Vector<String> n){
        // find start
        int[] s = new int[2];
        for(int j = 0; j != n.size(); j++){
            String row = n.get(j);
            for(int i = 0; i != row.length(); i++){
                if(row.charAt(i)=='S'){
                    s[0] = i;
                    s[1] = j;
                }
            }
        }

        int length = 1;
        int[] pos = findLegalPipe(s);

        while(pos != s){

        }
        return -1;
    }

    // returns position of first found connecting pipe
    private int[] findLegalPipe(int[] start){


    }
}
