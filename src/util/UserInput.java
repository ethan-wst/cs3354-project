package util;

/**
 * The `UserInput` class in Java parses and processes user input for a chess
 * game, converting
 * characters to integers based on specific rules.
 */
public class UserInput {
    /**
    * The lines `private final char[] parsedUserInput = new char[4];` and `private
    * final int[]
    * intUserInput = new int[4];` are declaring two private instance variables in
    * the `UserInput` class in Java.
    */ 
    private final char[] parsedUserInput = new char[4];
    private final int[] intUserInput = new int[4];

    /** The `UserInput` constructor in Java takes a `String` input from the user,
    * then it calls the
    * `parseInput` method to remove any spaces and store the characters in the
    * `parsedUserInput`
    * array. After that, it calls the `processInput` method to convert the
    * characters in the
    * `parsedUserInput` array to integers based on specific rules and store them in
    * the `intUserInput`
    * array. This way, the constructor processes the user input and prepares it for
    * further use in the chess game.
    */
    public UserInput(String userInput) {
        parseInput(userInput);
        processInput(parsedUserInput);
    }

    /**
     * The function `parseInput` removes spaces from a given user input string and
     * stores the non-space
     * characters in an array.
     * 
     * @param userInput The `userInput` parameter is a String that contains the
     *                  input provided by the
     *                  user. The `parseInput` method is designed to iterate through
     *                  each character in the `userInput`
     *                  String and store non-space characters in an array called
     *                  `parsedUserInput`.
     */
    public final void parseInput(String userInput) {
        for (int i = 0, j = 0; i < userInput.length(); i++) {
            if (userInput.charAt(i) != ' ') {
                try {
                    parsedUserInput[j] = userInput.charAt(i);
                    j++;
                } catch (Exception e) {
                    break;
                }

            }
        }
    }

    /**
     * The function processes an array of characters representing chess moves,
     * converting them into
     * corresponding integer values for further handling.
     * 
     * @param parsedInput The `parsedInput` parameter is an array of characters
     *                    representing user
     *                    input. The method `processInput` processes this input to
     *                    convert it into an integer array
     *                    `intUserInput` based on certain conditions.
     */
    public final void processInput(char[] parsedInput) {
        // Move is a castling move, implement handling later
        if ((int) parsedInput[0] == 48) {

        }
        for (int i = 0; i < parsedInput.length; i++) {
            // if input is a lower case letter (a-h)
            if ((int) parsedInput[i] < 105 && (int) parsedInput[i] > 96) {
                intUserInput[i] = (int) parsedInput[i] - 97;
            }
            // if input is a uppercase letter (A-H)
            else if ((int) parsedInput[i] < 73 && (int) parsedInput[i] > 64) {
                intUserInput[i] = (int) parsedInput[i] - 65;
            }
            // if input is int (1-8)
            else if ((int) parsedInput[i] < 57 && (int) parsedInput[i] > 48) {
                intUserInput[i] = (int) parsedInput[i] - 49;
            }
            // anything else is invalid input
            else {
                intUserInput[i] = -1;
                break;
            }
        }
    }

    /**
     * The function `getIntInput` returns an array of integers as user input.
     * 
     * @return An array of integers named intUserInput is being returned.
     */
    public int[] getIntInput() {
        return intUserInput;
    }
}