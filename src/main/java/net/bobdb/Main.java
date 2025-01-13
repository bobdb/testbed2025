package net.bobdb;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;


public class Main {
    public static void main(String[] args) {


        // 1. Given a sentence, find the longest word.
        String s = "When it comes to rocking Maiden doesnt screw around";
        String ans = Arrays.stream(s.split(" "))
                .max(Comparator.comparing(String::length))
                .get();
        System.out.println(ans);

        // 2. Remove duplicates from string and return in same order
        String s2 = "asdasdasdgahjsad";
        ans = Arrays.stream(s2.split(""))
                .distinct()
                .collect(Collectors.joining());
        System.out.println(ans);

        // 3. Given a sentence find the 2nd longest word
        ans = Arrays.stream(s.split(" "))
                .sorted(Comparator.comparing(String::length).reversed())
                .skip(1)
                .findFirst()
                .get();
        System.out.println(ans);

        // 4. return the length of the 2nd longest word
        int ans3 = Arrays.stream(s.split(" "))
                .map(String::length)
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .get();
        System.out.println(ans3);

        // 5. Given a sentence, find the occurrences of each word
        String s4 = "When it comes to rocking Maiden doesnt screw around with their rocking";
        Map<String, Long> counts = Arrays.stream(s4.split(" "))
                .collect(groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(counts);

        // 6. Given a sentence find the word(s) with a specified number of vowels
        var l = Arrays.stream(s4.split(" ")).filter(x->x.replaceAll("[^aeiouAEIOU]","").length()==2).toList();
        System.out.println(l);

        // 7. Given integer list, convert into 2 lists with evens and odds
        int[] arr = {1,7,4,6,9,4,2,4,5,8,9,4,5,6,8,4,2,3,1,3,2,8};
        var k = Arrays.stream(arr).boxed().collect(groupingBy(x->x%2==0,Collectors.toList()))
                .values().stream().toList();
        System.out.println(k);

        //8.  Given a word, find the occurence of each characher
        String str = "ASDFASDFASDFASDFASDFASDFASD";
        Map<String, Long> charCount = Arrays.stream(str.split(""))
                .collect(groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(charCount);

        //9. Rearrange int[] to form the highest/lowest possible value (I hate this)
        arr = new int[]{171, 2, 3, 4, 5};
        Arrays.stream(arr).boxed().sorted(Collections.reverseOrder()).forEach(System.out::print);
        System.out.println();
        // how to condense ints into a string to value

        //10.  Given array, find sum of unique integers
        arr = new int[]{1,2,3,4,4,5,3,3,3};
        int sum = Arrays.stream(arr).distinct().sum();
        System.out.println(sum);

        //11.  Find the first nonrepeated character
        final String newString = "ASDFASDFASDFASXDFASDFASDFASD";
        var firstNonZero = Arrays.stream(newString.split("")).filter( a->newString.indexOf(a)==newString.lastIndexOf(a)).findFirst().get();
        System.out.println(firstNonZero);

        firstNonZero =  newString.chars().mapToObj(x->(char)x).collect(groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(aa->aa.getValue()==1)
                .map(m->String.valueOf(m.getKey())).findFirst().get();
        System.out.println(firstNonZero);

        //12. Find first repeated character
        char q = str.chars().mapToObj(x->(char)x).collect(groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet().stream().filter(e->e.getValue()>1).map(Map.Entry::getKey).findFirst().get();
        System.out.println(q);

        //13.  Given an array of integers, group them according to decile
        int[] arr1 = {0,2,3,11,12,18,20,26,35,56,70,71};
        Map<Integer,List<Integer>> appo = Arrays.stream(arr1).boxed()
                .collect(Collectors.groupingBy(x->x/10*10,Collectors.toList()));
        System.out.println(appo);

        //14.  Given a list of strings, return only the integers
        String[] myArray = {"123", "XYZ", "456", "ABC"};
        List<Integer> ans2 = Arrays.stream(myArray).filter(x->x.matches("[0-9]+")).map(Integer::valueOf).toList();
        System.out.println(ans2);

        //
    }
}