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

    public int solve(){
        int hor = findMirror(this.map);
        if(hor != -1){
            return hor*100;
        }
        int vert = findMirror(this.rotate);
        if(vert != -1){
            return vert;
        }
        return -1;
    }

    @Override
    public String toString() {
        String out = "";
        for(String line : map){
            out+=line+"\n";
        }
        return out;
    }

    private char getChar(int x, int y){
        return map.get(y).charAt(x);
    }
    // mirror is on y axis if returned value > 100
    // otherwise mirror is on x axis
    // returns -1 if no mirror index found
    public int findMirror(Vector<String> n){
        for(int i = 0; i < n.size()-1; i++){
            if(n.get(i).equals(n.get(i+1))){
                for(int offset = 0; n.get(i-offset).equals(n.get(i+offset+1)); offset++){
                    // i-offset >= 0 && offset+i+1 < n.size()
                    // n.get(i-offset).equals(n.get(i+offset+1)
                    if(i-offset == 0 || offset+i+1 == n.size()-1){
                        return i+1;
                    }
                }
            }
        }
        return -1;
    }
}
