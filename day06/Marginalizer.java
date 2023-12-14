import java.util.Vector;

public class Marginalizer {

    Vector<Integer> distances;
    Vector<Integer> times;

    Marginalizer(Vector<Integer> times, Vector<Integer> distances){
        this.times=times;
        this.distances=distances;
    }

    // returns first winning race
    private long holdTime(long start, long end, long maxTime, long recordDistance){
        for(long j = start; ;) {
            if (j * (maxTime-j) > recordDistance) {
                return j;
            }
            if (start > end) j--;
            else j++;
        }
    }

    public long solve(){
        long out = 1;
        for(int i = 0; i != times.size(); i++){
            long x = 0;
            long min = holdTime(0, times.get(i), times.get(i), distances.get(i));
            long max = holdTime(times.get(i), 0, times.get(i), distances.get(i));
            x = max-min+1;
            out*=x;
        }
        return out;
    }

    public long solve2(){

        String temp = "";
        for(int value : times){
            temp+=String.valueOf(value);
        }
        long maxTime = Long.parseLong(temp);
        temp="";
        for(int value : distances){
            temp+=String.valueOf(value);
        }
        long recordDistance = Long.parseLong(temp);

        long min = holdTime(0, maxTime, maxTime, recordDistance);
        long max = holdTime(maxTime, 0, maxTime, recordDistance);

        return max-min+1;
    }
}
