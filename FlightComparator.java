import java.util.Comparator;

public class FlightComparator implements Comparator<flight> {
    public int compare(flight o1, flight o2) {
        if(o1.priority<o2.priority){
            return -1;
        }else if(o1.priority>o2.priority){
            return 1;
        }else {
            return o1.f_code.compareTo(o2.f_code);
        }
        }
    }


