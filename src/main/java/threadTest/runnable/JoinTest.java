package threadTest.runnable;

/**
 * join
 */
public class JoinTest {
    private static class ThreadA extends Thread {

        @Override
        public void run() {
            System.out.println("ThreadA sleep ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadA  wake");
        }
    }

    private static class ThreadB extends Thread {

        @Override
        public void run() {
            System.out.println("ThreadB sleep ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadB  wake");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadA threadA = new ThreadA();
        threadA.start();
        threadA.join();
        System.out.println("我等ThreadA wake 才执行");

        ThreadB threadB = new ThreadB();
        threadB.start();
        System.out.println("我不等ThreadB wake 了");

    }
}
