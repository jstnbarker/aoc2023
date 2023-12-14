import java.util.Vector;


public class Navigator {
    Vector<Vector<Vector<Long>>> maps;
    Vector<Long> seeds;

    Navigator(Vector<Long> seeds, Vector<Vector<Vector<Long>>> maps){
        this.seeds = seeds;
        this.maps = maps;
    }

    public long findMinimumLocation(){
        reverseHashSeed(46);
        for(long location = 0; ; location++){
            long prospect = reverseHashSeed(location);

            for(int j = 0; j < seeds.size(); j+=2){
                long rangeStart = seeds.get(j);
                long rangeEnd = rangeStart + seeds.get(j+1);
                if(prospect >= rangeStart && prospect < rangeEnd){
                    return location;
                }
            }
        }
    }

    public long reverseHashSeed(long z){
        // Select map group in reverse order
        for(int i = maps.size()-1; i >= 0; i--){
            // select mapping in map group
            for(Vector<Long >map : maps.get(i)){
                long drs = map.get(0);
                long srs = map.get(1);
                long len = map.get(2);

                if(drs <= z && z < drs+len) {
                    z = srs+(z-drs);
                    break;
                }
            }
        }
        return z;
    }

    /*
    private long hashSeed(long z){
        for(int i = 0; i != maps.size(); i++){
            Vector<Long> temp = maps.get(i);
            long drs = temp.get(0);
            long srs = temp.get(1);
            long len = temp.get(2);

            if(srs <= z && z < srs+len) return drs+(z-srs);
        }
        return z;
    }
     */
}
