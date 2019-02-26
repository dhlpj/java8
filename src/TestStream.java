import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Jack
 * @create 2018-12-26 23:32
 */
public class TestStream {
    public static void main(String[] args) {
        //Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);
        //Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);
        /*List<Integer> nums = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is:"+
                nums.stream().filter(num -> num != null)
                        .distinct().mapToInt(num -> num * 2)
                        .peek(System.out::println).skip(2).limit(4).sum());*/
        String[] strs = { "aaa", "bbb", "ccc" };
        Stream<String[]> stream = Arrays.stream(strs).map(str -> str.split(""));//Stream<String[]>
        Arrays.stream(strs).map(str -> str.split("")).forEach(x->System.out.print(x.getClass().getName()));// Ljava.lang.String;@53d8d10a
        System.out.println();
        Arrays.stream(strs).map(str -> str.split("")).flatMap(Arrays::stream).forEach(System.out::print);// aaabbbccc
        System.out.println();
        Arrays.stream(strs).map(str -> str.split("")).flatMap(str -> Arrays.stream(str)).forEach(System.out::print);// aaabbbccc
        /*List<Integer> ints = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println("ints sum is:" + ints.stream().reduce(0,(sum, item)->sum + item).get());
        System.out.println(ints.stream().allMatch(item -> item < 100));
        ints.stream().max((o1, o2) -> o1.compareTo(o2)).ifPresent(System.out::println);*/
    }
}
