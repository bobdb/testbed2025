package net.bobdb;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;


public class Main {
    public static void main(String[] args) {

        // 1. Given a sentence, find the longest word.
        String s = "When it comes to rocking Maiden doesn't screw around";
        String ans = Arrays.stream(s.split(" "))
                .max(Comparator.comparing(String::length))
                .orElse("Get bent");
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
                .orElse("Get bent");
        System.out.println(ans);

        // 4. return the length of the 2nd longest word
        int ans3 = Arrays.stream(s.split(" "))
                .map(String::length)
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(666);
        System.out.println(ans3);

        // 5. Given a sentence, find the occurrences of each word
        String s4 = "When it comes to rocking Maiden doesn't screw around with their rocking";
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

        //8.  Given a word, find the occurrence of each character
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

        //11.  Find the first non-repeated character
        final String newString = "ASDFASDFASDFASXDFASDFASDFASD";
        var firstNonZero = Arrays.stream(newString.split(""))
                .filter( a->newString.indexOf(a)==newString.lastIndexOf(a))
                .findFirst()
                .orElse("Get bent");
        System.out.println(firstNonZero);

        firstNonZero =  newString.chars().mapToObj(x->(char)x).collect(groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(aa->aa.getValue()==1)
                .map(m->String.valueOf(m.getKey())).findFirst().orElse("Get bent");
        System.out.println(firstNonZero);

        //12. Find first repeated character
        char q = str.chars().mapToObj(x->(char)x).collect(groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet().stream().filter(e->e.getValue()>1).map(Map.Entry::getKey)
                .findFirst()
                .orElse('~');
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

        //15.  Product of first 2 elements of array
        int[] arr2 = {2,3,4,5};
        ans3 = Arrays.stream(arr2).boxed().toList().stream().limit(2).reduce(1,(a,b)->a*b);
        System.out.println(ans3);

        //16.  Group anagrams from a list of string
        String[] words = {"ab", "ba", "tap","pat"};
        Collection<List<String>> var5 =
                Arrays.stream(words).collect(Collectors.groupingBy(x -> Arrays.stream(x.toLowerCase().split(""))
                .sorted().collect(Collectors.toList()))).values();
        System.out.println(var5);

        //17. Multiply Alternative numbers in an Array
        int[] arrd = {2, 4, 1, 6, 2, 7, 3, 1, 2};
        ans3 = IntStream.range(0, arrd.length).filter(x->x%2==0).map(x->arrd[x]).reduce(1,(a,b)->a*b);
        System.out.println(ans3);

        //18. Multiply outside in
        int[] arre = {1,2,3,4,5,6};
        int[] ans4 = IntStream.range(0,arre.length/2).map(x->arre[x]*arre[arre.length-x-1]).toArray();
        System.out.println(Arrays.toString(ans4));

        //19. move all zeros to beginning of array
        int[] arrf = {1,0,2,0,3,0,4,5,6,0,8};
        List<Integer> ans5 = Arrays.stream(arrf).boxed().toList().stream()
                .collect(Collectors.partitioningBy(x->x!=0))
                .values().stream().flatMap(Collection::stream).toList();
        System.out.println(ans5);

        //20. return true if array contains all distinct values
        int[] arrg = {1,2,3,4,4,5};
        boolean distinctx = Arrays.stream(arrg).boxed().toList().stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .values().stream().noneMatch(x->x>1);
        System.out.println(distinctx);

        //21. group the strings based on middle character
        String[] argh = {"asa", "ada", "dsd","aea"};
        var midcr = Stream.of(argh).collect(Collectors.groupingBy(x->x.substring(1,2)));
        System.out.println(midcr);

        //22. sum of elements in List
        List<Integer> argi = Arrays.asList(1,2,3,4,5);
        int sumi = argi.stream().mapToInt(Integer::valueOf).sum();
        System.out.println(sumi);

        //23. Sort a list of strings in alphabetical order
        List<String> argj = Arrays.asList("Zasdfasdf","Ysdgsfds","asdasda", "BSDFSDF");
        List<String> srted = argj.stream().sorted(String.CASE_INSENSITIVE_ORDER).toList();
        System.out.println(srted);

        //24. Convert a list of integers to their squares.
        List<Integer> argk = Arrays.asList(1,2,3,4,5);
        List<Integer> sqrs = argk.stream().map(x->x*x).toList();
        System.out.println(sqrs);

        //25. find and print distinct odds
        List<Integer> argl = Arrays.asList(17,4,6,9,2,3,5,8,9,3,3,3,6,1);
        List<Integer> distods = argl.stream().filter(x->x%2==1).distinct().toList();
        System.out.println(distods);

        //26. union of 2 lists
        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
        List<Integer> list2 = List.of(1,2,3,4,5);
        List<Integer> cc = Stream.concat(list1.stream(),list2.stream()).toList();
        System.out.println(cc);

        //27. kth smallest element of list
        List<Integer> list3 = List.of(1,2,3,8,4,5);
        int sk=3;
        int kth = list3.stream().sorted().skip(sk-1).findFirst().orElse(666);
        System.out.println(kth);

        //28. remove all not numeric integers from list of strings
        List<String> list4 = List.of("a1b2g4", "gg3df2", "k9d67d");
        Pattern pattern = Pattern.compile("^[0-9]");
        List<String> ans6 = list4.stream().map(x->pattern.matcher(x).replaceAll("")).toList();
        System.out.println(ans6);

        //29. return a list of the strings that contain only digits
        List<String> list5 = List.of("a1b2g4", "gg3df2", "k9d67d", "43","23","567");
        List<String> ans7 = list5.stream().filter(x->x.matches("\\d+")).toList();
        System.out.println(ans7);

        //30.Convert a list of strings to uppercase
        List<String> list6 = List.of("sdgdfg", "sdfasdf", "dfgfgf","sdfasdf" );
        List<String> ans8 = list6.stream().map(String::toUpperCase).toList();
        System.out.println(ans8);

        //31. Return the average of a list of numbers
        List<Integer> list7 = List.of(1,2,3,4,5);
        double avg = list7.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println(avg);

        //32. Intersection of two lists (integers)
        List<Integer> l1 = List.of(1,2,3,4,5);
        List<Integer> l2 = List.of(3,4,5,6,7);
        List<Integer> xection = l1.stream().filter(l2::contains).toList();
        System.out.println(xection);

        //33. Occurrence of domains
        List<Employee> emps = List.of(new Employee("bob","bob@mail.com"), new Employee("harry","harry@meh.com"));
        var kk = emps.stream().map(x->x.mail().substring(x.mail().indexOf("@"))).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(kk);

        //34.  Fibonacci sequence
        List<Integer> fib = Stream.iterate(new int[] {0,1},f->new int[] {f[1],f[0]+f[1]})
                .limit(10)
                .map(f->f[0]+f[1])
                .toList();
        System.out.println(fib);

        //35. List to List of Squares
        List<Integer> l8 = List.of(2,3,4,5,6);
        List<Integer> sq = l8.stream().mapToInt(Integer::valueOf).map(x->x*x).boxed().toList();
        System.out.println(sq);

        //36. Transform Person stream into a single string consisting of name fields separated by | and capitalized
        List<Person> persons = List.of(new Person("alice"), new Person("bill"), new Person("charles"));

        Collector<Person, StringJoiner, String> personCollector =
                Collector.of(
                        ()-> new StringJoiner(" | "),
                        (j,p)->j.add(p.name().toUpperCase()),
                        StringJoiner::merge,
                        StringJoiner::toString
                );

        String ssss = persons.stream().collect(personCollector);
        System.out.println(ssss);









    }
}