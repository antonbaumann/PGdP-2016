
public class HeadListSol {

    Entry head;

    /**
     * constructor empty HeadList
     */
    HeadListSol() {
        head = null;
    }

    /**
     * Appends a new element with value info to the end of this list
     *
     * @param info value of the new element
     */
    public void add(int info) {

        if (head == null) {
            head = new Entry(null, null, info);
            head.first = head;
        } else {
            Entry curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = new Entry(head, null, info);
        }
    }

    /**
     * Removes and returns the element at position index from this list.
     *
     * @param index position of the element that is removed
     * @return value of the removed element
     */
    public int remove(int index) {

        if (index < 0 || head == null) // index out of lower bound
            return Integer.MIN_VALUE;

        if (index == 0) {// head is removed
            int tmp = head.elem;
            head = head.next;
            setHead(head);
            return tmp;
        } else {
            Entry curr = head;
            Entry prev = head;
            int i;
            for (i = 0; i < index && curr.next != null; i++) {
                prev = curr;
                curr = curr.next;
            }

            if (i < index) // index out of upper bound
                return Integer.MIN_VALUE;

            prev.next = curr.next;
            return curr.elem;
        }

    }

    /**
     * sets the head of each list element to newHead
     *
     * @param newHead reference to the new head
     */
    private void setHead(Entry newHead) {
        if (head != newHead)
            System.out.println("newHead is not head!!!");

        if (head != null) {
            Entry tmp = head;
            do {
                tmp.first = newHead;
                tmp = tmp.next;
            } while (tmp != null);
        }
    }

    /**
     * reverse the list example: [1,2,3,4,5] --> [5,4,3,2,1], [] --> [], [1] -->
     * [1]
     */
    public void reverse() {
        if (head == null || head.next == null) {
            return;
        }
        Entry prev = null;
        Entry tmp = head;
        Entry nxt = head.next;
        while (tmp.next != null) {
            tmp.next = prev;
            prev = tmp;
            tmp = nxt;
            nxt = nxt.next;
        }
        tmp.next = prev;
        head = tmp;
        setHead(tmp);

    }

    public int size() {

        if (head == null)
            return 0;
        int size = 1;
        Entry tmp = head.next;
        while (tmp != null) {
            tmp = tmp.next;
            size++;
        }
        return size;
    }

    @Override
    public String toString() {
        String out = "[";
        if (head != null) {
            out += head.elem;
            Entry tmp = head.next;
            while (tmp != null) {
                out = out + "," + tmp.elem;
                tmp = tmp.next;
            }
        }
        out += "]";
        return out;
    }

    class Entry {

        Entry first;
        Entry next;
        int elem;

        public Entry(Entry first, Entry next, int elem) {
            this.first = first;
            this.next = next;
            this.elem = elem;
        }

    }
}
