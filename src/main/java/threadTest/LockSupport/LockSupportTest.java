package threadTest.LockSupport;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * lockSupport示例:用于指定线程等待
 * https://ifeve.com/locksuppor-source/
 */
public class LockSupportTest {
    private static final ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue();
    private static final ThreadPoolExecutor pool1 = new ThreadPoolExecutor(0, 10, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    public static void main(String[] args) {
        //
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "开始执行！");
                LockSupport.parkNanos(30 * 1000 * 1000);
                System.out.println(Thread.currentThread().getName() + "执行结束！");
            });
            thread.setName("thread " + i);
            pool1.execute(thread);
            queue.add(thread);
        }
        queue.forEach((item) -> {
            try {
                Thread.sleep(new Random().nextInt(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main唤醒：" + item.getName());
            LockSupport.unpark(item);
        });
    }
}
