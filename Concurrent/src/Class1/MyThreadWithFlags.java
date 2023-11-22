package Class1;

public class MyThreadWithFlags extends Thread {
    boolean finish = false;

    public void setFinish(boolean exit) {
        this.finish = exit;
    }

    @Override
    public void run() {
        while (!finish) {
            System.out.println("MyThreadWithFlags Thread");
        }
    }
}
