package pgdpws16;

public class Queue {

    // TODO instance variables, methods, ...

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

}
 
