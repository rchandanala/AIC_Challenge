import com.google.common.collect.ImmutableList;

import java.util.Scanner;
import java.util.Stack;

public class BalancedBrackets {
    private static final ImmutableList<Character> BRACKETS = ImmutableList.of('{', '[', '(', ')', ']', '}');
    private static final ImmutableList<Character> CLOSING_BRACKETS = ImmutableList.of(')', ']', '}');
    private static final ImmutableList<Character> OPENING_BRACKETS = ImmutableList.of('{', '[', '(');

    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner input = new Scanner(System.in);

        String s;
        if (input.hasNext()) {
            s = input.nextLine();
            if (isBalanced(s)) {
                System.out.print('Y');
            } else {
                System.out.print('N');
            }
            System.out.print(' ');
            s.chars().mapToObj(i -> (char) i).filter(BalancedBrackets::isBracket).forEach(c -> System.out.print(c));
        }
    }

    private static boolean isBalanced(String expression) {
        Stack<Character> validateBrackets = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (isClosingBracket(c)) {
                if (validateBrackets.isEmpty()) {
                    return false;
                }
                Character top = validateBrackets.pop();
                if (!matchingBrackets(c, top)) {
                    return false;
                }
            } else if (isOpeningBracket(c)) {
                validateBrackets.push(c);
            }
        }
        return validateBrackets.isEmpty();
    }

    private static boolean matchingBrackets(char closing, char opening) {
        return (closing == ')' && opening == '(') || (closing == '}' && opening == '{') || (closing == ']' && opening == '[');
    }

    private static boolean isBracket(char c) {
        return BRACKETS.contains(c);
    }

    private static boolean isOpeningBracket(char c) {
        return OPENING_BRACKETS.contains(c);
    }

    private static boolean isClosingBracket(char c) {
        return CLOSING_BRACKETS.contains(c);
    }
}

