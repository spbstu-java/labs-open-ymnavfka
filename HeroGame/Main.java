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
