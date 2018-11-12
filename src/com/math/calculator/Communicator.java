package com.math.calculator;

import com.math.calculator.calculation.MathService;
import com.math.calculator.calculation.exception.UserActionNotFoundException;
import com.math.calculator.calculation.symbols.Bracket;
import com.math.calculator.calculation.symbols.Operator;
import com.math.calculator.calculation.symbols.UserAction;
import com.math.calculator.history.HistoryPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Communicates with user: receives and shows data.
 */
class Communicator {

    private final MathService mathService = new MathService();

    private HistoryPrinter historyPrinter = new HistoryPrinter(mathService.getUsers());

    /**
     * Opens console to communicate with user
     *
     * @param action: action name
     * @return an exit string
     * @throws Exception when program crashed
     */
    String openConsole(String action) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            if (action.equals("")) {
                System.out.println("The result = " + getCalcResult(br));
            }
            UserAction userAction = getAction(br);
            switch (userAction) {
                case CALCULATE:
                    System.out.println("The result = " + getCalcResult(br));
                    return openConsole(userAction.getCommand());
                case ALL_RESULTS:
                    historyPrinter.printAllResults();
                    return openConsole(userAction.getCommand());
                case ALL_UNIQUE_RESULTS:
                    historyPrinter.printAllUniqueResults();
                    return openConsole(userAction.getCommand());
                case USER_ALL_RESULTS:
                    System.out.println("Input please username:");
                    System.out.println(historyPrinter.getUserAllResults(br.readLine()));
                    return openConsole(userAction.getCommand());
                case USER_UNIQUE_RESULTS:
                    System.out.println("Input please username:");
                    System.out.println(historyPrinter.getUserUniqueResults(br.readLine()));
                    return openConsole(userAction.getCommand());
                case EXIT:
                    return "Good bye";
            }
        } catch (UserActionNotFoundException e) {
            System.out.println("Error! Wrong command! Please choose one of command from the given list");
        }
        return openConsole("Error");
    }

    /**
     * Receives data from user and returns the result
     *
     * @param reader read next entered line
     * @return result of math calculation
     * @throws IOException when @reader read entered line
     */
    private String getCalcResult(BufferedReader reader) throws IOException {

        System.out.println("Input please your name:");
        String username = reader.readLine();
        System.out.println("Input please a math expression. \n Numbers only or math operators like " + Operator.getAll() + Bracket.getAll());
        String expression = reader.readLine();
            return mathService.calculateAndSave(expression, username);
    }

    /**
     * Opens command panel and returns some chosen action
     *
     * @param reader to read entered line
     * @return concrete user action
     * @throws IOException                 when @reader read entered line
     * @throws UserActionNotFoundException when entered string is not user action
     */
    private UserAction getAction(BufferedReader reader) throws IOException, UserActionNotFoundException {
        StringBuilder builder = new StringBuilder();
         builder.append(UserAction.CALCULATE.getMessage()).append(UserAction.CALCULATE.getCommand())
                .append("\n").append(UserAction.ALL_RESULTS.getMessage()).append(UserAction.ALL_RESULTS.getCommand())
                .append("\n").append(UserAction.ALL_UNIQUE_RESULTS.getMessage()).append(UserAction.ALL_UNIQUE_RESULTS.getCommand())
                .append("\n").append(UserAction.USER_ALL_RESULTS.getMessage()).append(UserAction.USER_ALL_RESULTS.getCommand())
                .append("\n").append(UserAction.USER_UNIQUE_RESULTS.getMessage()).append(UserAction.USER_UNIQUE_RESULTS.getCommand())
                .append("\n").append(UserAction.EXIT.getMessage()).append(UserAction.EXIT.getCommand());
        System.out.println(builder.toString());
        return UserAction.getUserAction(reader.readLine());
    }
}
