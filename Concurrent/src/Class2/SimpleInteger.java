package Class2;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleInteger {
    AtomicInteger value;

    public SimpleInteger(int value) {
        this.value.set(value);
    }

    public void increment() {
        value.incrementAndGet();
    }

    public void decrement() {
        value.decrementAndGet();
        //more code
    }

    public int getValue() {
        int localReturn = value.get();
        return localReturn;
    }
}
