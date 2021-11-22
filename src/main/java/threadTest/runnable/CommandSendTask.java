package threadTest.runnable;

import threadTest.CommandSendService;

import java.util.concurrent.Callable;

public class CommandSendTask implements Callable {
    @Override
    public Object call() throws Exception {
        Thread thread = Thread.currentThread();
        synchronized (thread) {
            CommandSendService.threadMap.put("123456", Thread.currentThread());
            System.out.println("发送指令！");
            thread.wait(15000);
        }
        return "指令发送结果：success！";
    }
}
