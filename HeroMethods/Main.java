package HeroMethods;

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
