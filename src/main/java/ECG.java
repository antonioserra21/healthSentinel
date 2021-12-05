import java.io.*;
import java.util.ArrayList;

public class ECG {
    ArrayList<Double> samples = new ArrayList<>();
    ArrayList<Integer> peaks;

    ECG(String filename){
        //int counter = 0;
        FileInputStream fin;
        try {
            fin = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open the file");
            return;
        }
        try {
            int i;
            StringBuilder str = new StringBuilder();
            double temp;
            do {
                i = fin.read();
                if (((char) i == ',') | (i == -1) ) {
                    temp = Double.parseDouble(str.toString());
                    //System.out.println(str);
                    str = new StringBuilder();
                    samples.add(temp);
                    //counter += 1;
                    continue;
                }
                str.append((char) i);
            }
            while (i != -1);
        } catch (IOException e) {
            System.out.println("Error reading the file");
        }
        //al.forEach(System.out::println);
        //System.out.println("\nThe number of samples is:");
        //System.out.println(counter);
    }

    void peaks(){
        //System.out.println("\nPeaks of this ECG are found at the sample position(s):");
        peaks = new ArrayList<>();
        for (int c = 0; c < samples.size(); c++) {
            if (c == 0 && samples.get(c) >= samples.get(c+1)){
                peaks.add(c);
                continue;
            }
            if (samples.get(c) >= samples.get(c-1) && samples.get(c) >= samples.get(c+1) && samples.get(c) > 0.7){
                peaks.add(c);
            }
        }
        peaks.forEach(System.out::println);
    }
    int hr(){
        peaks();
        int HR = peaks.size()*6;
        System.out.println("\nThe heart rate is: " + HR);
        return HR;
    }
}
