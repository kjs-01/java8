package chapter11;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

/**
 * Created by kjs850 on 2017. 2. 10..
 */
public class Util {

    private static final Random RANDOM = new Random(0);
    private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    public static void delay(){
        int delay = 1000;

        try {
            Thread.sleep(delay);
        }catch(InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static double format(double number){
        synchronized (formatter){
            return new Double(formatter.format(number));
        }
    }
}
