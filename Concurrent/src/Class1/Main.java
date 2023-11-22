package Class1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //launchCreateThreads();

        //launchThreadWithFlag();

        //launchThreadWithInterrupt();

        //syncThreads1();

        //syncThreads2();

        //threadsWithSameClass();
    }

    public static void launchThreadWithFlag() {
        MyThreadWithFlags thread = new MyThreadWithFlags();
        thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException: " + ex.getMessage());
        }
        thread.setFinish(true);
    }

    public static void launchThreadWithInterrupt() {
        MyThreadWithInterrupt thread = new MyThreadWithInterrupt();
        thread.start();

        try {
            // Wait for a while...
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    public static void launchCreateThreads() {
        //Create and run Thread
        Thread thread = new MyThread();
        thread.start();

        //Create and run Runnable
        Thread runnable = new Thread(new MyRunnable());
        runnable.start();

        //Create and run lambda runnable
        Runnable runnable1 = () -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Counting Lambda Runnable" + i);
            }
        };
        Thread runnableLambda = new Thread(runnable1);
        runnableLambda.start();
    }

    public static void syncThreads1() {
        System.out.println("Start Main thread: ");
        Thread t = new MyThread();
        t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException: " + ex.getMessage());
        }
        System.out.println("Finish Main thread: ");


    }

    public static void syncThreads2() {
        System.out.println("Start Main thread: ");
        MyThreadWithWait thread1 = new MyThreadWithWait("One");
        MyThreadWithWait thread2 = new MyThreadWithWait("two", thread1);
        thread1.start();
        thread2.start();

        System.out.println("Finish Main thread: ");


    }

    public static void threadsWithSameClass(){
        Counter c = new Counter(100);
        Thread tinc = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                c.increment();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) { }
            }
            System.out.println("Finishing inc. Final value= " + c.getValue());
        });

        Thread tac = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                c.decrement();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) { }
            }
            System.out.println("Finishing inc. Final value= " + c.getValue());
        });

        tinc.start();
        tac.start();
    }
}
