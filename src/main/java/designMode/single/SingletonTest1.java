package designMode.single;

/**
 * 单例模式-饿汉模式-类加载就初始化单例
 */
public class SingletonTest1 {
    private static SingletonTest1 singletonTest1 = new SingletonTest1();

    public SingletonTest1 getInstance() {
        return SingletonTest1.singletonTest1;
    }
}
