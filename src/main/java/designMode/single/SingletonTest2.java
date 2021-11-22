package designMode.single;

/**
 * 单例模式-懒汉模式-第一次使用时初始化
 */
public class SingletonTest2 {
    private static SingletonTest2 singletonTest = null;

    /**
     * 单线程模式（线程不安全，不推荐）
     *
     * @return
     */
    public SingletonTest2 getInstanceA() {
        if (singletonTest == null) {
            singletonTest = new SingletonTest2();
        }
        return SingletonTest2.singletonTest;
    }

    /**
     * 适合多线程（效率低，不推荐）
     *
     * @return
     */
    public synchronized SingletonTest2 getInstanceB() {
        if (singletonTest == null) {
            singletonTest = new SingletonTest2();
        }
        return SingletonTest2.singletonTest;
    }

    /**
     * 适合多线程（某些情况下可能会有问题）
     *
     * @return
     */
    public SingletonTest2 getInstanceC() {
        if (singletonTest == null) {
            synchronized (singletonTest) {
                if (singletonTest == null) {
                    singletonTest = new SingletonTest2();
                }
            }
        }
        return SingletonTest2.singletonTest;
    }

    /**
     * 内部类实现（推荐）
     *
     * @return
     */
    private static class InstanceHolder {
        private static final SingletonTest2 singletonTest = new SingletonTest2();
    }

    public SingletonTest2 getInstanceD() {
        return InstanceHolder.singletonTest;
    }

}
