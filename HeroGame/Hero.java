public class Hero {
    private MoveStrategy moveStrategy;

    public Hero(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void move(String from, String to) {
        moveStrategy.move(from, to);
    }
}
