package threadTest.runnable;

import java.util.concurrent.*;

public class CallableTest {
    public static void main(String[] args) {
        //1.使用callable获取线程返回值
//        test1();
        //2.使用futureTask取消任务
        test2();
    }

    /**
     * 1.使用callable获取线程返回值
     */
    public static void test1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> submit = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Thread：" + Thread.currentThread().getName() + "running");
                Thread.sleep(1000);
                return 2;
            }
        });
        //这里future的实现为：class java.util.concurrent.FutureTask
        System.out.println(submit.getClass());
        System.out.println("Thread：" + Thread.currentThread().getName() + "running");
        try {
            Integer integer = submit.get(2000, TimeUnit.MILLISECONDS);
            System.out.println("callable result:" + integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * futureTask
     * 首先，调用submit方法是没有返回值的。这里实际上是调用的submit(Runnable task)方法，而上面的Demo，调用的是submit(Callable<T> task)方法。
     *
     * 然后，这里是使用FutureTask直接取get取值，而上面的Demo是通过submit方法返回的Future去取值。
     */
    public static void test2() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> {
            System.out.println("Thread :" + Thread.currentThread().getName() + "");
            Thread.sleep(1000);
            return 2;
        });
        executorService.submit(futureTask);
        System.out.println("Thread：" + Thread.currentThread().getName() + " running");
        try {
            Integer integer = futureTask.get(3000, TimeUnit.MILLISECONDS);
            System.out.println("futureTask result:" + integer);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
