package personal.kg.learning.concepts;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by gyanc on 7/29/2018.
 */
public class LearnSet {
    public static void main(String... args) {
        String[] data = new String[]{"this", "is", "a", "unique", "collection", "set", "this", "is", "a", "unique", "collection", "set"};
        sampleSet(data);
        sampleHashSet(data);
        sampleLinkedHashSet(data);
        sampleTreeSet(data);
        experimentToCollection(data);
        experimentViaList(data);
        experimentViaArray();

    }

    private static void experimentToCollection(String[] data) {

        System.out.println("Experiment with toCollection" + Arrays.stream(data).collect(Collectors.toCollection(TreeSet::new)));

    }

    private static void experimentViaList(String[] data) {

        System.out.println("Experiment via List" + new TreeSet<>(Arrays.stream(data).collect(Collectors.toList())));

    }

    private static void experimentViaArray() {

        System.out.println("Experiment via Array"+ new LinkedHashSet<>(Arrays.asList(new String[]{
                "the",
                "order"
                ,
                "is", "preserved", "duplicates","are","not"
        })));

    }

    private static void sampleTreeSet(String[] data) {

        System.out.println("TreeSet" + new TreeSet<>(Arrays.stream(data).unordered().collect(Collectors.toSet())));

    }

    private static void sampleLinkedHashSet(String[] data) {
        System.out.println("LinkedHashSet" + new LinkedHashSet<>(Arrays.stream(new String[]{
                "the",
                "order",
                "the",
                "is", "preserved", "duplicates","are","not"
        }).collect(Collectors.toSet())));

    }

    private static void sampleHashSet(String[] data) {
        System.out.println("HashSet" + new HashSet<>(Arrays.stream(data).unordered().collect(Collectors.toSet())));
    }

    private static void sampleSet(String[] data) {
        System.out.println("Set" + Arrays.stream(data).unordered().collect(Collectors.toSet()));
    }
}
