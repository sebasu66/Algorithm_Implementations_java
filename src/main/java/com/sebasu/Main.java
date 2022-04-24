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

