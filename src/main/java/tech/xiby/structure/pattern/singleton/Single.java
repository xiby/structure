package tech.xiby.structure.pattern.singleton;

/**
 * 单例模式实现
 *
 * @author xiby
 */
public class Single {
    private Single() {

    }

    public static Single getInstance() {
        return SingleHolder.INSTANCE;
    }

    public void show() {
        System.out.println("I am a singleton");
    }

    private static class SingleHolder {
        private static Single INSTANCE = new Single();
    }
}
