public class Hero {
    private MoveStrategy moveStrategy;

    public Hero(MoveStrategy moveStrategy) {
        if (moveStrategy == null)
            throw new IllegalArgumentException("MoveStrategy cannot be null");
        this.moveStrategy = moveStrategy;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        if (moveStrategy == null)
            throw new IllegalArgumentException("MoveStrategy cannot be null");
        this.moveStrategy = moveStrategy;
    }

    public void move(String from, String to) {
        moveStrategy.move(from, to);
    }
}
