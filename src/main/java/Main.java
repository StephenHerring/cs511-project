import implementation.NaiveImplementation;

public class Main {

    public static void main(String[] args) {
        NaiveImplementation naiveImplementation = new NaiveImplementation();
        double naiveTime = (double) naiveImplementation.timeExecution() / 1000000000.0;;
        System.out.println("Naive implementation took: " + naiveTime +
                " seconds inserting " + naiveImplementation.getTotalElements() + " rows");
    }
}
