package chapter11.v1;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by kjs850 on 2017. 2. 14..
 */
public class BestPriceFinderMain {

    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    public static void main(String[] args) {
        execute("sequential", () -> bestPriceFinder.findPricesSequential("myPhone27S")); //sequential done in 4048 msecs
        execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone27S"));//parallel done in 1036 msecs
        execute("composed CompletableFuture", () -> bestPriceFinder.findPricesFuture("myPhone27S")); //Future done in 1037 msecs
        execute("combined USD CompletableFuture", () -> bestPriceFinder.findPricesInUSD("myPhone27S")); //combined USD CompletableFuture done in 3037 msecs
        execute("combined USD CompletableFuture v2", () -> bestPriceFinder.findPricesInUSD2("myPhone27S"));  //combined USD CompletableFuture v2 done in 3032 msecs
        execute("combined USD CompletableFuture v3", () -> bestPriceFinder.findPricesInUSD3("myPhone27S"));   //combined USD CompletableFuture v3 done in 3013 msecs
    }

    private static void execute(String msg, Supplier<List<String>> s){
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
