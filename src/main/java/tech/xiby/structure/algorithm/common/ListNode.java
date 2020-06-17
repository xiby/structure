package tech.xiby.structure.algorithm.common;

/**
 * 链表节点
 *
 * @param <K>
 * @param <V>
 * @author xiby
 */
public class ListNode<K, V> {
    private ListNode pre;
    private CacheNode data;
    private ListNode next;

    public ListNode() {
    }

    public ListNode(CacheNode data) {
        this.data = data;
        pre = null;
        next = null;
    }

    public ListNode getPre() {
        return pre;
    }

    public void setPre(ListNode pre) {
        this.pre = pre;
    }

    public CacheNode getData() {
        return data;
    }

    public void setData(CacheNode data) {
        this.data = data;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
