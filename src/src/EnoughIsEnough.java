package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class EnoughIsEnough {
    public static int[] deleteNth(int[] elements, int maxOccurrences) {
        Map<Integer, Integer> occurrencesRepeated = new HashMap<>();
        List<Integer> limitedOccurrences = new ArrayList<>();
        for (int element : elements) {
            int occurrences = occurrencesRepeated.getOrDefault(element, 0);
            if (occurrences < maxOccurrences) {
                limitedOccurrences.add(element);
                occurrencesRepeated.put(element, occurrences + 1);
            }
        }
        return limitedOccurrences.stream().mapToInt(Integer::intValue).toArray();
    }

//    --------------------Optimized----------------

    public static int[] deleteNthOptimized(int[] elements, int max) {

        if (max < 1) return new int[0];

        final HashMap<Integer,Integer> map = new HashMap<>();
        final List<Integer> list = new ArrayList<>();

        for (final Integer i : elements) {
            final Integer v = map.put(i, map.getOrDefault(i, 0) + 1);
            if (v == null || v < max) list.add(i);
        }

        return list.stream().mapToInt(i->i).toArray();
    }

    public static int[] deleteNthOptimizedStreams(int[] elements, int maxOccurrences) {
        Map<Integer, Integer> occurrence = new HashMap<>();
        return IntStream.of(elements)
                .filter(motif -> occurrence.merge(motif, 1, Integer::sum) <= maxOccurrences)
                .toArray();
    }
}