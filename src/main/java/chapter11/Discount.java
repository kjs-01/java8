package chapter11;

import static chapter11.ExchangeService.*;
import static chapter11.Util.delay;
import static chapter11.Util.format;

/**
 * Created by kjs850 on 2017. 2. 14..
 */
public class Discount {

    public enum Code{
        NONE(0),SIVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage){
            this.percentage = percentage;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(Discount.Code.valueOf("SIVER"));
//    }
    public static  String applyDiscount(Quote quote){

        return  quote.getShopName()+ " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());

    }

    private static double apply(double price, Code code) {
        delay();
        return format(price * (100 - code.percentage) / 100);
    }

}
