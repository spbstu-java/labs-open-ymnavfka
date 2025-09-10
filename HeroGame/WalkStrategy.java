public class WalkStrategy implements MoveStrategy {
    @Override
    public void move(String from, String to) {
        System.out.println("Hero walks from " + from + " to " + to);
    }
}