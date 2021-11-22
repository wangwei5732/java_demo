package threadTest.runnable;

public class RunnableTest {
    public static void main(String[] args) {
        System.out.println("main thread " + Thread.currentThread().getName()+" start");
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lamda");
        }).start();
        System.out.println("main thread " + Thread.currentThread().getName()+" end");

    }
}
