package lamda;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * lambda基本用法
 */
public class LambdaDemo01 {
    /**
     * 只有一个强制实现方法的接口，使用@FunctionInterface注解标记后，可以使用lambda
     */
    @FunctionalInterface
    interface OneParamFunction {
        /**
         * 平方
         *
         * @param i
         * @return
         */
        int square(int i);
    }

    @FunctionalInterface
    interface TwoParamFuction {
        /**
         * 求和
         *
         * @param i
         * @param j
         * @return
         */
        int sum(int i, int j);
    }


    public static void main(String[] args) {
        /**
         * 自己创建函数接口的方式
         */
        //①单个参数、单行实现的函数，不需要加（）也不需要return，可以直接使用i->i*i的格式
        OneParamFunction f1 = i -> i * i;
        int square = f1.square(2);
        System.out.println("OneParamFunction:" + square);
        //②多个参数，单行实现的函数，需要加上()把参数抱起来，(i,j)->i+j
        TwoParamFuction f2 = (i, j) -> i + j;
        int sum = f2.sum(1, 2);
        System.out.println("TwoParamFuction:" + sum);
        //②lambda参数默认不需要加类型，如果加了类型也要用（）括起来
        OneParamFunction f21 = (int i) -> i * i;
        int square2 = f21.square(2);
        System.out.println("OneParamFunction:" + square2);
        //③多行实现的函数，就需要加{}
        TwoParamFuction f3 = (i, j) -> {
            int m = i + j;
            return m * m;
        };
        System.out.println("TwoParamFuction:" + f3.sum(1, 2));
        /**
         * 在上面的代码中，我们自关心方法的输入输出，其他的都不是我们关心的，因此JDK8在java.util.function这个包中，提供了函数接口，来简化我们的代码
         * UnaryOperator 输入 T 输出 T 一元函数，输入输出类型相同
         * Predicate 输入T 输出 boolean 断言
         * Consumer 输如T 输出 无 消费一个数据，没有输入输出
         * Function<T,R> 输入T 输入R 一个输入一个输出
         * Supplier 输入无 输出T 提供一个数据，没有输入，只有输出
         * BinaryOperator 输入(T,T)输出T，二元函数，输入输出类型相同
         * BiPredicate<L,R> 输入(L,R)输出boolean 两个输入参数的断言
         * BiConsumer<T,U> 输入(T,U) 输出无 两个输入参数的消费者
         * BiFunction<T,U,R> 输入(T，U) 输出R 两个输入，一个输出参数
         */
        //① UnaryOperator 一个输入输出，输入输出类型相同时使用
        UnaryOperator<Integer> up1 = i -> i * i;
        System.out.println("平方=" + up1.apply(2));
        //② predicate 断言,提供了
        Predicate<Integer> p1 = i -> i > 2;
        System.out.println("断言2>2:" + p1.test(2));
        //③Consumer 消费数据
        Consumer<String> c1 = i -> System.out.println("consumer:" + i);
        c1.accept("test");
        //④Supplier
        Supplier<String> s1 = () -> "supplier test";
        System.out.println(s1.get());
        //⑤对于常用类型的函数接口，JDK直接提供了相关的类供我们使用，如IntConsumer LongConsumer

    }
}
