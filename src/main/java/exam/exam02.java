package exam;

import chapter4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chapter4.Dish.menu;
import static java.util.stream.Collectors.toList;

/**
 * Created by kjs850 on 2017. 2. 7..
 */
public class exam02 {

//    2. 매핑 - p.152
//    스트림의 각 요소에 함수 적용하기
//    스트림 평면화(flatmap)
    public static void main(String[] args) {

        System.out.println("스트림의 각 요소에 함수 적용하기" + "==============");
        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)    //chanining
                .collect(toList());
        System.out.println(dishNameLengths);



        System.out.println("스트림 평면화(flatMap)" + "==============");
        List<String> words = Arrays.asList("Hello", "World");

        System.out.println("step1" + "==============");
        List<String[]> uniqueCharacters1 = words.stream()
                .map(word -> word.split(""))  //String[]
                .distinct()
                .collect(toList());
        uniqueCharacters1.forEach(System.out::println); // p.154 그림 5-5 참고.

        System.out.println("step2" + "==============");
        List<Stream<String>> uniqueCharacters2 =
                words.stream()
                        .map(word -> word.split(""))
                        .map(Arrays::stream) // Stream<String>
                .distinct()
                .collect(toList());
        uniqueCharacters2.forEach(System.out::println);

        System.out.println("step3 flatMap 사용" + "==============");
        List<String> uniqueCharacters3 =
                words.stream()
                        .map(word -> word.split(""))
                        .flatMap(Arrays::stream)   //Stream<String>을 스트림의 콘텐츠로 매핑.
                        .distinct()
                        .collect(Collectors.toList());
        uniqueCharacters3.forEach(System.out::println);

        //appendix
//        Stream<String[]>		-> flatMap ->	Stream<String>
//        Stream<Set<String>>	-> flatMap ->	Stream<String>
//        Stream<List<String>>	-> flatMap ->	Stream<String>
//        Stream<List<Object>>	-> flatMap ->	Stream<Object>

        //ex.
//        { {1,2}, {3,4}, {5,6} } -> flatMap -> {1,2,3,4,5,6}
//        { {'a','b'}, {'c','d'}, {'e','f'} } -> flatMap -> {'a','b','c','d','e','f'}

    }
}
