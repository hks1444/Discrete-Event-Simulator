import java.util.*;

public class ATC {
 public String name;
 public flight current_flight;
 public Queue<flight> readyQueue2 = new LinkedList<>();
 public PriorityQueue<flight> prereadyQueue = new PriorityQueue<>(new FlightComparator());
 public ArrayList<flight> waiting = new ArrayList<>();
 public ATC(String name){
   this.name =name;
 }

}
