public class healthSentinel {
    public static void main(String[] args) {
        ECG signal = new ECG("ECGCSV.txt");
        signal.peaks();
    }
}
