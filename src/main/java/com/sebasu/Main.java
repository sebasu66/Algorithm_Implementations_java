package main.java.com.sebasu;

import main.java.com.sebasu.algo.BalancedParenthesisCombination;
import main.java.com.sebasu.algo.CityTraffic;
import main.java.com.sebasu.algo.MinWindowSubstring;

import java.util.*;


public class Main {


    public static void main(String[] args) {

        BalancedParenthesisCombination parenthesisCombination = new BalancedParenthesisCombination();
        List<String> parenthesis = parenthesisCombination.generateParenthesis(5);
        System.out.println(parenthesis.size() + "  " + parenthesis);

        System.out.println( new MinWindowSubstring("ABDOBECODEBANCBCADEASDFDGASDFERVCBVCBACBADFGHBC", "ABDC").solve());

        String[] nodes = new String[]{"1:[5]", "2:[5,15,7]", "3:[5]", "4:[5]", "5:[1,2,3,4]", "7:[2,8]", "8:[7,38]", "15:[2]", "38:[8]"};

        String commaSeparatedNodes = new CityTraffic(nodes).solve();
        System.out.println(commaSeparatedNodes);
    }




    //Write a program that takes as input a number n and returns all the strings with n matched pairs of brackets.
    public static List<String> generateBrackets(int n) {
        List<String> result = new ArrayList<>();
        generateBrackets(n, n, "", result);
        return result;
    }

    private static void generateBrackets(int left, int right, String s, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(s);
            return;
        }
        if (left > 0) {
            generateBrackets(left - 1, right, s + "[", result);
        }
        if (right > left) {
            generateBrackets(left, right - 1, s + "]", result);
        }
    }

    //Write a program that takes as input a number n and returns all the strings with n matched pairs of braces.
    public static List<String> generateBraces(int n) {
        List<String> result = new ArrayList<>();
        generateBraces(n, n, "", result);
        return result;
    }

    private static void generateBraces(int left, int right, String s, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(s);
            return;
        }
        if (left > 0) {
            generateBraces(left - 1, right, s + "{", result);
        }
        if (right > left) {
            generateBraces(left, right - 1, s + "}", result);
        }
    }


    //factorial using memoization
    public static int factorial(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return factorial(n, memo);
    }

    public static int factorial(int n, int[] memo) {
        if (n == 0) return 1;
        if (memo[n] != -1) return memo[n];
        memo[n] = n * factorial(n - 1, memo);
        return memo[n];
    }
}

