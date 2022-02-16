package be.kdg.stratego.customUtil;
import java.util.InputMismatchException;
import java.util.Scanner;
// Dit is een gigantisch grote test die nog nooit eerder gezien werd
public class AskUtility {
    private final static Scanner keyboard = new Scanner(System.in);

    public static String askString(String question, boolean retryOnError) {
        System.out.print(question);
        return keyboard.nextLine();
    }

    public static int askInt(String question, boolean retryOnError) {
        // Intialisation
        int result = 0;

        // If we don't need to retry, set the OK check to true immediately
        boolean resultOk = !retryOnError;

        do {
            try {
                // Ask the question
                System.out.print(question);

                // Get the answer
                result = keyboard.nextInt();

                // Just to clear the input buffer to prevent weird bugs
                keyboard.nextLine();

                // All checks ok!
                resultOk = true;
            }
            catch (InputMismatchException e) {
                resultOk = false;
            }
        } while (!resultOk);

        return result;
    }

    public static boolean askBoolean(String question, boolean retryOnError) {
        // Intialisation
        boolean result = false;

        // If we don't need to retry, set the OK check to true immediately
        boolean resultOk = !retryOnError;

        do {
            try {
                // Ask the question
                System.out.print(question);

                // Get the answer
                String tempResult = keyboard.nextLine();

                if(tempResult.equalsIgnoreCase("yes")) {
                    result = true;
                    resultOk = true;
                } else if (tempResult.equalsIgnoreCase("no")) {
                    result = false;
                    resultOk = true;
                } else {
                    System.out.println("Please reply with yes or no.");
                    resultOk = false;
                }
            }
            catch (InputMismatchException e) {
                resultOk = false;
            }
        } while (!resultOk);

        return result;
    }
}
