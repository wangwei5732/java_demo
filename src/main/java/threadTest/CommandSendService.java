package threadTest;

import threadTest.runnable.CommandSendTask;

import java.util.concurrent.*;

public class CommandSendService {
    public static ConcurrentHashMap<String, Thread> threadMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // 使用
        ExecutorService executor = Executors.newCachedThreadPool();
        CommandSendTask commandSendTask = new CommandSendTask();
        FutureTask<String> futureTask = new FutureTask<>(commandSendTask);
        executor.submit(futureTask);

        //10s内唤醒
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread thread1 = threadMap.get("123456");
                synchronized (thread1) {
                    System.out.println("10s内反馈消息了！");
                    threadMap.get("123456").notify();

                }
            }
        }).start();
        String result = "";
        try {
            result = futureTask.get(10, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("超时未返回！");
        } catch (Exception e) {
            System.out.println("执行异常");
        }
        System.out.println("get :" + result);
        threadMap.remove("123456");

//        //10s内唤醒
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Thread thread1 = threadMap.get("123456");
//                if (thread1 != null) {
//                    synchronized (thread1) {
//                        System.out.println("10s内反馈消息了！");
//                        threadMap.get("123456").notify();
//                    }
//
//                }
//            }
//        }).start();

    }
}
