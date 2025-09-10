public class HorseRideStrategy implements MoveStrategy {
    @Override
    public void move(String from, String to) {
        System.out.println("Hero rides a horse from " + from + " to " + to);
    }
}