// Интерфейс стратегии перемещения
interface MoveStrategy {
    void move(String from, String to);
}

// Конкретные стратегии
class WalkStrategy implements MoveStrategy {
    @Override
    public void move(String from, String to) {
        System.out.println("Hero walks from " + from + " to " + to);
    }
}

class HorseRideStrategy implements MoveStrategy {
    @Override
    public void move(String from, String to) {
        System.out.println("Hero rides a horse from " + from + " to " + to);
    }
}

class FlyStrategy implements MoveStrategy {
    @Override
    public void move(String from, String to) {
        System.out.println("Hero flies from " + from + " to " + to);
    }
}

// Класс героя
class Hero {
    private MoveStrategy moveStrategy;

    public Hero(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    // Установка новой стратегии
    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    // Перемещение
    public void move(String from, String to) {
        moveStrategy.move(from, to);
    }
}

// Демонстрация работы
public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero(new WalkStrategy());

        hero.move("Village", "Forest");

        hero.setMoveStrategy(new HorseRideStrategy());
        hero.move("Forest", "Castle");

        hero.setMoveStrategy(new FlyStrategy());
        hero.move("Castle", "Mountain");
    }
}
