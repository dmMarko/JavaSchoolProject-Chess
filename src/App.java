import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner inputGetter = new Scanner(System.in); // input
        String input = ""; // the string that saves the user's input

        while (!input.equals("play")) { // the game only starts when the user wants to play
            System.out.println("Welcom to chess for 2 \ntype “play” or “rules”"); // welcome message
            input = inputGetter.nextLine().toLowerCase(); // get the input

            if (input.equals("rules")) { // if the user wanted the rules, give him the rules
                System.out.println(Utilities.RULES_TEXT); // print the
            } else if (!input.equals("play")) {
                System.out.println("invalid\n");
            }
        }
    }
}