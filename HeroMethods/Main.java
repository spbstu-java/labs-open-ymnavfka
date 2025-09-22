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
