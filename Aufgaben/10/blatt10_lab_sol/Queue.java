package pgdp;

public class Queue<T> {

    private Entry<T> first;
    private Entry<T> last;
    private int numElements = 0;

    /**
     *
     * @return true if Queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return this.numElements == 0;
    }

    /**
     * inserts a new element in the Queue (FIFO)
     *
     * @param newElement is appended as last element to the Queue
     */
    public void enqueue(T newElement) {
        numElements++;
        if (numElements == 1) {
            first = new Entry<T>(newElement);
            last = first;
        } else {
            last.next = new Entry<T>(newElement);
            last = last.next;
        }
    }

    /**
     * removes a element from the Queue (FIFO)
     *
     * @return the first element from the Queue
     */
    public T dequeue() {
        if (isEmpty())
            return null;
        T t = first.elem;
        if (first == last)
            last = null;
        first = first.next;
        numElements--;
        return t;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "Queue is empty";

        Entry<T> t = first.next;
        String s = "[" + first.elem;
        while (t != null) {
            s += ", " + t.elem;
            t = t.next;
        }
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        Queue<String> q1 = new Queue<>();
        for (int i = 0; i < 10; i++) {
            q1.enqueue(Integer.toString(i));
            System.out.println(q1);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(q1.dequeue());
        }
        System.out.println(q1.dequeue());

        Queue<Integer> q2 = new Queue<>();
        for (int i = 0; i < 10; i++) {
            q2.enqueue(i);
            System.out.println(q2);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(q2.dequeue());
        }
        System.out.println(q2.dequeue());
    }

    private class Entry<T> {

        private Entry<T> next;
        private T elem;

        public Entry(T elem) {
            this.elem = elem;
        }

    }
}
