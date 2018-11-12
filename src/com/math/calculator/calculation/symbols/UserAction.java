package com.math.calculator.calculation.symbols;

import com.math.calculator.calculation.exception.UserActionNotFoundException;

/**
 * Commands to use all calculator functions by user
 */
public enum UserAction {

    CALCULATE("c", "To calculate input: "),
    ALL_RESULTS("a", "To display all results history input "),
    ALL_UNIQUE_RESULTS("u", "To display all unique results history input "),
    USER_ALL_RESULTS("ua", "To display all results of user input "),
    USER_UNIQUE_RESULTS("uu", "To display all unique results of user input "),
    EXIT("e", "To exit press ");

    /**
     * String value of command
     */
    private final String command;

    private final String message;

    UserAction(String command, String message) {
        this.command = command;
        this.message = message;
    }

    /**
     * Returns string value of command
     */
    public String getCommand() {
        return command;
    }
    /**
     * Returns string message of command
     */
    public String getMessage() {
        return message;
    }
    /**
     * Checks if symbol is the action and return the action
     *
     * @param action entered string by user
     * @return concrete action
     * @throws UserActionNotFoundException when @action is not a command
     */
    public static UserAction getUserAction(String action) throws UserActionNotFoundException {
        for (UserAction userAction : UserAction.values()) {
            if (userAction.getCommand().equals(action)) {
                return userAction;
            }
        }
        throw new UserActionNotFoundException();
    }

}
