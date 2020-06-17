package tech.xiby.structure.algorithm.lru;

import org.junit.Assert;
import org.junit.Test;

public class LruCacheTest {

    @Test
    public void getOrInsert() {
        LruCache<Integer, Integer> cache = new LruCache<>(3, (node) -> System.out.println(node.getData().getKey()));
        cache.getOrInsert(1, 2);
        Assert.assertEquals(1, cache.getCurrentSize());
        cache.getOrInsert(1, 4);
        cache.getOrInsert(2, 3);
        Assert.assertEquals(cache.getCurrentSize(), 2);
        cache.getOrInsert(3, 4);
        Assert.assertEquals(3, cache.getCurrentSize());
        cache.getOrInsert(4, 4);
    }
}