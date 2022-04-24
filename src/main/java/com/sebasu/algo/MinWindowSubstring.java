package main.java.com.sebasu.algo;

import java.util.ArrayList;
import java.util.List;

public class MinWindowSubstring {

    private final static int NOT_FOUND = -1;
    private static final int SEARCH_FIRST_CHAR = 0;
    private static final int SEARCH_LAST_CHAR = 1;
    String validWord;
    private final String input;
    private final String pattern;
    private char lastChar;
    private char firstChar;
    //memoization
    private List<String> validMemo;

    public MinWindowSubstring(String input, String pattern) {
        this.input = input;
        this.pattern = pattern;
        validWord = input;
        validMemo = new ArrayList<>();

    }

    public String solve() {
        if (input.length() < pattern.length() || input.length() < 2) return "";
        firstChar = pattern.charAt(0);
        lastChar = pattern.charAt(pattern.length() - 1);
        findMinWindowSubstring();
        return validWord;
    }

    private void findMinWindowSubstring() {
        int tFrom = NOT_FOUND;
        int tTo = NOT_FOUND;
        int scanMode = SEARCH_FIRST_CHAR;
        int l = 0;
        for (int r = 0; r < input.length(); r++) {
            switch (scanMode) {
                case SEARCH_FIRST_CHAR: {
                    if (input.charAt(r) == firstChar) {
                        tFrom = l = r;
                        scanMode = SEARCH_LAST_CHAR;
                    }
                }
                case SEARCH_LAST_CHAR: {
                    if (input.charAt(r) == lastChar) {
                        tTo = r;
                        String term = input.substring(tFrom, tTo + 1);
                        if (isValidTerm(term)) {
                            saveIfShorter(term);
                            l++;
                            // a valid term is found, but is it the shortest?
                            while (l < r) {
                                if (input.charAt(l) == firstChar) {
                                    term = input.substring(l, tTo + 1);
                                    if (isValidTerm(term))
                                        saveIfShorter(term);
                                }
                                l++;
                            }
                            // keep searching for the next valid term
                            scanMode = SEARCH_FIRST_CHAR;
                        }
                    }
                }
            }
        }
    }

    private void saveIfShorter(String term) {
        if (term.length() < validWord.length())
            validWord = term;
    }

    private boolean isValidTerm(String subInput) {
        if (validMemo.contains(subInput)) return true;
        String subPattern = pattern.substring(1, pattern.length() - 1);
        int index = 0;
        for (Character c : subPattern.toCharArray()) {
            index = subInput.indexOf(c, index);
            if (index == NOT_FOUND) return false;
        }
        validMemo.add(subInput);
        return true;
    }

}
