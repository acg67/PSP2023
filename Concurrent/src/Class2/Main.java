package Class2;

import Class1.MyThread;

import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        //createExecutor();

        //createScheduledThreadPoolExecutor();


    }

    public static void createExecutor(){
        //Create executor
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        //Create Threads
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        //Add threads to executor
        executor.execute(t1);
        executor.execute(t2);

        //Launch executor
        executor.shutdown();
    }

    public static void createScheduledThreadPoolExecutor(){
        //Create executor
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);

        //Create Threads
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        //Add threads to executor
        executor.schedule(t1, 5, TimeUnit.SECONDS);
        executor.schedule(t2, 1, TimeUnit.SECONDS);

        //Launch executor
        executor.shutdown();
    }

}
