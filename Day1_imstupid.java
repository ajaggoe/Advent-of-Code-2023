// package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day1_imstupid {
    private static final Map<String, String> name2digit = new HashMap<>();

    static {
        name2digit.put("one", "1");
        name2digit.put("two", "2");
        name2digit.put("three", "3");
        name2digit.put("four", "4");
        name2digit.put("five", "5");
        name2digit.put("six", "6");
        name2digit.put("seven", "7");
        name2digit.put("eight", "8");
        name2digit.put("nine", "9");
    }

    static String findNumberInString(String substring, boolean considerWords) {
        try {
            Integer.parseInt(substring);
            return substring;
        } catch (NumberFormatException ex) {
            if (considerWords) {
                return name2digit.get(substring);
            }

            return null;
        }
    }

    static int firstForwardMatch(String str, boolean considerWords) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                String maybeMatch = findNumberInString(str.substring(i, j + 1), considerWords);
                if (maybeMatch != null) {
                    return Integer.parseInt(maybeMatch);
                }
            }
        }

        throw new IllegalStateException("Didn't find a match. Invalid string.");
    }

    static int firstReverseMatch(String str, boolean considerWords) {
        for (int i = str.length() - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                String maybeMatch = findNumberInString(str.substring(j, i + 1), considerWords);
                if (maybeMatch != null) {
                    return Integer.parseInt(maybeMatch);
                }
            }
        }

        throw new IllegalStateException("Didn't find a match. Invalid string.");
    }

    static int getDigitFromString(String str, boolean considerWords) {
        int first = firstForwardMatch(str, considerWords);
        int last = firstReverseMatch(str, considerWords);
        return Integer.parseInt(String.format("%d%d", first, last));
    }

    private static int solution(File file, boolean considerWords) {
        int sum = 0;

        int i =0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine() && i < 30) {
                String line = scanner.nextLine();
                System.out.println("Line: "+line);
                if (line.isBlank()) {
                    continue;
                }
                System.out.println(getDigitFromString(line, considerWords));
                sum += getDigitFromString(line, considerWords);
                i++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        

        return sum;
    }

    public static void main(String[] args) {
        File file = new File("input.txt");

        // int partOneSum = solution(file, false);
        // System.out.printf("Part 1 Sum: %d\n", partOneSum);

        int partTwoSum = solution(file, true);
        System.out.printf("Part 2 Sum: %d\n", partTwoSum);
    }
}