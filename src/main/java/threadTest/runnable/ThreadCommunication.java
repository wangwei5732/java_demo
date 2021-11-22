package threadTest.runnable;

/**
 * 线程通讯
 * 1.使用锁，等待通知机制：notify（） wait（）
 * 2.信号量volatile
 * 3.
 */
public class ThreadCommunication {
    private static Object lock = new Object();

    static class ThreadA implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    System.out.println("A" + Thread.currentThread().getName() + ":" + i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class ThreadB implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    System.out.println("B" + Thread.currentThread().getName() + ":" + i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();

    }
}
