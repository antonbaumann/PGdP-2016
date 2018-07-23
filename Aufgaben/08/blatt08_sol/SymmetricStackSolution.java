
public class SymmetricStackSolution {

    private int[] data;
    private int first;
    private int last;

    public SymmetricStackSolution() {
        data = new int[4];
        first = last = -1;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public int getNumberOfElements() {
        if (first == -1)
            return 0;
        else if (first < last)
            return (last - first) + 1;
        else if (first > last)
            return 1 + data.length - (first - last);
        else
            return 1;
    }

    public void increase() {
        if (!isFull()) return;

        int[] data_new = new int[data.length * 2];
        int first_new = data.length / 2;
        int last_new = first_new;

        if (first == 0 && last == data.length - 1) {
            for (int i = 0; i < data.length; ++i, ++last_new)
                data_new[last_new] = data[i];
        } else {
            for (int i = first; i < data.length; ++i, ++last_new)
                data_new[last_new] = data[i];
            for (int i = 0; i <= last; ++i, ++last_new)
                data_new[last_new] = data[i];
        }

        --last_new;

        assert (data.length == (last_new - first_new) + 1);

        data = data_new;
        first = first_new;
        last = last_new;
    }

    public void decrease() {
        if (isEmpty()) return;

        if (getNumberOfElements() > data.length / 4) return;

        int[] data_new = new int[data.length / 2];
        int first_new = data.length / 8;
        int last_new = first_new;

        if (first <= last) {
            for (int i = first; i <= last; ++i, ++last_new)
                data_new[last_new] = data[i];
        } else {
            for (int i = first; i < data.length; ++i, ++last_new)
                data_new[last_new] = data[i];
            for (int i = 0; i <= last; ++i, ++last_new)
                data_new[last_new] = data[i];
        }

        --last_new;

        data = data_new;
        first = first_new;
        last = last_new;
    }

    public boolean isEmpty() {
        return getNumberOfElements() == 0;
    }

    public boolean isFull() {
        return getNumberOfElements() == data.length;
    }

    public void prepend(int x) {
        increase();

        if (isEmpty()) {
            first = last = data.length / 2;
        } else {
            --first;
            if (first < 0) first = data.length - 1;
        }

        data[first] = x;
    }

    public void append(int x) {
        increase();

        if (isEmpty()) first = last = data.length / 2;
        else last = ++last % data.length;

        data[last] = x;
    }

    public void removeFirst() {
        if (first == last) first = last = -1;
        else first = ++first % data.length;
        decrease();
    }

    public void removeLast() {
        if (first == last) first = last = -1;
        else {
            --last;
            if (last < 0) last = data.length - 1;
        }
        decrease();
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < data.length; ++i) {
            if (first <= last && (i < first || i > last))
                out += "* ";
            if (first <= last && i > first && i < last)
                out += " " + data[i];
            if (first > last && i > last && i < first)
                out += "* ";
            if (first > last && (i > first || i < last))
                out += " " + data[i];
            if (i == first)
                out = out + "(" + data[i];
            if (i == last)
                out += (first == last ? "" : " " + data[i]) + ")";
        }
        return out;
    }
}
