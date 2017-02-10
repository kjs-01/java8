package exam;

import chapter4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chapter4.Dish.menu;
import static java.util.stream.Collectors.toList;

public class exam03 {

//    3. 검색과 매칭  - p.157
//    프레디케이트가 적어도 한 요소와 일치하는지 확인
//    프레디케이트가 모든요소와 일치하는지 확인
//    요소검색
//    첫 번째 요소 찾기
    public static void main(String[] args) {

        System.out.println("프레디케이트가 적어도 한 요소와 일치하는지 확인 - anyMatch" + "==============");
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println("프레디케이트가 모든요소와 일치하는지 확인 - allMatch, noneMatch" + "==============");
        System.out.println("isAllHealthyMenu:" + isHealthyMenu1()); // Calories() < 1000
        System.out.println("isAllJunkMenu:" +isHealthyMenu2());


        System.out.println("요소검색" + "==============");
        Optional<Dish> dish =  findVegetarianDish();
        dish.ifPresent(dish1 -> System.out.println("find Any VegetarianDish1: " + dish1.getName()));

        if(dish.isPresent()) { //true
            System.out.println("find Any VegetarianDish2: " + dish.get().getName());
        }
    }

    //Predicate
    private static boolean isVegetarianFriendlyMenu() {
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    private static boolean isHealthyMenu1() {
        return menu.stream().allMatch(dish -> dish.getCalories() < 1000);
    }

    private static boolean isHealthyMenu2() {
        return menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);
    }

    //Stream은 임의의 요소를 찾는 findFirst, findAny 기능도 제공한다.
    // FindFirst, FindAny 값은 Optional 객체를 리턴한다.
    private static Optional<Dish> findVegetarianDish() {
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }
}
