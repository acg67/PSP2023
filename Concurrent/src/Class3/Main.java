package Class3;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //OneCallable();
        //MultiCallGetAll();
        MultiCallGetOne();
    }


    public static void OneCallable() {
        Callable<Integer> callInt = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return 20;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };
        //Executor Creation
        ExecutorService executor = Executors.newFixedThreadPool(1);
        // Calling submit executes the thread and returns a Future
        Future<Integer> future = executor.submit(callInt);
        //Launch executor
        executor.shutdown();

        System.out.println("future done? " + future.isDone());
        Integer result;
        try {
            //NORMAL CALL
            // result = future.get(); // It BLOCKS main thread until it returns!
            //CALL WITH WAIT AND TimeoutException
            result = future.get(10, TimeUnit.SECONDS); // It BLOCKS main thread until it returns!

            System.out.println("future done? " + future.isDone());
            System.out.println("Result: " + result); // Prints 20
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException: " + ex.getMessage());
        } catch (ExecutionException ex) {
            System.out.println("ExecutionException: " + ex.getMessage());
        } catch (TimeoutException ex) {
            System.out.println("TimeoutException: " + ex.getMessage());
        }
    }

    public static Callable<Integer> getSumCallable(int num1, int num2, int secondsSleep) {
        return () -> {
            try {
                TimeUnit.SECONDS.sleep(secondsSleep);
                return num1 + num2;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };
    }

    public static void MultiCallGetAll() {
        List<Callable<Integer>> callables = Arrays.asList(
                getSumCallable(3, 6, 2),
                getSumCallable(5, 8, 3),
                getSumCallable(12, 3, 1)
        );
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Future<Integer>> futures;
        try {
            futures = executor.invokeAll(callables);
            executor.shutdown();
            futures.forEach(future -> {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new IllegalStateException(e);
                }
            });
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException: " + ex.getMessage());
        }
    }

    public static void MultiCallGetOne() {
        List<Callable<Integer>> callables = Arrays.asList(
                getSumCallable(3, 6, 2),
                getSumCallable(5, 8, 3),
                getSumCallable(12, 3, 1)
        );
        ExecutorService executor = Executors.newWorkStealingPool();
        try {
            // Blocks and returns first result
            int firstResult = executor.invokeAny(callables);
            executor.shutdown();
            System.out.println(firstResult); // 15 --> 12 + 3 finishes in 1 second
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException: " + ex.getMessage());
        } catch (ExecutionException ex) {
            System.out.println("ExecutionException: " + ex.getMessage());
        }
    }

}