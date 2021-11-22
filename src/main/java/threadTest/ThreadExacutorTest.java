package threadTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExacutorTest {
    // 线程池核心线程数量
    private final static int CORE_POOL_SIZE = 2;
    // 线程池最大线程数量
    private final static int MAX_POOL_SIZE = 3;
    // 允许的空闲时间（10s）
    private final static long KEEP_ALIVE_TIME = 0L;
    // 线程池所使用的缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 1;
    // 创建线程池
    private static final ThreadPoolExecutor tp = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue(WORK_QUEUE_SIZE), new ThreadPoolExecutor.CallerRunsPolicy());


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i+"======");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "run--------------------");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "thread" + Math.random());
            tp.execute(thread);
        }
    }
}
