package threadTest.runnable;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * @Description: 我的测试类
 * @ClassName: MyThreadLocalDemo.java
 * @author ChenYongJia
 * @Date 2019年4月17日 晚上23:28
 * @Email chen87647213@163.com
 */
public class MyThreadLocalDemo {

	private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

	private String getString() {
		return threadLocal.get();
	}

	private void setString(String string) {
		threadLocal.set(string);
	}

	public static void main(String[] args) {
		int threads = 9;
		MyThreadLocalDemo demo = new MyThreadLocalDemo();
		CountDownLatch countDownLatch = new CountDownLatch(threads);
		for (int i = 0; i < threads; i++) {
			Thread thread = new Thread(() -> {
				demo.setString(Thread.currentThread().getName());
				System.out.println("demo.getString()================>" + demo.getString());
				countDownLatch.countDown();
			}, "执行线程 - " + i);
			thread.start();
		}
	}

}
