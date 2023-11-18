package Class1;

public class MyThreadWithWait extends Thread {

    Thread waitThread;
    String name;

    public MyThreadWithWait(String name) {
        waitThread = null;
        this.name = name;
    }

    public MyThreadWithWait(String name, Thread thread) {
        waitThread = thread;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Start thread: " + name);

        if (waitThread != null) {
            try {
                waitThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Finishedthread: " + name);
    }
}
