package tech.xiby.structure.pattern.singleton;

import org.junit.Assert;
import org.junit.Test;

public class SingleTest {

    @Test
    public void getInstance() {
        Single single1 = Single.getInstance();
        single1.show();
        Single single2 = Single.getInstance();
        Assert.assertEquals(single1, single2);

        EnumSingle enumSingle1 = EnumSingle.INSTANCE;
        enumSingle1.show();
        EnumSingle enumSingle2 = EnumSingle.INSTANCE;
        Assert.assertEquals(enumSingle1, enumSingle2);
    }
}