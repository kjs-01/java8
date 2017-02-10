package exam;

/**
 * Created by kjs850 on 2017. 2. 7..
 */

import chapter4.Dish;
import static chapter4.Dish.menu;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class exam01 {


// 1. 필터링과 슬라이싱 - p.148
//        프레디케이트로 필터링
//        고유 요소 필터링.
//        스트림 축소
//        요소 건너뛰기

    public static void main(String[] args) {

        System.out.println("프레디케이트로 필터링" + "==============");
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());

        vegetarianMenu.forEach(System.out::println);

        System.out.println("고유 요소 필터링 - distinct" + "==============");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i-> i % 2 ==0)
                .distinct()
                .forEach(System.out::println);

        System.out.println("스트림 축소 - limit" + "==============");
        List<Dish> dishesLimit3 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());

        dishesLimit3.forEach(System.out::println);

        System.out.println("요소 건너뛰기 - skip" + "==============");
        List<Dish> dishesSkip2 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());

        dishesSkip2.forEach(System.out::println);

        System.out.println("parallelStream" + "==============");
        //  총 코어 개수:	2
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18)
//                .stream()
                .parallelStream()
                .forEach(x -> {
                    System.out.println(x);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }
}
