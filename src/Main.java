import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //testStream();
        //testStream2();
        //testFunctionalInterface();
        //testMethodReference();
        //testConstructorReference();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
    }

    public static void testStream(){
        List<Product> list = new ArrayList<>();
        list.add(new Product(10,"红色"));
        list.add(new Product(20,"黄色"));
        list.add(new Product(30,"绿色"));
        list.add(new Product(40,"橙色"));
        /*list.stream()
                .filter((p)->p.price<40)//lambda表达式，代表匿名内部类，括号中代表实现的方法的参数，箭头右侧代表实现方法的具体操作，只针对存在一个抽象方法时
                .limit(2)//限制list中的元素个数
                .forEach(System.out::println);*/
        list.stream()
                .map(Product::test)//指定执行的方法(方法必须存在返回值)
                .forEach(System.out::println);//打印方法的返回值
    }
    /*
    1.创建stream
    2.中间操作（过滤、map）
    3.终止操作
    */
    public static void testStream2(){
        // 1，校验通过Collection 系列集合提供的stream()或者paralleStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        // 2.通过Arrays的静态方法stream()获取数组流
        String[] str = new String[10];
        Stream<String> stream2 = Arrays.stream(str);

        // 3.通过Stream类中的静态方法of
        Stream<String> stream3 = Stream.of("aa","bb","cc");

        // 4.创建无限流
        // 迭代
        Stream<Integer> stream4 = Stream.iterate(0,(x) -> x+2);

        //生成
        Stream.generate(() ->Math.random());
    }
    //函数式接口,无需去定义接口
    public static void testFunctionalInterface(){
        //函数式接口
        //1.消费型接口,有参无返回值
        Consumer<Integer> con = (x) -> System.out.println(x);//无需去定义接口，Consumer中已经定义了
        con.accept(1);
        //2.供给型接口，无参有返回值
        Supplier<Integer> sup = () -> 3;
        sup.get();
        //3.Function 《T,R》：:函数式接口，有参有返回值
        Function<Long,Long> fun = (x)->x+1;
        System.out.println(fun.apply(3l));
        //4.Predicate《T》： 断言型接口，有参有返回值，返回值是boolean类型
        Predicate<String> predicate = (str)->str.length()>3;
        System.out.println(predicate.test("1234"));
    }
    //方法引用
    public static void testMethodReference() {
    /*  1. 对象：：实例方法名
        2. 类：：静态方法名
        3. 类：：实例方法名 （lambda参数列表中第一个参数是实例方法的调用 者，第二个参数是实例方法的参数时可用）*/
        Consumer<Integer> con = (x) -> System.out.println(x);
        con.accept(100);
        // 方法引用-对象::实例方法
        Consumer<Integer> con2 = System.out::println;
        con2.accept(100);
        // 方法引用-类名::静态方法名
        BiFunction<Integer, Integer, Integer> biFun = (x, y) -> Integer.compare(x, y);
        BiFunction<Integer, Integer, Integer> biFun2 = Integer::compare;
        Integer result = biFun2.apply(100, 200);
        // 方法引用-类名::实例方法名
        BiFunction<String, String, Boolean> fun1 = (str1, str2) -> str1.equals(str2);
        BiFunction<String, String, Boolean> fun2 = String::equals;
        Boolean result2 = fun2.apply("hello", "world");
        String string = "123";
        Function<String, Boolean> fun3 = string::equals;
        System.out.println(fun3.apply("123"));
    }
    //构造方法引用    类名::new
    public static void testConstructorReference(){
        Supplier<String> supplier = ()->new String("123");
        System.out.println(supplier.get());
        //不带参数
        Supplier<String> supplier2 = String::new;
        System.out.println(supplier2.get());
        // 构造方法引用 类名::new （带一个参数）
        Function<String, String> fun = (x) -> new String(x);
        System.out.println(fun.apply("123"));
        Function<String, String> fun2 = String::new;
        System.out.println(fun2.apply("123"));
        Function<Integer,String[]> fun3 = (x)->new String[x];
        Function<Integer,String[]> fun4 = String[]::new;
        String[] strArrays = fun3.apply(2);
        Arrays.stream(strArrays).forEach(System.out::println);

    }

}
class Product{
    int price;
    String color;

    public Product(int price, String color) {
        this.price = price;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", color='" + color + '\'' +
                '}';
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String test(){
        System.out.println("test");
        return "test return";
    }
}
