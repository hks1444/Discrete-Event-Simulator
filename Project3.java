import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Project3 {
    public static void main(String[] args) throws IOException {
        HashMap<String,ACC> accs = new HashMap<>();
        File infile = new File(args[0]);
        File outfile = new File(args[1]);
        Scanner input = new Scanner(infile);
        FileWriter output = new FileWriter(outfile);
        int a = input.nextInt();
        int f = input.nextInt();
        for(int i=0;i<a;i++){
            String name = input.next();
            String[] aps = input.nextLine().strip().split(" ");
            accs.put(name,new ACC(name,aps));
        }
        for(int j = 0;j<f;j++){
            int adm = input.nextInt();
            String flname = input.next();
            String acc = input.next();
            String dep = input.next();
            String land = input.next();
            String[] vals = input.nextLine().strip().split(" ");
            int[] times= new int[vals.length];
            for(int k=0;k<vals.length;k++){
                times[k] = Integer.parseInt(vals[k]);
            }
            accs.get(acc).addFlight(adm,flname,acc,dep,land,times);
        }
        for(ACC x:accs.values()){
            while(!x.isEmpty()){
                x.the_function();
            }
        }
        for(ACC x:accs.values()){
            StringBuilder out =new StringBuilder();
            out.append(x.code).append(" ");
            out.append(x.time);
            for(int y: x.table.keySet().stream().sorted().toList()){
                out.append(" ").append(x.table.get(y).name).append(String.format("%03d", y));
            }
            out.append("\n");
            output.write(out.toString());
        }
        input.close();
        output.close();
    }
}