package chapter11.v1;

import chapter11.ExchangeService;
import chapter11.ExchangeService.Money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by kjs850 on 2017. 2. 10..
 */
public class BestPriceFinder {

    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll")/*,
            new Shop("ShopEasy")*/);

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    public List<String> findPricesSequential(String product){
        return shops.stream()
                .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
                .collect(toList());
    }

    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> shop.getName() + " price is " + shop.getPrice(product))
                .collect(Collectors.toList());
    }

    public List<String> findPricesFuture(String product){
        List<CompletableFuture<String>> priceFutures = shops
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is "
                    + shop.getPrice(product), executor))
                .collect(toList());

        List<String> prices = priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());

        return prices;
    }

    public List<String> findPricesInUSD(String product){
        List<CompletableFuture<Double>>  priceFutures = new ArrayList<>();
        for(Shop shop : shops){
            CompletableFuture<Double> futurePriceInUSD =
                    CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                    .thenCombine(
                            CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.EUR, Money.USD)),
                            (price, rate) -> price * rate
                    );

            priceFutures.add(futurePriceInUSD);

        }

        List<String> prices =  priceFutures
                                .stream()
                                .map(CompletableFuture::join)
                                .map(price -> "price is " + price)
                                .collect(toList());
        return prices;

    }

    public List<String> findPricesInUSDJava7(String product){
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<Double>> priceFutures = new ArrayList<>();
        for(Shop shop : shops){
            final Future<Double> futureRate = executor.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    return ExchangeService.getRate(Money.EUR, Money.USD);
                }
            });

            Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    try{
                        double priceInEUR = shop.getPrice(product);
                        return priceInEUR * futureRate.get();
                    }catch(InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                }
            });
            priceFutures.add(futurePriceInUSD);
        }//for

        List<String> prices = new ArrayList<>();
        for (Future<Double> priceFuture : priceFutures){
            try{
                prices.add(" price is "+ priceFuture.get());
            }catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

        }
        return prices;
    }

    public List<String> findPricesInUSD2(String product) {
        List<CompletableFuture<String>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            // Here, an extra operation has been added so that the shop name
            // is retrieved within the loop. As a result, we now deal with
            // CompletableFuture<String> instances.
            CompletableFuture<String> futurePriceInUSD =  //double에서 string으로 바뀜.
                    CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                            .thenCombine(
                                    CompletableFuture.supplyAsync(
                                            () -> ExchangeService.getRate(Money.EUR, Money.USD)),
                                    (price, rate) -> price * rate
                            ).thenApply(price -> shop.getName() + "price is" + price);
            priceFutures.add(futurePriceInUSD);
        }
        List<String> prices = priceFutures
                .stream()
                .map(CompletableFuture::join)
                //여기서 빠짐.
                .collect(Collectors.toList());
        return prices;
    }

    public List<String> findPricesInUSD3(String product){

        Stream<CompletableFuture<String>> priceFuturesStream = shops
                .stream()
                .map(shop -> CompletableFuture
                        .supplyAsync(() -> shop.getPrice(product))
                        .thenCombine(
                                CompletableFuture.supplyAsync(() -> ExchangeService.getRate(Money.EUR, Money.USD)),
                                (price, rate) -> price * rate)
                        .thenApply(price -> shop.getName()+ " price is " + price));

        List<CompletableFuture<String>> priceFutures = priceFuturesStream.collect(toList());
        List<String> prices = priceFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(toList());

        return prices;

    }




}
