package pgdp;

import java.util.LinkedList;

public class JavaList extends MiniJava{

    public static void main(String[] args) {
        LinkedList<String> l = new LinkedList<String>();
        int n = read("Geben Sie eine ganze Zahl ein.");
        for(int i = 0; i <= n; i++)
            l.add("" + i);

        for(String t : l)
            System.out.println(t);
    }
}
