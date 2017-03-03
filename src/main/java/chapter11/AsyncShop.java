package chapter11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static chapter11.Util.delay;
import static chapter11.Util.format;

/**
 * Created by kjs850 on 2017. 2. 16..
 */
public class AsyncShop {

    private final String name;
    private final Random random;

    public AsyncShop(String name) {
        this.name = name;
        this.random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public Future<Double> getPrice(String product) {
        /*
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
                    try {
                        double price = calculatePrice(product);
                        futurePrice.complete(price);
                    } catch (Exception ex) {
                        futurePrice.completeExceptionally(ex);
                    }
        }).start();
        return futurePrice;
        */
        return CompletableFuture.supplyAsync(()-> calculataePrice(product));
    }

    private Double calculataePrice(String product) {
        delay();
//        if(true) throw new RuntimeException("product not available");
        return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }
}
