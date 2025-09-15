package Stream;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1. Среднее значение
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Average: " + StreamTasks.average(nums));

        // 2. Строки в верхний регистр с префиксом
        List<String> words = Arrays.asList("one", "two", "three");
        System.out.println("Upper with prefix: " + StreamTasks.toUpperWithPrefix(words));

        // 3. Квадраты уникальных элементов
        List<Integer> nums2 = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        System.out.println("Unique squares: " + StreamTasks.uniqueSquares(nums2));

        // 4. Последний элемент коллекции
        List<String> list = Arrays.asList("a", "b", "c");
        System.out.println("Last element: " + StreamTasks.lastElement(list));

        // 5. Сумма чётных чисел
        int[] arr = { 1, 2, 3, 4, 5 };
        System.out.println("Sum of even numbers: " + StreamTasks.sumEven(arr));

        // 6. Преобразование строк в Map
        List<String> strList = Arrays.asList("apple", "banana", "carrot");
        System.out.println("Map: " + StreamTasks.toMap(strList));
    }
}
