import java.util.Vector;

public class Marginalizer {

    Vector<Integer> distances;
    Vector<Integer> times;

    Marginalizer(Vector<Integer> times, Vector<Integer> distances){
        this.times=times;
        this.distances=distances;
    }
}
