public class FlyStrategy implements MoveStrategy {
    @Override
    public void move(String from, String to) {
        System.out.println("Hero flies from " + from + " to " + to);
    }
}