package chapter5;

import chapter4.Dish;

import java.util.Optional;

import static chapter4.Dish.menu;
public class Finding {

    public static void main(String[] args) {

        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println("isAllHealthyMenu:" + isAllHealthyMenu()); // Calories() < 1000
        System.out.println("isAllJunkMenu:" +isAllJunkMenu());

        Optional<Dish> dish =  findVegetarianDish();

        dish.ifPresent(dish1 -> System.out.println("find Any VegetarianDish: " + dish1.getName()));
    }



    //Predicate
    private static boolean isVegetarianFriendlyMenu() {
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    private static boolean isAllHealthyMenu() {
        return menu.stream().allMatch(dish -> dish.getCalories() < 1000);
    }

    private static boolean isAllJunkMenu() {
        return menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);
    }

    //Stream은 임의의 요소를 찾는 findFirst, findAny 기능도 제공한다.
    // FindFirst, FindAny 값은 Optional 객체를 리턴한다.
    private static Optional<Dish> findVegetarianDish() {
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }
}
