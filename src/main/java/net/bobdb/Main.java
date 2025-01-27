package net.bobdb;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
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
        List<Person> persons = List.of(new Person("alice","New York", 21), new Person("bill","Boston", 25), new Person("charles","Boston", 50));
        Collector<Person, StringJoiner, String> personCollector =
                Collector.of(
                        ()-> new StringJoiner(" | "),
                        (j,p)->j.add(p.name().toUpperCase()),
                        StringJoiner::merge,
                        StringJoiner::toString
                );

        String ssss = persons.stream().collect(personCollector);
        System.out.println(ssss);

        //37.  Group list of strings by first character and count them
        List<String> list9 = List.of("abc","agf","aky","bsd","bnw","casd");
        Map<Character, Long> sd= list9.stream().collect(Collectors.groupingBy(x->x.charAt(0), Collectors.counting()));
        System.out.println(sd);

        //38. Convert a list to a map
        Map<String,List<Person>> personcity = persons.stream()
                                    .collect(Collectors.groupingBy(Person::city));
        System.out.println(personcity);

        //39. Multiply array elemnts
        Integer[] arr3 = {1,2,3,4,5};
        int prod = Arrays.stream(arr3).mapToInt(Integer::valueOf).reduce(1,(a,b)->a*b);
        System.out.println(prod);

        //40. stream resumption with supplier
        List<String> names = Arrays.asList("Alice","Bill","Carl","Dick");
        Supplier<Stream<String>> namestream = names::stream;
        namestream.get().forEach(System.out::println);
        long cou = namestream.get().count();
        System.out.println(cou);

        //41. convert a list of strings to uppercase and concat
        String conc = names.stream()
                        .map(String::toUpperCase).reduce((a,b)->a+" "+b).orElse("");
        System.out.println(conc);

        //4-. Flatmap vs Map
        List<ItemContainer> pci = Arrays.asList(
                new ItemContainer(1, List.of("a","b","c")),
                new ItemContainer(2, List.of("d","e","f")),
                new ItemContainer(3, List.of("g","h","i"))
        );
        List<List<String>> persons2 = pci.stream().map(ItemContainer::getPersons).toList();
        System.out.println(persons2);
        List<String> persons3 = pci.stream().flatMap(x->x.getPersons().stream()).toList();
        System.out.println(persons3);

        //42. Concatenate two streams
        Stream<String> ss1 = Stream.of("1", "2", "3");
        Stream<String> ss2 = Stream.of("4", "5", "6");
        Stream<String> ss2all = Stream.concat(ss1,ss2);
        System.out.println(ss2all.toList());

        //43. Get names whos age>30, unique, sorted
        // bad
        var r1 = persons.stream()
                .filter(x->x.age()>30)
                .map(Person::name)
                .distinct()
                .sorted()
                .toList();
        System.out.println(r1);

        //better.  still sucks.
        var r2 = persons.stream()
                .filter(x->x.age()>30)
                .map(Person::name)
                .toList();
        var r3 = r2.stream()
                .distinct().sorted().toList();
        System.out.println(r3);

        //45 fun with Product records
        List<Product> productList = List.of (
                new Product(1,"a",1.23, "A"),
                new Product(2,"b",11.23, "B"),
                new Product(3,"c",111.23, "C")
        );
        System.out.println(productList.stream().filter(x->x.price()<20).map(Product::name).toList());
        System.out.println(productList.stream().map(x->new Product(x.id(), x.name(), x.price()*0.20,x.category())).toList());
        System.out.printf("%.2f\n", productList.stream().mapToDouble(Product::price).average().orElse(0.00));
        System.out.println(productList.stream().min(Comparator.comparing(Product::price)).map(Product::name).orElse(null));

        //46 best practices (more)
        //List of names, find names that start with someting and count
        //message: don't reuse streams
        List<String> l10 = List.of("Aasd", "Asdfsdf", "gfgfg");
        Stream<String> s10 = l10.stream().filter(x->x.startsWith("A"));
        s10.forEach(System.out::println);
        try {
            long c = s10.count();
            System.out.println(c);
        } catch (IllegalStateException e) {
            System.out.println(e);
        }

        //47. Best practices
        Stream<Integer> inf = Stream.iterate(1, x->x+1);
        inf.limit(10).forEach(System.out::println);
        IntStream infs = IntStream.iterate(1, x->x+1);
        infs.limit(10).forEach(System.out::println);

        Stream<Double> rands_incorrect = Stream.iterate(Math.random(), n->Math.random()); //not greate, iterate for definte function
        Stream<Double> rands = Stream.generate(Math::random); //not greate, iterate for definte function
        rands.limit(10).forEach(System.out::println);


    }
}

class ItemContainer {
    int id;
    List<String> persons;

    ItemContainer(int id, List<String> persons) {
        this.id = id;
        this.persons = new ArrayList<>(persons);
    }

    List<String> getPersons() {
        return persons;
    }
}