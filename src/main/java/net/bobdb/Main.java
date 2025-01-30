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
        String s1 = "When it comes to rocking Maiden doesn't screw around";
        String ans1 = Arrays.stream(s1.split(" "))
                .max(Comparator.comparing(String::length))
                .orElse("Get bent");
        System.out.println(ans1);

        // 2. Remove duplicates from string and return in same order
        String s2 = "asdasdasdgahjsad";
        String ans2 = Arrays.stream(s2.split(""))
                .distinct()
                .collect(Collectors.joining());
        System.out.println(ans2);

        // 3. Given a sentence find the 2nd longest word
        String ans3 = Arrays.stream(s1.split(" "))
                .sorted(Comparator.comparing(String::length).reversed())
                .skip(1)
                .findFirst()
                .orElse("Get bent");
        System.out.println(ans3);

        // 4. return the length of the 2nd longest word
        int ans4 = Arrays.stream(s1.split(" "))
                .map(String::length)
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(666);
        System.out.println(ans4);

        // 5. Given a sentence, find the occurrences of each word
        String s5 = "When it comes to rocking Maiden doesn't screw around with their rocking";
        Map<String, Long> ans5 = Arrays.stream(s5.split(" "))
                .collect(groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(ans5);

        // 6. Given a sentence find the word(s) with a specified number of vowels
        var ans6 = Arrays.stream(s5.split(" ")).filter(x->x.replaceAll("[^aeiouAEIOU]","").length()==2).toList();
        System.out.println(ans6);

        // 7. Given integer list, convert into 2 lists with evens and odds
        int[] arr7 = {1,7,4,6,9,4,2,4,5,8,9,4,5,6,8,4,2,3,1,3,2,8};
        var ans7 = Arrays.stream(arr7).boxed().collect(groupingBy(x->x%2==0,Collectors.toList()))
                .values().stream().toList();
        System.out.println(ans7);

        //8.  Given a word, find the occurrence of each character
        String s8 = "ASDFASDFASDFASDFASDFASDFASD";
        Map<String, Long> ans8 = Arrays.stream(s8.split(""))
                .collect(groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(ans8);

        //9. Rearrange int[] to form the highest/lowest possible value (I hate this)
        int[] a9 = {171, 2, 3, 4, 5};
        Arrays.stream(a9).boxed().sorted(Collections.reverseOrder()).forEach(System.out::print);
        System.out.println();

        //10.  Given array, find sum of unique integers
        int[] a10 = {1,2,3,4,4,5,3,3,3};
        int sum = Arrays.stream(a10).distinct().sum();
        System.out.println(sum);

        //11.  Find the first non-repeated character
        String s11 = "ASDFASDFASDFASXDFASDFASDFASD";
        var ans11 = Arrays.stream(s11.split(""))
                .filter( a->s11.indexOf(a)==s11.lastIndexOf(a))
                .findFirst()
                .orElse("Get bent");
        System.out.println(ans11);
        ans11 =  s11.chars().mapToObj(x->(char)x).collect(groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(aa->aa.getValue()==1)
                .map(m->String.valueOf(m.getKey())).findFirst().orElse("Get bent");
        System.out.println(ans11);

        //12. Find first repeated character
        char ans12 = s11.chars().mapToObj(x->(char)x).collect(groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet().stream().filter(e->e.getValue()>1).map(Map.Entry::getKey)
                .findFirst()
                .orElse('~');
        System.out.println(ans12);

        //13.  Given an array of integers, group them according to decile
        int[] arr13 = {0,2,3,11,12,18,20,26,35,56,70,71};
        Map<Integer,List<Integer>> ans13 = Arrays.stream(arr13).boxed()
                .collect(Collectors.groupingBy(x->x/10*10,Collectors.toList()));
        System.out.println(ans13);

        //14.  Given a list of strings, return only the integers
        String[] arr14 = {"123", "XYZ", "456", "ABC"};
        List<Integer> ans14 = Arrays.stream(arr14).filter(x->x.matches("[0-9]+")).map(Integer::valueOf).toList();
        System.out.println(ans14);

        //15.  Product of first 2 elements of array
        int[] arr15 = {2,3,4,5};
        int ans15 = Arrays.stream(arr15).boxed().toList().stream().limit(2).reduce(1,(a,b)->a*b);
        System.out.println(ans15);

        //16.  Group anagrams from a list of string
        String[] arr16 = {"ab", "ba", "tap","pat"};
        Collection<List<String>> ans16 =
                Arrays.stream(arr16).collect(Collectors.groupingBy(x -> Arrays.stream(x.toLowerCase().split(""))
                .sorted().collect(Collectors.toList()))).values();
        System.out.println(ans16);

        //17. Multiply Alternative numbers in an Array
        int[] arr17 = {2, 4, 1, 6, 2, 7, 3, 1, 2};
        int ans17 = IntStream.range(0, arr17.length).filter(x->x%2==0).map(x->arr17[x]).reduce(1,(a,b)->a*b);
        System.out.println(ans17);

        //18. Multiply outside in
        int[] arr18 = {1,2,3,4,5,6};
        int[] ans18 = IntStream.range(0,arr18.length/2).map(x->arr18[x]*arr18[arr18.length-x-1]).toArray();
        System.out.println(Arrays.toString(ans18));

        //19. move all zeros to beginning of array
        int[] arr19 = {1,0,2,0,3,0,4,5,6,0,8};
        List<Integer> ans19 = Arrays.stream(arr19).boxed().toList().stream()
                .collect(Collectors.partitioningBy(x->x!=0))
                .values().stream().flatMap(Collection::stream).toList();
        System.out.println(ans19);

        //20. return true if array contains all distinct values
        int[] arr20 = {1,2,3,4,4,5};
        boolean ans20 = Arrays.stream(arr20).boxed().toList().stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .values().stream().noneMatch(x->x>1);
        System.out.println(ans20);

        //21. group the strings based on middle character
        String[] arr21 = {"asa", "ada", "dsd","aea"};
        var ans21 = Stream.of(arr21).collect(Collectors.groupingBy(x->x.substring(1,2)));
        System.out.println(ans21);

        //22. sum of elements in List
        List<Integer> arr22 = Arrays.asList(1,2,3,4,5);
        int ans22 = arr22.stream().mapToInt(Integer::valueOf).sum();
        System.out.println(ans22);

        //23. Sort a list of strings in alphabetical order
        List<String> arr23 = Arrays.asList("Zasdfasdf","Ysdgsfds","asdasda", "BSDFSDF");
        List<String> ans23 = arr23.stream().sorted(String.CASE_INSENSITIVE_ORDER).toList();
        System.out.println(ans23);

        //24. Convert a list of integers to their squares.
        List<Integer> arr24 = Arrays.asList(1,2,3,4,5);
        List<Integer> ans24 = arr24.stream().map(x->x*x).toList();
        System.out.println(ans24);

        //25. find and print distinct odds
        List<Integer> arr25 = Arrays.asList(17,4,6,9,2,3,5,8,9,3,3,3,6,1);
        List<Integer> ans25 = arr25.stream().filter(x->x%2==1).distinct().toList();
        System.out.println(ans25);

        //26. union of 2 lists
        List<Integer> arr261 = Arrays.asList(1,2,3,4,5);
        List<Integer> arr262= List.of(1,2,3,4,5);
        List<Integer> ans26 = Stream.concat(arr261.stream(),arr262.stream()).toList();
        System.out.println(ans26);

        //27. kth smallest element of list
        List<Integer> arr27 = List.of(1,2,3,8,4,5);
        int skip=3;
        int ans27 = arr27.stream().sorted().skip(skip-1).findFirst().orElse(666);
        System.out.println(ans27);

        //28. remove all not numeric integers from list of strings
        List<String> arr28 = List.of("a1b2g4", "gg3df2", "k9d67d");
        Pattern pattern = Pattern.compile("^[0-9]");
        List<String> ans28 = arr28.stream().map(x->pattern.matcher(x).replaceAll("")).toList();
        System.out.println(ans28);

        //29. return a list of the strings that contain only digits
        List<String> arr29 = List.of("a1b2g4", "gg3df2", "k9d67d", "43","23","567");
        List<String> ans29 = arr29.stream().filter(x->x.matches("\\d+")).toList();
        System.out.println(ans29);

        //30.Convert a list of strings to uppercase
        List<String> arr30 = List.of("sdgdfg", "sdfasdf", "dfgfgf","sdfasdf" );
        List<String> ans30 = arr30.stream().map(String::toUpperCase).toList();
        System.out.println(ans30);

        //31. Return the average of a list of numbers
        List<Integer> arr31 = List.of(1,2,3,4,5);
        double ans31 = arr31.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println(ans31);

        //32. Intersection of two lists (integers)
        List<Integer> arr321 = List.of(1,2,3,4,5);
        List<Integer> arr322 = List.of(3,4,5,6,7);
        List<Integer> ans32 = arr321.stream().filter(arr322::contains).toList();
        System.out.println(ans32);

        //33. Occurrence of domains
        List<Employee> arr33 = List.of(new Employee("bob","bob@mail.com", 32, "M"), new Employee("harry","harry@meh.com", 45, "M"), new Employee("carol","carol@meh.com", 43, "F"));
        var ans33 = arr33.stream().map(x->x.mail().substring(x.mail().indexOf("@"))).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(ans33);

        //34.  Fibonacci sequence
        List<Integer> ans34 = Stream.iterate(new int[] {0,1},f->new int[] {f[1],f[0]+f[1]})
                .limit(10)
                .map(f->f[0]+f[1])
                .toList();
        System.out.println(ans34);

        //35. List to List of Squares
        List<Integer> arr35 = List.of(2,3,4,5,6);
        List<Integer> ans35 = arr35.stream().mapToInt(Integer::valueOf).map(x->x*x).boxed().toList();
        System.out.println(ans35);

        //36. Transform Person stream into a single string consisting of name fields separated by | and capitalized
        List<Person> persons = List.of(new Person("alice","New York", 21), new Person("bill","Boston", 25), new Person("charles","Boston", 50));
        Collector<Person, StringJoiner, String> personCollector =
                Collector.of(
                        ()-> new StringJoiner(" | "),
                        (j,p)->j.add(p.name().toUpperCase()),
                        StringJoiner::merge,
                        StringJoiner::toString
                );

        String ans36 = persons.stream().collect(personCollector);
        System.out.println(ans36);

        //37.  Group list of strings by first character and count them
        List<String> arr37 = List.of("abc","agf","aky","bsd","bnw","casd");
        Map<Character, Long> ans37 = arr37.stream().collect(Collectors.groupingBy(x->x.charAt(0), Collectors.counting()));
        System.out.println(ans37);

        //38. Convert a list to a map
        Map<String,List<Person>> ans38 = persons.stream()
                                    .collect(Collectors.groupingBy(Person::city));
        System.out.println(ans38);

        //39. Multiply array elemnts
        Integer[] arr39 = {1,2,3,4,5};
        int ans39 = Arrays.stream(arr39).mapToInt(Integer::valueOf).reduce(1,(a,b)->a*b);
        System.out.println(ans39);

        //40. stream resumption with supplier
        List<String> names = Arrays.asList("Alice","Bill","Carl","Dick");
        Supplier<Stream<String>> namestream = names::stream;
        namestream.get().forEach(System.out::println);
        long ans40 = namestream.get().count();
        System.out.println(ans40);

        //41. convert a list of strings to uppercase and concat
        String ans41 = names.stream()
                        .map(String::toUpperCase).reduce((a,b)->a+" "+b).orElse("");
        System.out.println(ans41);

        //4-. Flatmap vs Map
        List<ItemContainer> itemContainerList = Arrays.asList(
                new ItemContainer(1, List.of("a","b","c")),
                new ItemContainer(2, List.of("d","e","f")),
                new ItemContainer(3, List.of("g","h","i"))
        );
        List<List<String>> ans4x1 = itemContainerList.stream().map(ItemContainer::getPersons).toList();
        System.out.println(ans4x1);
        List<String> ans4x2 = itemContainerList.stream().flatMap(x->x.getPersons().stream()).toList();
        System.out.println(ans4x2);

        //42. Concatenate two streams
        Stream<String> arr421 = Stream.of("1", "2", "3");
        Stream<String> arr422 = Stream.of("4", "5", "6");
        Stream<String> ans42 = Stream.concat(arr421,arr422);
        System.out.println(ans42.toList());

        //43. Get names whos age>30, unique, sorted
        // bad
        var arr43 = persons.stream()
                .filter(x->x.age()>30)
                .map(Person::name)
                .distinct()
                .sorted()
                .toList();
        System.out.println(arr43);

        //better.  still sucks.
        var ans442 = persons.stream()
                .filter(x->x.age()>30)
                .map(Person::name)
                .toList();
        var ans443 = ans442.stream()
                .distinct().sorted().toList();
        System.out.println(ans443);

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
        List<String> arr46 = List.of("Aasd", "Asdfsdf", "gfgfg");
        Stream<String> s10 = arr46.stream().filter(x->x.startsWith("A"));
        s10.forEach(System.out::println);
//        try {
//            long c = s10.count();
//            System.out.println(c);
//        } catch (IllegalStateException e) {
//            System.out.println(e);
//        }

        //4-. Best practices
        Stream<Integer> inf = Stream.iterate(1, x->x+1);
        inf.limit(10).forEach(System.out::println);
        IntStream infs = IntStream.iterate(1, x->x+1);
        infs.limit(10).forEach(System.out::println);

        Stream<Double> rands_incorrect = Stream.iterate(Math.random(), _ ->Math.random()); //not great, iterate for definte function
        System.out.println(rands_incorrect.limit(10).toList());
        Stream<Double> rands = Stream.generate(Math::random); //not great, iterate for definite function
        rands.limit(10).forEach(System.out::println);

        //47. Avg age of male and female employees
        var ans47 = arr33.stream().collect(Collectors.groupingBy(Employee::gender,Collectors.averagingInt(Employee::age)));
        System.out.println(ans47);

        //48 Middle character of string
        String s48 = "123456";
        int len = s48.length();
        int mid = len/2;
        String ans481s = Arrays.stream(s48.split("")).skip(s48.length()/2).findFirst().orElse("something went wrong");
        System.out.println(ans481s);
        var ans482 = IntStream.range(0,len)
                .filter(x->len%2==0 ? (x==mid||x==mid-1) : (x==mid)  )
                .mapToObj(s48::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString() ;
        System.out.println(ans482);

        //49 distinct numbers in descending order which starts with 1
        int[] arr49 = {12, 34, 11, 1243, 45, 23, 1, 143};
        List<Integer> ans49 = Arrays.stream(arr49)
                .distinct()
                .filter(x->String.valueOf(x).startsWith("1"))
                .boxed()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println(ans49);

        //51 find salaries over something, and then increase
        Employee2 e51 = new Employee2("sdfsf","Asdasd",12,"M");
        Employee2 e52 = new Employee2("sdfsf","Asdasd",72,"M");
        List<Employee2> le2 = new ArrayList<>();
        le2.add(e51);
        le2.add(e52);
        List<Employee2> le3 = le2.stream().filter(x->x.getAge()>20).peek(x->x.setAge(123)).toList();
        System.out.println(le3);
        System.out.println(le2); // original modified...single underlying array and mutable

        List<Employee> lemp = arr33.stream().filter(x->x.age()>40)
             //   .peek(x->x.age()+1)
                .toList();
        System.out.println(lemp);

        // 51 Sort by name and email
        List<Employee> ans51 = arr33.stream().sorted(Comparator.comparing(Employee::name).thenComparing(Employee::mail)).toList();
        System.out.println(ans51);

        // 52 Count of a particular substring
        String s3 = "hellohellohelhellooooheo";
        String ck = "hello";
        long k4 = IntStream.range(0, s3.length()-ck.length()-1)
                .filter(x-> s3.startsWith(ck, x))
                .count();
        System.out.println(k4);

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