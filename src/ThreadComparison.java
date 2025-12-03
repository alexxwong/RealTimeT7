
public class ThreadComparison {

    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 10;

        //normal threads
        Thread[] normalThreads = new Thread[numberOfThreads];
        long startTimeNormal = System.nanoTime();

        for (int i = 0; i < numberOfThreads; i++) {
            normalThreads[i] = new NormalThread();
            normalThreads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            normalThreads[i].join();
        }

        long endTimeNormal = System.nanoTime();
        double durationNormalSeconds = (endTimeNormal - startTimeNormal) / 1_000_000_000.0;


        //synchronized threads
        Thread[] syncThreads = new Thread[numberOfThreads];
        long startTimeSync = System.nanoTime();

        for (int i = 0; i < numberOfThreads; i++) {
            syncThreads[i] = new SynchronizedThread();
            syncThreads[i].start();
        }


        for (int i = 0; i < numberOfThreads; i++) {
            syncThreads[i].join();
        }

        long endTimeSync = System.nanoTime();
        double durationSyncSeconds = (endTimeSync - startTimeSync) / 1_000_000_000.0;



        System.out.printf("Normal thread = %.8f seconds%n", durationNormalSeconds);
        System.out.printf("Synchronized thread = %.8f seconds%n", durationSyncSeconds);
    }
}


class NormalThread extends Thread {
    public static int counter = 0;

    @Override
    public void run() {

        for (int i = 0; i < 1000; i++) {
            counter++;
        }
    }
}

class SynchronizedThread extends Thread {
    public static int counter = 0;
    private static final Object lock = new Object();

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {

            synchronized (lock) {
                counter++;
            }
        }
    }
}