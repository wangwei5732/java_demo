package stream;

import java.util.concurrent.CountDownLatch;

public class Test {
    public static void main(String[] args) {
        long functionInterval = 3600000;
        long beginTime = 1688452558467L;
        long endTime = 1688536465309L;

        //计算拆分数
        int count = (endTime - beginTime) % functionInterval == 0 ? 0 : 1;
        count += (endTime - beginTime) / functionInterval;
        //计数器，等待所有计算完成后才继续执行后续方法
        CountDownLatch latch = new CountDownLatch(count);
        System.out.println(count);
        int m = 0;
        for (long i = beginTime; i < endTime; i += functionInterval) {
            m++;
            System.out.println(m);
        }
    }
}
