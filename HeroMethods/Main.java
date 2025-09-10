package HeroMethods;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Решение задачи: аннотация, класс с методами (public/protected/private),
 * и вызов всех аннотированных protected/private методов N раз.
 */
public class Main {
    public static void main(String[] args) {
        HeroMethods hero = new HeroMethods();

        // Вызов из "другого" класса (AnnotatedInvoker)
        AnnotatedInvoker.invokeAnnotatedProtectedAndPrivateMethods(hero);
    }
}

/**
 * Аннотация с целочисленным параметром.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Repeat {
    int value();
}

/**
 * Класс, содержащий public, protected и private методы с разными параметрами.
 * Некоторые методы аннотированы @Repeat(N).
 */
class HeroMethods {
    // ========== Public methods ==========
    public void publicWalk() {
        System.out.println("[public] Walking...");
    }

    public int publicRun(int speed) {
        System.out.println("[public] Running with speed " + speed);
        return speed;
    }

    // ========== Protected methods ==========
    @Repeat(3)
    protected void protectedSneak() {
        System.out.println("[protected] Sneaking silently");
    }

    @Repeat(2)
    protected String protectedTalk(String msg, int times) {
        String res = "[protected] Talking: " + msg + " x" + times;
        System.out.println(res);
        return res;
    }

    protected void protectedUnused(double d) {
        System.out.println("[protected] Unused: " + d);
    }

    // ========== Private methods ==========
    @Repeat(4)
    private void privateHide() {
        System.out.println("[private] Hiding in shadows");
    }

    @Repeat(2)
    private int privateCompute(int a, int b, boolean flag) {
        int result = flag ? (a + b) : (a - b);
        System.out.println("[private] compute => " + result);
        return result;
    }

}

/**
 * Класс-вызыватель, который находит все аннотированные protected и private
 * методы,
 * подготавливает подходящие аргументы независимо от их типов, и вызывает их N
 * раз.
 */
class AnnotatedInvoker {

    public static void invokeAnnotatedProtectedAndPrivateMethods(Object target) {
        Class<?> cls = target.getClass();
        Method[] methods = cls.getDeclaredMethods();

        Arrays.sort(methods, (m1, m2) -> m1.getName().compareTo(m2.getName())); // для стабильного вывода

        for (Method method : methods) {
            Repeat ann = method.getAnnotation(Repeat.class);
            if (ann == null)
                continue; // только аннотированные

            int mod = method.getModifiers();
            // Только protected или private (не public)
            if (!Modifier.isProtected(mod) && !Modifier.isPrivate(mod))
                continue;

            int times = ann.value();
            System.out.printf("Invoking %s (modifiers=%s) %d time(s)\n",
                    method.getName(),
                    Modifier.toString(mod),
                    times);

            method.setAccessible(true); // чтобы иметь возможность вызвать protected/private

            // Подготовка аргументов для каждого вызова
            Class<?>[] paramTypes = method.getParameterTypes();
            Object[] args = new Object[paramTypes.length];
            for (int i = 0; i < paramTypes.length; i++) {
                args[i] = defaultValueFor(paramTypes[i]);
            }

            // Вызов указанное число раз
            for (int t = 0; t < times; t++) {
                try {
                    method.invoke(target, args);
                    // Можно обработать return value если необходимо
                } catch (InvocationTargetException ite) {
                    System.err.println("Method threw an exception: " + ite.getTargetException());
                } catch (Exception e) {
                    System.err.println("Failed to invoke method " + method.getName() + ": " + e);
                }
            }

            System.out.println(); // разделитель
        }
    }

    /**
     * Возвращает "умолчательное" значение для заданного типа параметра,
     * чтобы можно было вызвать метод без знания типов заранее.
     */
    private static Object defaultValueFor(Class<?> type) {
        if (type.isPrimitive()) {
            if (type == boolean.class)
                return false;
            if (type == byte.class)
                return (byte) 0;
            if (type == short.class)
                return (short) 0;
            if (type == int.class)
                return 0;
            if (type == long.class)
                return 0L;
            if (type == float.class)
                return 0.0f;
            if (type == double.class)
                return 0.0d;
            if (type == char.class)
                return '\0';
            // fallback
            return 0;
        }

        // Специально для String — пустая строка (удобно для вывода)
        if (type == String.class)
            return "";

        // Пустой массив для массивов
        if (type.isArray()) {
            return Array.newInstance(type.getComponentType(), 0);
        }

        // Для enum — первая константа (если есть)
        if (type.isEnum()) {
            Object[] consts = type.getEnumConstants();
            return consts != null && consts.length > 0 ? consts[0] : null;
        }

        // Попытка создать объект через конструктор без параметров
        try {
            Constructor<?> ctor = type.getDeclaredConstructor();
            ctor.setAccessible(true);
            return ctor.newInstance();
        } catch (Exception e) {
            // если не удалось — вернём null (подходит для ссылочных типов)
            return null;
        }
    }
}
