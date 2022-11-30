public class flight {
    public int enter=0;
    public int progress=0;
    public int priority = 0;
    public int current = 0;
    public int[] op_time;
    public int admission_time;
    public String f_code;
    public String acc;
    public String dep;
    public String land;
    public flight(int adm_time, String f_code,String acc,String dep,String land,int[] times){
        this.admission_time = adm_time;
        this.f_code = f_code;
        this.acc = acc;
        this.dep = dep;
        this.land = land;
        this.op_time = times;
    }
}
