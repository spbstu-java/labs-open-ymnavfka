package Stream;

import java.util.*;
import java.util.stream.*;

public class StreamTasks {

    // 1. Среднее значение списка целых чисел
    public static double average(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    // 2. Все строки -> верхний регистр + префикс "_new_"
    public static List<String> toUpperWithPrefix(List<String> list) {
        return list.stream()
                .map(s -> "_new_" + s.toUpperCase())
                .toList();
    }

    // 3. Квадраты элементов, встречающихся только один раз
    public static List<Integer> uniqueSquares(List<Integer> list) {
        return list.stream()
                .filter(i -> Collections.frequency(list, i) == 1)
                .map(i -> i * i)
                .toList();
    }

    // 4. Последний элемент коллекции или исключение
    public static <T> T lastElement(Collection<T> col) {
        return col.stream()
                .reduce((first, second) -> second)
                .orElseThrow(NoSuchElementException::new);
    }

    // 5. Сумма чётных чисел массива
    public static int sumEven(int[] arr) {
        return Arrays.stream(arr)
                .filter(x -> x % 2 == 0)
                .sum();
    }

    // 6. Преобразовать строки в Map: первый символ -> ключ, остальные -> значение
    public static Map<Character, String> toMap(List<String> list) {
        return list.stream()
                .collect(Collectors.toMap(s -> s.charAt(0), s -> s.substring(1)));
    }
}
