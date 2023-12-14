import java.util.Vector;

public class MirrorMap {
    Vector<String> map;
    Vector<String> rotate = new Vector<>();
    public MirrorMap(Vector<String> map){
        this.map=map;
        // select column
        for(int i = 0; i != map.get(0).length(); i++){
            String temp = "";
            for(int j = 0; j != map.size(); j++){
                temp=temp+map.get(j).charAt(i);
            }
            rotate.add(temp);
        }
    }

    private char getChar(int x, int y){
        return map.get(y).charAt(x);
    }
    // mirror is on y axis if returned value > 100
    // otherwise mirror is on x axis
    public int findMirror(){
        int out = 0;
        // check for vertical mirror
        // select column for test
        for(int i = 0; i < map.size()-1; i++){
            if(map.get(i).equals(map.get(i+1))){

            }
        }
        for(int i = 0; i < rotate.size()-1; i++){
            if(rotate.get(i).equals(rotate.get(i+1))) {
            }
        }

        // check for horizontal mirror
        return out;
    }
}
