package main.java.com.sebasu.algo;

import java.util.HashMap;
import java.util.Map;

/*
The Utopian Tree goes through 2 cycles of growth every year. Each spring, it doubles in height.
Each summer, its height increases by 1 meter.
A Utopian Tree sapling with a height of 1 meter is planted at the onset of spring.
How tall will the tree be after n growth cycles?
For example, if the number of growth cycles is 5, the calculations are as follows:

        Period  Height
        0          1
        1          2
        2          3
        3          6
        4          7
        5          14
        6          15


UtopianTree has the following parameter(s):
 int n: the number of growth cycles to simulate
 int: the height of the tree after the given number of cycles

 Input Format
The first line contains an integer, t, the number of test cases.
subsequent lines each contain an integer,n , the number of cycles for that test case.
Constraints

Sample Input       -->         Sample Output

      3
      0                             1
      1                             2
      4                             7

*/
public class UtopianTree {

    private static final Map<Integer, Integer> memo = new HashMap<>();

    /**
     * This function will return the height of the tree after the n cycles.<br>
     * Formula:<br>
     * 1. if 0 cycles, height of the tree = 1.<br>
     * 2. if even cycle, height of the tree = 2^((n/2)+1) - 1.<br>
     * 3. if odd cycle, height of the tree = height(n+1) - 1.<br>
     *<br>
     * Use memoization to store and reuse calculations.<br>
     * Could also be done using recursion, but this is more efficient.<br>
     *<br>
     * @param n : number of cycles
     * @return : height of the tree after the given cycles
     */
    public static int utopianTree(int n) {
        int height = 1;
        if (n == 0) {
            return height;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        if (n % 2 == 0) {
            height = (int) Math.pow(2, ((n/2) + 1)) - 1;
        } else {
            height = utopianTree(n + 1) - 1;
        }
        memo.put(n, height);
        return height;
    }
}
