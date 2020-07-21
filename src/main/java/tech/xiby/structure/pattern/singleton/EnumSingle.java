package tech.xiby.structure.pattern.singleton;

/**
 * 枚举单例实现
 *
 * @author xiby
 */
public enum EnumSingle {
    INSTANCE;

    public void show() {
        System.out.println("I am Enum Singleton");
    }
}
