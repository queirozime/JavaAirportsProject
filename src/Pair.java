import java.util.Comparator;

public class Pair {
    public String first;
    public Double second;

    public Pair (String s, Double d) {
        this.second = d;
        this.first = s;
    }

    public Pair () {
        this.second = Double.valueOf(0);
        this.first = "";
    }

}

class PairComparator implements Comparator<Pair> {

    // Overriding compare()method of Comparator
    // for descending order of cgpa
    public int compare(Pair s1, Pair s2) {
        if (s1.second > s2.second)
            return 1;
        else if (s1.second < s2.second)
            return -1;
        return 0;
    }
}
