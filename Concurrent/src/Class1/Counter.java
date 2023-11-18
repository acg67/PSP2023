package Class1;

public class Counter {
    int value;

    public Counter(int value) {
        this.value = value;
    }


    //Add synchronized function
    public synchronized void increment() {
        value++;
    }

    //Add synchronized function between  code

    public void decrement() {
        //code

        synchronized (this) {
            value--;
        }

        //more code
    }

    public int getValue() {
        return value;
    }
}
