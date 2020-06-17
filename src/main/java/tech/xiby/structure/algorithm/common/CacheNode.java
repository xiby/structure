package tech.xiby.structure.algorithm.common;

/**
 * 缓存的节点信息
 *
 * @author xiby
 */
public class CacheNode<K, V> {
    private K key;
    private V value;
    private int keyHash;

    public CacheNode() {
    }

    public CacheNode(K key, V value) {
        this.key = key;
        this.value = value;
        keyHash = key.hashCode();
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getKeyHash() {
        return keyHash;
    }
}
