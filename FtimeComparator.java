import java.util.Comparator;

public class FtimeComparator implements Comparator<flight> {
    @Override
    public int compare(flight o1, flight o2) {
        return Integer.compare(o1.admission_time, o2.admission_time);
    }
}
