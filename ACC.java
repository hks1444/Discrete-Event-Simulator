import java.util.*;

public class ACC {
    public flight current_flight;
    private int end_check=0;
    public boolean isEmpty(){
        return end_check == 0;
    }
    public PriorityQueue<flight> toBeAdmitted = new PriorityQueue<>(new FtimeComparator());
    public Queue<flight> readyQueue2 = new LinkedList<>();
    public PriorityQueue<flight> prereadyQueue = new PriorityQueue<>(new FlightComparator());
    public ArrayList<flight> waiting = new ArrayList<>();
    public String code;
    public int time=0;
    private int timeslice = 30;
    public Hashtable<Integer,ATC> table= new Hashtable<>(1000,1);
    public ACC(String code,String[] airports){
        this.code = code;
        for(String ap:airports) addtoTable(ap);
    }
    public void addFlight(int adm_time, String f_code,String acc,String dep,String land,int[] times){
        end_check++;
        toBeAdmitted.add(new flight(adm_time, f_code, acc, dep, land,times));
    }
    private void addtoTable(String a){
        int code = getCode(a,0);
        int i = 0;
        boolean cont = true;
        while(cont){
        if(table.getOrDefault(code,null)!=null){
            i++;
            code = getCode(a,i);
        }else{
            cont = false;
            table.put(code,new ATC(a));
        }
        }
    }

    public void addNew(){
        while(!toBeAdmitted.isEmpty()){
            flight temp = toBeAdmitted.peek();
            if(temp.admission_time==time){
                flight bla = toBeAdmitted.poll();
                bla.enter = time;
                prereadyQueue.add(bla);
            }else{
                break;
            }
        }
    }
    public ATC findfromTable(String a){
        int code = getCode(a,0);
        int i = 0;
        boolean cont = true;
        while(cont){
            if(!table.get(code).name.equals(a)){
                i++;
                code = getCode(a,i);
            }else{
                cont = false;
            }
        }
        return table.get(code);
    }
    private int getCode(String a,int i){
        int temp=0;
        for(int b=0;b<a.length();b++){
            temp += a.charAt(b)*Math.pow(31,b);
        }
        return (temp+i)%1000;
    }
    public void the_function(){
        addNew();
        while(!prereadyQueue.isEmpty()){
            flight temp = prereadyQueue.poll();
            temp.enter = time;
            readyQueue2.add(temp);
        }
        if(current_flight!=null){
            current_flight.progress++;
            timeslice--;
        }else{
            if(!readyQueue2.isEmpty()){
                current_flight = readyQueue2.poll();
             //   System.out.println(current_flight.enter+" | "+time+" | "+current_flight.f_code+" | "+this.code+" Running | "+ (current_flight.op_time[current_flight.current]- current_flight.progress));
                current_flight.progress++;
                timeslice--;
            }
        }
        for(flight x:waiting){
            x.progress++;
        }
        for(ATC y:table.values()){
            while(!y.prereadyQueue.isEmpty()){
                flight temp = y.prereadyQueue.poll();
                temp.enter = time;
                y.readyQueue2.add(temp);
            }
            if(y.current_flight!=null){
                y.current_flight.progress++;
            }else{
                if(!y.readyQueue2.isEmpty()){
                    y.current_flight = y.readyQueue2.poll();
                 //   System.out.println(y.current_flight.enter+" | "+time+" | "+y.current_flight.f_code+" | "+y.name+" Running | "+ y.current_flight.op_time[y.current_flight.current]);
                    y.current_flight.progress++;
                }
            }
            for(flight x:y.waiting){
                x.progress++;
            }
        }
        //update progress
        time++;
        //move flights
        if(current_flight!=null){
            if(current_flight.progress>=current_flight.op_time[current_flight.current]){
                timeslice=30;
                current_flight.priority=0;
                current_flight.progress=0;
                current_flight.current++;
                if(current_flight.current==1|current_flight.current==11){
                    current_flight.enter=time;
                   // System.out.println(current_flight.enter+" | "+time+" | "+current_flight.f_code+" | "+this.code+" Waiting | "+ current_flight.op_time[current_flight.current]);
                    waiting.add(current_flight);
                }else if(current_flight.current==3){
                    findfromTable(current_flight.dep).prereadyQueue.add(current_flight);
                }else  if(current_flight.current==13){
                    findfromTable(current_flight.land).prereadyQueue.add(current_flight);
                }else{
                    current_flight.enter=time;
                  //  System.out.println(current_flight.enter+" | "+time+" | "+current_flight.f_code+" | Finished | "+ 0);
                    end_check--;
                }
                current_flight = null;
            }else if(timeslice==0){
                timeslice=30;
                current_flight.priority=1;
                prereadyQueue.add(current_flight);
                current_flight = null;
            }
        }
        for(ATC y:table.values()){
            if(y.current_flight!=null) {
                if(y.current_flight.op_time[y.current_flight.current]<=y.current_flight.progress){
                    y.current_flight.progress=0;
                    y.current_flight.current++;
                    if(y.current_flight.current==20|y.current_flight.current==10){
                        prereadyQueue.add(y.current_flight);
                        }else{
                        y.current_flight.enter=time;
                  //    System.out.println(y.current_flight.enter+" | "+time+" | "+y.current_flight.f_code+" | "+y.name+" Waiting | "+ y.current_flight.op_time[y.current_flight.current]);
                        y.waiting.add(y.current_flight);
                    }
                    y.current_flight=null;
                }
            }
            Iterator<flight> x = y.waiting.iterator();
            while(x.hasNext()){
                flight temp = x.next();
                if(temp.progress>=temp.op_time[temp.current]){
                    temp.progress=0;
                    temp.current++;
                    y.prereadyQueue.add(temp);
                    x.remove();
                }
            }
        }
        Iterator<flight> x2 = waiting.iterator();
        while(x2.hasNext()){
            flight temp1 = x2.next();
            if(temp1.progress>=temp1.op_time[temp1.current]){
                temp1.progress=0;
                temp1.current++;
                prereadyQueue.add(temp1);
                x2.remove();
            }
        }
    }
}
