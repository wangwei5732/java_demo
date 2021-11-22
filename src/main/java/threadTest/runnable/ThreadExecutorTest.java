package threadTest.runnable;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExecutorTest {
    public static void main(String[] args) {
        new ThreadExecutorTest().test1();
    }

    //核心线程为0，线程总数为Integer.Max_value，60s
    private void test1() {
        //核心线程为0，线程总数为Integer.Max_value，60s
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        for (int i = 0; i < 50; i++) {
            ThreadTest threadTest = new ThreadTest();
            threadTest.start();
            System.out.println("getActiveCount:" + threadPoolExecutor.getActiveCount() + "getQueue.size:" + threadPoolExecutor.getQueue().size());
        }
    }

    private static class ThreadTest extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getThreadGroup().getName() + ":" + Thread.currentThread().getName() + ":running");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
