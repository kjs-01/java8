package exam;

import chapter4.Dish;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static chapter4.Dish.menu;

/**
 * Created by kjs850 on 2017. 2. 7..
 */
public class exam06 {

//    6. 숫자형 스트림(p.171)
//       기본형 특화 스트림
//       숫자범위
//       숫자 스트림 활용: 피타고라스 수  (pass)
    public static void main(String[] args) {


        System.out.println("기본형 특화 스트림" + "==============");

        //숫자 스트림으로 매핑
        int calories = menu.stream()    //Stream<Dish> 반환
                .mapToInt(Dish::getCalories) //IntStream 반환
                .sum();
        System.out.println("Number of calories:" + calories);

        //객체 스트림으로 복원하기
        IntStream intStream =  menu.stream().mapToInt(Dish::getCalories);

        Stream<Integer> stream = intStream.boxed(); ////특화 스트림을 boxed()를 이용해 일반 스트림으로 변환 할 수 있다.
        stream.forEach(System.out::println);

        //optionalInt
        OptionalInt maxCalories = menu.stream()    //Stream<Dish> 반환
                .mapToInt(Dish::getCalories) //IntStream 반환
                .max();
        int max = maxCalories.orElse(1);
        System.out.println("max:: " + max);



        System.out.println("숫자범위" + "==============");
        //range(시작값은 포함, 종료값 비포함.) , rangeClosed(시작값과 종료값이 결과에 포함)
        IntStream evenNumbers1 =  IntStream.range(1, 3)
                                            .filter(n -> {
                                                System.out.println(n);
                                                return true;
                                            });
//        System.out.println(evenNumbers1.count());

        IntStream evenNumbers2 =  IntStream.rangeClosed(1, 100)
                                            .filter(n -> n%2 == 0);
        System.out.println(evenNumbers2.count());

    }
}
