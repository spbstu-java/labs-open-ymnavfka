package Translator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Translator tr = new Translator("Translator/dictionary.txt");
            try (Scanner sc = new Scanner(System.in)) {
                System.out.println("Enter text to translate:");
                String line = sc.nextLine();
                System.out.println(tr.translate(line));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
