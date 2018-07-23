
public class DoublyLinkedList {

    Entry head;
    int size;

    /**
     * constructor empty DoublyLinkedList
     */
    public DoublyLinkedList() {
        head = null;
        size = 0;
    }


    /**
     * Returns the number of elements in this list.
     * @return number of elements in this list
     */
    public int size(){
        return size;
    }

    /**
     * Appends a new element with value info to the end of this list
     * @param info value of the new element
     */
    public void add(int info) {
        if (head != null)
            add(head, info);
        else
            head = new Entry(null, null, info);
        size++;
    }

    /**
     * Appends a new element with value info to the end of this list
     * recursively
     * @param node current node of recursion
     * @param info value of the new element
     */
    private void add(Entry node, int info) {
        if (node.next == null)
            node.next = new Entry(node, null, info);
        else
            add(node.next, info);
    }

    /**
     * Inserts a new element with value info
     * at the specified position in this list.
     * @param index position where the element is inserted, from 0 ... list.size()
     * @param info value of the new element
     */
    public void add(int index, int info) {

        if (index < 0 || index > size)
            return;
        if (index == 0) {
            head = new Entry(null, head, info);
            head.next.prev = head;
            size++;
            return;
        }
        if(index == size){
            add(info);
            return;
        }          
        Entry prev = head;
        Entry tmp = head.next;
        int i;
        for (i = 1; i < index; i++) {
            prev = tmp;
            tmp = tmp.next;
        }
        prev.next = new Entry(prev, tmp, info);
        tmp.prev = prev.next;
        size++;
    }


    /**
     * Removes and returns the element at position index from this list.
     * @param index position of the element that is removed
     * @return value of the removed element
     */
    public int remove(int index) {
        // index out of bound
        if(index < 0 || index > size-1)
            return Integer.MIN_VALUE;
        // head is removed
        if(index == 0){
            int ret = head.elem;
            head = head.next;
            if(head != null)
                head.prev = null;
            size--;
            return ret;
        }
        else{
            int i;
            Entry tmp = head.next;
            for(i = 1; i < index; i++){
                tmp = tmp.next;
            }
            tmp.prev.next = tmp.next;
            if(tmp.next != null)
                tmp.next.prev = tmp.prev;
            size--;
            return tmp.elem;
        }
    }

    /**
     * shifts the list the specified number of positions to the left
     * example: [1,5,6,7] ---shift(2)---> [6,7,1,5]
     * @param index number of position to shift, from 0 to size-1
     */
    public void shiftLeft(int index){
        if(index <= 0 || index > size-1)
            return;
        Entry tmp = head;
        do{
            tmp = tmp.next;
        }while(tmp.next != null);
        // construct a cyclic list
        tmp.next = head;
        head.prev = tmp;
        // go to the point where to split the cyclic list
        for(int i = 0; i <= index; i++)
            tmp = tmp.next;
        head = tmp;
        tmp.prev.next = null;
        tmp.prev = null;

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
    
    private String testPrev(){
        Entry tmp = head;
        String out = "[";
        if(head != null){
            while(tmp.next != null)
                tmp = tmp.next;
            
            out += tmp.elem;
            tmp = tmp.prev;
            while(tmp != null){
                out = out + "," + tmp.elem;
                tmp = tmp.prev;
            }
        }
        out += "]";
        return out;
    }

    public static void main(String[] args) {

        DoublyLinkedList t = new DoublyLinkedList();
        for(int i = 0; i < 15; i++){
            t.add(i);
        }
        
        System.out.println(t);
        for(int i = 0; i < 15; i++){
            System.out.println("shiftLeft - " + i);
            t.shiftLeft(i);
            System.out.println(t);
        }
        
        System.out.println("test prev: " + t.testPrev());

        DoublyLinkedList l = new DoublyLinkedList();
        System.out.println("empty list: " + l);
        System.out.println("reverse empty list: " + l);
        System.out.println("delete first element on empty list: " + l.remove(0));
        l.add(1);
        System.out.println("delete first element on list " + l + ": " + l.remove(0));
        l.add(1);
        System.out.println("add 1 : " + l);
        l.add(2);
        System.out.println("add 2 : " + l);
        System.out.println("delete second element on list " + l + l.remove(1));
        System.out.println("delete second element on list " + l + l.remove(1));
        l.add(4);
        l.add(5);
        l.add(7);
        l.add(10);
        System.out.println("add 42 at position 3 in list: " + l);
        l.add(3, 42);
        System.out.println(l);
        System.out.println(l);
        System.out.println("delete second element on list" + l + l.remove(1));
        System.out.println("delete third element on list" + l + l.remove(2));
        System.out.println("delete third element on list " + l + l.remove(2));
        System.out.println("delete first element on list " + l + l.remove(0));
        System.out.println("delete first element on list " + l + l.remove(0));
        System.out.println(l);
    }

    class Entry {

        Entry prev;
        Entry next;
        int elem;

        public Entry(Entry prev, Entry next, int elem) {
            this.prev = prev;
            this.next = next;
            this.elem = elem;
        }

    }
}
