
import java.util.Random;

public class HeadListTest {

    static HeadList l;
    static HeadListSol lsol;
    static double points = 0;
    static boolean output;

    public static void test(String msg, double p) {
        if (l.toString().equals(lsol.toString())) {
            points = points + p;
            if (output) {
                System.out.println(msg);
                System.out.println("is: \t" + l);
                System.out.println("should: " + lsol);
                System.out.println();
            }
        } else {
            System.out.println("fail: " + msg);
            System.out.println("is: \t" + l);
            System.out.println("shoult: " + lsol);
            System.out.println();
        }
    }

    public static void main(String[] args) {

        /* set output = false if you do not want to see the
         output of the single test steps */
        output = true;

        l = new HeadList();
        lsol = new HeadListSol();

        // reverse on empty list
        l.reverse();
        lsol.reverse();
        test("reverse empty list", 0.25);

        // delete - index out of bounds
        l.remove(-1);
        lsol.remove(-1);
        l.remove(0);
        lsol.remove(0);
        l.remove(1);
        lsol.remove(1);
        test("remove: special cases (index out of bound)", 0.5);

        // add
        l.add(3);
        lsol.add(3);
        test("add first element", 0.5);

        //reverse list with one element    
        l.reverse();
        lsol.reverse();
        test("reverse list with one element", 0.25);

        // add additional elements and reverse
        for (int i = 0; i < 15; i++) {
            int x = new Random().nextInt(100);
            l.add(x);
            lsol.add(x);
            test("add additional elements", 0);
            l.reverse();
            lsol.reverse();
            test("reverse list", 0);
        }
        test("add additional elements", 1);
        l.reverse();
        lsol.reverse();
        test("reverse list", 1.5);

        // remove first
        l.remove(0);
        lsol.remove(0);
        test("remove first element", 0.25);

        //remove last
        l.remove(lsol.size() - 1);
        lsol.remove(lsol.size() - 1);
        test("remove last element", 0.25);

        //remove general
        int n = lsol.size();
        String tmp;
        for (int i = 0; i < n; i++) {
            int pos = new Random().nextInt(n - i);
            tmp = lsol.toString();
            l.remove(pos);
            lsol.remove(pos);
            test("remove element at index " + pos + " from list" + tmp, 0);
        }
        test("remove general", 1.5);

        System.out.println("points = " + points);

    }

}
