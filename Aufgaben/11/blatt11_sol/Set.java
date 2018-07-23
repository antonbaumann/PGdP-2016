package pgdp;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Set<T> implements Iterable<T> {

    private class List<U> {

        private final U info;
        private final List<U> next;

        private List(U e, List<U> next) {
            this.info = e;
            this.next = next;
        }
    }

    private class ListIterator implements Iterator<T> {

        private List<T> list;

        private ListIterator(List<T> l) {
            this.list = new List<>(null, l);
        }

        @Override
        public boolean hasNext() {
            return list != null && list.next != null;
        }

        @Override
        public T next() {
            if (list == null || list.next == null) throw new NoSuchElementException();
            list = list.next;
            return list.info;
        }
    }

    private final List<T> list;

    public Set() {
        this(null);
    }

    private Set(List<T> list) {
        this.list = list;
    }

    public Set<T> add(T e) {
        if (e == null) throw new NullPointerException();
        if (this.contains(e)) return this;

        return new Set<>(new List<>(e, list));
    }

    public Set<T> remove(Object o) {
        if (o == null) throw new NullPointerException();
        return new Set<>(removeHelper(list, o));
    }

    private List<T> removeHelper(List<T> l, Object o) {
        if (l == null) return null;
        if (l.info.equals(o)) return l.next;

        return new List<>(l.info, removeHelper(l.next, o));
    }

    public boolean contains(Object o) {
        for (T i : this) if (o.equals(i)) return true;

        return false;
    }

    public int size() {
        int n = 0;
        for (T i : this) ++n;
        return n;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator(list);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        final Set<?> other = (Set<?>) o; // type erasure :(
        for (Object i : this) if (!other.contains(i)) return false;
        for (Object i : other) if (!this.contains(i)) return false;
        return true;
    }

    @Override
    public String toString() {
        String s = "{";
        List<T> i;
        for (i = list; i != null && i.next != null; i = i.next) {
            s += i.info + ",";
        }
        if (i != null) s += i.info;
        return s + "}";
    }
}
