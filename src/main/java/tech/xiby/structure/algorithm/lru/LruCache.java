package tech.xiby.structure.algorithm.lru;

import tech.xiby.structure.algorithm.common.CacheNode;
import tech.xiby.structure.algorithm.common.CallbackMethod;
import tech.xiby.structure.algorithm.common.ListNode;

/**
 * LRU算法实现
 * 利用双向链表实现
 * 每当访问一个节点，将该节点置于链表头部
 * 要删除时，将尾节点删除
 *
 * @author xiby
 */
public class LruCache<K, V> {
    /**
     * 头指针
     */
    private ListNode<K, V> head;
    /**
     * 尾指针
     */
    private ListNode<K, V> tail;

    private int maxSize;

    private int currentSize;

    private CallbackMethod method;

    public LruCache(int maxSize, CallbackMethod method) {
        this(maxSize);
        this.method = method;
    }

    private LruCache(int maxSize) {
        ListNode headNode = new ListNode();
        head = headNode;
        tail = headNode;
        this.maxSize = maxSize;
        this.currentSize = 0;
    }


    /**
     * 判断是否存在key
     *
     * @param key 要查询的key
     * @return 存在 返回true,否则返回false
     */
    public boolean containKey(K key) {
        ListNode work = head.getNext();
        while (work != null) {
            if (work.getData().getKey().equals(key)) {
                return true;
            }
            work = work.getNext();
        }
        return false;
    }

    public void insertWhenNotExist(K key, V value) {
        if (!containKey(key)) {
            headInsert(key, value);
        }
    }

    public V getOrInsert(K key, V valueForInsert) {
        ListNode current = getByKey(key);
        if (current == null) {
            //原cache中没有该值，插入
            insert(key, valueForInsert);
            return null;
        } else {
            V ret = (V) current.getData().getValue();
            //原cache中存在该值，先放到链头，再返回
            if (current == head.getNext()) {
                //为第一个节点，直接返回
            } else if (current.getNext() == null) {
                //为最后一个节点，且不为第一个节点
                tail = current.getPre();
                tail.setNext(null);
                current.setNext(head.getNext());
                head.getNext().setPre(current);
                current.setPre(head);
            } else {
                current.getNext().setPre(current.getPre());
                current.getPre().setNext(current.getNext());
                current.setNext(head.getNext());
                head.getNext().setPre(current);
                head.setNext(current);
            }
            return ret;

        }
    }

    /**
     * 根据key值获取缓存节点
     *
     * @param key
     * @return NULL when key not exist
     */
    private ListNode getByKey(K key) {
        ListNode work = head.getNext();
        while (work != null) {
            if (work.getData().getKey().equals(key)) {
                return work;
            }
            work = work.getNext();
        }
        return null;
    }

    /**
     * 直接插入
     *
     * @param key
     * @param value
     */
    private void insert(K key, V value) {
        if (currentSize < maxSize) {
            //直接采用头插法
            headInsert(key, value);
            //插入节点后，大小自增
            currentSize++;
        } else {
            if (method != null) {
                method.invoke(tail);
            }
            tail = tail.getPre();
            tail.getNext().setPre(null);
            tail.setNext(null);
            headInsert(key, value);
        }
    }

    private void headInsert(K key, V value) {
        ListNode current = new ListNode(new CacheNode(key, value));
        current.setNext(head.getNext());
        if (head.getNext() != null) {
            head.getNext().setPre(current);
        }
        current.setPre(head);
        head.setNext(current);
        if (head == tail) {
            //针对插入的第一个元素做处理
            tail = head.getNext();
        }
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getCurrentSize() {
        return currentSize;
    }
}
