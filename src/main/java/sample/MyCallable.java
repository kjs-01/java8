package sample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by kjs850 on 2017. 2. 10..
 */

//http://www.journaldev.com/1090/java-callable-future-example
public class MyCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        //return the thread name executing this callable task.
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<String>> list = new ArrayList<>();

        Callable<String> callable = new MyCallable();

        for (int i = 0; i < 100; i++) {
            Future<String> future = executor.submit(callable);
            list.add(future);
        }

        for (Future<String> future : list){
            try {
                System.out.println(new Date()+ "::" +future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
