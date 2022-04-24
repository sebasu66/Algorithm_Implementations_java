package main.java.com.sebasu.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function to generate all possible n pairs of balanced parentheses.
 *
 * Examples:
 *
 * Input: n=1
 * Output: {}
 *
 * Input : n=2
 * Output:
 * {}{},{{}}
 *
 * Input: n=3
 * Output:
 * {}{}{},{{}{}},{{{}}},{{}}{},{}{{}}
 *
 * @author sebasu
 */
public class BalancedParenthesisCombination {

    public BalancedParenthesisCombination() {
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesis(n, n, "", result);
        return result;
    }

    /**
     * Generate all possible n pairs of balanced parentheses.
     * this function will recursively call itself and ask:
     * "how many left parenthesis can I still add?" -> add a left parenthesis to temp, decrement left,
     * "are remaining right parenthesis more than left ?" -> add a right parenthesis to temp, decrement right
     * "no more left nor right parenthesis can be added?" -> add temp to result list
     *
     * @param left : left parenthesis "(" left to generate (initially n)
     * @param right: right parenthesis ")" left to generate (initially n)
     * @param temp  : holds the current combination until its completed
     * @param result: list of all possible combinations
     */
    private void generateParenthesis(int left, int right, String temp, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(temp);
            return;
        }
        if (left > 0) {
            generateParenthesis(left - 1, right, temp + "(", result);
        }
        if (right > left) {
            generateParenthesis(left, right - 1, temp + ")", result);
        }
    }

}
