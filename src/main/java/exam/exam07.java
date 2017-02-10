package exam;
import chapter4.Dish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chapter4.Dish.menu;
import static java.util.stream.Collectors.toList;

public class exam07 {


//    7. 스트림 만들기(p.178)
//    값으로 스트림 만들기
//    배열로 스트림 만들기
//    파일로 스트림 만들기
//    함수로 무한 스트림 만들기

    public static void main(String[] args) throws IOException {
        System.out.println("값으로 스트림 만들기" + "==============");
        Stream<String> stream =  Stream.of("Java8", "Lambda", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty();
        System.out.println("emptyStream.count():" + emptyStream.count());

        System.out.println("배열로 스트림 만들기" + "==============");
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println("sum:" + sum);

        System.out.println("파일로 스트림 만들기" + "==============");
        Files.lines(Paths.get("/Users/kjs850/IdeaProjects/java8/data.txt"), Charset.defaultCharset())
                .forEach(System.out::println);

        long uniqueWords = Files.lines(Paths.get("/Users/kjs850/IdeaProjects/java8/data.txt"), Charset.defaultCharset())
                .flatMap(line -> Arrays.stream(line.split("")))
                .distinct()
                .count();
        System.out.println("There are " + uniqueWords + " unique words in data.txt");

        System.out.println("함수로 무한 스트림 만들기" + "==============");
        //iterte, generate(연속적으로 생산하지 않는다.)
        Stream.iterate(0, n-> 1)
                .limit(10)
                .forEach(System.out::println);

        Stream.generate(()->1) //()-> 1
                .limit(5)
                .forEach(System.out::println);

    }
}
