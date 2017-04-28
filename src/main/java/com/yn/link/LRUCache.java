package com.yn.link;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangnan on 17/4/17.
 *
 * LRU算法，非线程安全的
 *
 */
public class LRUCache<K, V> {

    private final int MAX_CACHE_SIZE;

    private Map<K, Entry<K, V>> cache;

    private Entry<K, V> first;
    private Entry<K, V> last;

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache1 = new LRUCache<Integer, Integer>(2);
        for (int i=0; i<10; i++) {
            cache1.put(i, i);
//            if (i %2 == 0) {
//                cache1.print();
//            }
        }

        cache1.get(8);
        cache1.print();
    }

    public LRUCache(int size) {
        this.MAX_CACHE_SIZE = size;
        cache = new HashMap<K, Entry<K, V>>(size);
    }

    /**
     * 往缓存中添加数据
     *
     * @param k
     * @param v
     */
    public void put(K k, V v) {
        Entry entry = getEntry(k);
        if (entry == null) {
            if (cache.size() >= MAX_CACHE_SIZE) {
                cache.remove(last.k);
                removeLast();
            }
            
            entry = new Entry();
            entry.k = k;
        }

        entry.v = v;
        moveToFirst(entry);
        cache.put(k, entry);
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        Entry<K, V> entry = first;
        while (entry != null) {
            sb.append(String.format("%s:%s ", entry.k, entry.v));
            entry = entry.next;
        }

        System.out.println(sb.toString());
    }

    /**
     * 获取缓存的值
     *
     * @param k
     * @return
     */
    public V get(K k) {
        Entry<K, V> entry = getEntry(k);
        if (entry == null) return null;

        moveToFirst(entry);
        return entry.v;
    }

    /**
     * 删除缓存
     *
     * @param k
     */
    public void remove(K k) {
        Entry<K, V> entry = getEntry(k);
        if (entry == null) return;

        if (entry.pre != null) {
            entry.pre.next = entry.next;
        }

        if (entry.next != null) {
            entry.next.pre = entry.pre;
        }

        if (entry == first) {
            first = entry.next;
        }

        if (entry == last) {
            last = entry.pre;
        }

        cache.remove(k);

    }

    /**
     * 移除链表尾部
     *
     */
    private void removeLast() {
        if (last == null) {
            return;
        }

        last = last.pre;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
    }

    private Entry<K, V> getEntry(K k) {
        return cache.get(k);
    }

    /**
     * 将节点移动到表头
     *
     * @param entry
     */
    private void moveToFirst(Entry<K, V> entry) {
        if (entry == first) return;
        if (entry.pre != null) {
            entry.pre.next = entry.next;
        }

        if (entry.next != null) {
            entry.next.pre = entry.pre;
        }

        if (entry == last) {
            last = last.pre;
        }

        if (first == null || last == null) {
            first = last = entry;
            return;
        }

        entry.next = first;
        first.pre = entry;
        first = entry;
        entry.pre = null;
    }

    static class Entry<K, V> {
        public Entry<K, V> pre;
        public Entry<K, V> next;
        public V v;
        public K k;
    }
}
