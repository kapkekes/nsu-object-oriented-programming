package io.github.kapkekes;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** Utility class for methods, based on Z-function of the string. */
public class StringUtilities {
    /**
     * Calculate Z-function of the given string.
     *
     * @param string the string to calculate
     * @return a list of integers
     */
    public static List<Integer> functionZ(String string) {
        int len = string.length();
        int left = 0;
        int right = 0;
        int index;
        List<Integer> z = IntStream.of(0).boxed().collect(Collectors.toList());

        for (index = 1; index < len; index += 1) {
            if (index <= right) {
                z.set(index, Math.min(right - index + 1, z.get(index - left)));
            }

            while (index + z.get(index) < len
                    && string.charAt(z.get(index)) == string.charAt(z.get(index) + index)) {
                z.set(index, z.get(index) + 1);
            }

            if (index + z.get(index) - 1 > right) {
                left = index;
                right = index + z.get(index) - 1;
            }
        }

        return z;
    }

    /**
     * Find the first occurrence of the {@code pattern} in the {@code text} and return it index.
     *
     * @param pattern the pattern to match
     * @param text target
     * @return {@code Optional<Integer>} if there is an occurrence in the text. Otherwise,
     *     {@code Optional.empty()}
     */
    public static Optional<Integer> substringIndex(String pattern, BufferedReader text) {
        var list = new ArrayList<>();
        return Optional.empty();
    }
}
