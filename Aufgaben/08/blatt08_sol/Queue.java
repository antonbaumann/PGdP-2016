package pgdpws16;

public class Queue {

    private int first, last;
    private int[] arr;

    public Queue() {
        first = last = -1;
        arr = new int[2];
    }

    public boolean isEmpty() {
        return first == -1;
    }

    public void enqueue(int x) {
        if (first == -1) {
            first = last = 0;
            arr[last] = x;
            return;
        }
        last = (last + 1) % arr.length;
        if (last == first) {
            int[] newarr = new int[2 * arr.length];
            for (int i = 0; i < arr.length; i++)
                newarr[i] = arr[(first + i) % arr.length];
            first = 0;
            last = arr.length;
            arr = newarr;
        }
        arr[last] = x;
    }

    public int dequeue() {
        /* if the Queue is empty you would throw an exception
         we consider exceptions later in the lecture*/
        if (isEmpty())
            return Integer.MIN_VALUE;
        int ret = arr[first];
        if (first == last) {
            first = last = -1;
            return ret;
        }
        first = (first + 1) % arr.length;
        int n = last - first + 1;
        if (first <= last && n <= arr.length / 4) {
            int[] newarr = new int[arr.length / 2];
            for (int i = 0; i < n; i++)
                newarr[i] = arr[first + i];
            arr = newarr;
            first = 0;
            last = n - 1;
        }
        n = arr.length - first + last + 1;
        if (first > last && n <= arr.length / 4) {
            int[] newarr = new int[arr.length / 2];
            for (int i = 0; i < n; i++)
                newarr[i] = arr[(first + i) % arr.length];
            arr = newarr;
            first = 0;
            last = n - 1;
        }
        return ret;
    }

    @Override
    public String toString() {

        String out = "";
        for (int i = 0; i < arr.length; i++) {
            if (first <= last && (i < first || i > last))
                out += " *";
            if (first <= last && i > first && i < last)
                out += " " + arr[i];
            if (first > last && i > last && i < first)
                out += " *";
            if (first > last && (i > first || i < last))
                out += " " + arr[i];
            if (i == first)
                out = out + " (" + arr[i];
            if (i == last)
                out += (first == last ? "" : " " + arr[i]) + ")";
        }
        return out;
    }

    public void testDecrease() {
        arr = new int[40];
        for (int i = 0; i < 40; i++)
            arr[i] = i;

        first = 36;
        last = 4;
    }

    public static void main(String[] args) {
        System.out.println("Initialize Queue");
        Queue q = new Queue();
        System.out.println(q);

        System.out.println("enqueue 1 to 10");
        for (int i = 1; i <= 10; i++) {
            q.enqueue(i);
            System.out.println(q);
        }

        System.out.println("alternating: enqueue dequeue (enqueue 11 to 20)");
        for (int i = 11; i <= 20; i++) {
            q.enqueue(i);
            System.out.println(q);
            q.dequeue();
            System.out.println(q);
        }

        System.out.println("dequeue 10 times");
        for (int i = 0; i < 10; i++) {
            q.dequeue();
            System.out.println(q);
        }

        System.out.println("Test decrease with first > last");
        q.testDecrease();
        System.out.println(q);
        q.dequeue();
        System.out.println(q);

    }
}
