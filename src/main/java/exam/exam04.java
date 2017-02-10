package exam;


import chapter4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chapter4.Dish.menu;
import static java.util.stream.Collectors.toList;

public class exam04 {

//    4. 리듀싱 - p.160
//    요소의 합
//    최대값과 최소값

    public static void main(String[] args) {

        //리듀싱 연산 - 모든 스트림 요소를 처리해서 값으로 도출하는 것. (fold)
        //sum, min, max, average ...

        System.out.println("요소의 합" + "==============");

        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);

        System.out.println("최대값과 최소값" + "==============");

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        int min = numbers.stream().reduce(0, Integer::min);
//        min.ifPresent(System.out::println);


        //p.166 중간 연산과 최종연산 표 한번 보고 넘어가기.
    }
}
