import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero(new WalkStrategy()); // стартовая стратегия

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nChoose move strategy: walk, horse, fly (or 'exit' to quit):");
                String choice = scanner.nextLine().toLowerCase();

                if (choice.equals("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                MoveStrategy strategy;
                switch (choice) {
                    case "walk" -> strategy = new WalkStrategy();
                    case "horse" -> strategy = new HorseRideStrategy();
                    case "fly" -> strategy = new FlyStrategy();
                    default -> {
                        System.out.println("Invalid choice, keeping current strategy");
                        strategy = null;
                    }
                }

                if (strategy != null) {
                    hero.setMoveStrategy(strategy);
                }

                System.out.println("Where should the hero move from?");
                String from = scanner.nextLine();
                System.out.println("Where should the hero move to?");
                String to = scanner.nextLine();

                hero.move(from, to);
            }
        }
    }
}
