package util;


public class UserInput {
    private final char[] parsedUserInput = new char[4];
    private final int[] intUserInput = new int[4];

    public UserInput(String userInput) {
        parseInput(userInput);
        processInput(parsedUserInput);
    }

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

    public final void processInput(char[] parsedInput) {
        //Move is a castling move, implement handling later
        if ((int)parsedInput[0] == 48) {
            
        } 
        for (int i = 0; i < parsedInput.length; i++) {
            //if input is a lower case letter (a-h)
            if ((int)parsedInput[i] < 105 && (int)parsedInput[i] > 96) {
                intUserInput[i] = (int)parsedInput[i] - 97; 
            } 
            //if input is a uppercase letter (A-H)
            else if ((int)parsedInput[i] < 73 && (int)parsedInput[i] > 64) {
                intUserInput[i] = (int)parsedInput[i] - 65;
            } 
            //if input is int (1-8)
            else if ((int)parsedInput[i] < 57 && (int)parsedInput[i] > 48) {
                intUserInput[i] = (int)parsedInput[i] - 49;
            }
            //anything else is invalid input 
            else {
                intUserInput[i] = -1;
                break;
            }
        }
    }

    public int[] getIntInput() {
        return intUserInput;
    }
}