
public class SymmetricStackTest {

    public static double testGetNumberOfElements() {
        double points = 0;

        SymmetricStack qs = new SymmetricStack();
        int[] a = new int[100];
        qs.setData(a);
        qs.setFirst(-1);
        qs.setLast(-1);

        if (qs.getNumberOfElements() == 0) points += 0.5;
        else System.out.println("getNumberOfElements test 1 failed");

        qs.setFirst(5);
        qs.setLast(74);
        if (qs.getNumberOfElements() == 70) points += 0.5;
        else System.out.println("getNumberOfElements test 2 failed");

        qs.setFirst(0);
        qs.setLast(a.length - 1);
        if (qs.getNumberOfElements() == 100) points += 0.5;
        else System.out.println("getNumberOfElements test 3 failed");

        qs.setFirst(50);
        qs.setLast(49);
        if (qs.getNumberOfElements() == 100) points += 0.5;
        else System.out.println("getNumberOfElements test 4 failed");

        return points;
    }

    public static double testPrepend() {
        double points = 0;
        final int numElems = 100;
        String x, y;
        SymmetricStack qs = new SymmetricStack();
        qs.setData(new int[numElems]);

        StringBuilder s = new StringBuilder();
        for (int i = 1; i < (numElems / 2) - 10; ++i) {
            s.insert(0, i); // bad code
            qs.prepend(i);
        }

        x = qs.toString().replaceAll(" |\\(|\\)|\\*", "");
        if (s.toString().equals(x)) points += 0.5;
        else System.out.println("prepened test 1 failed");

        // test 2
        int[] a = new int[numElems];
        for (int i = 0; i < numElems; ++i) a[i] = i;
        qs.setData(a);
        qs.setFirst(0);
        qs.setLast(numElems - 10);
        qs.prepend(-1);

        int[] b = new int[numElems];
        for (int i = 0; i < numElems; ++i) b[i] = i;
        b[b.length - 1] = -1;

        if (java.util.Arrays.equals(a, b)) points += 0.5;
        else System.out.println("prepened test 2 failed");

        // test 3
        s = new StringBuilder();
        StringBuilder tmp = new StringBuilder();
        tmp.append(-1);
        for (int i = 0; i < numElems; ++i) {
            if (i <= numElems / 2) s.append(i);
            if (i >= numElems / 2 + 10) tmp.append(i);
            a[i] = i;
        }
        s.append(tmp);
        qs.setData(a);
        qs.setFirst(numElems / 2 + 10);
        qs.setLast(numElems / 2);
        qs.prepend(-1);

        x = qs.toString().replaceAll(" |\\(|\\)|\\*", "");
        if (s.toString().equals(x)) points += 0.5;
        else System.out.println("prepend test 3 failed");

        return points;
    }

    public static double testAppend() {
        double points = 0;
        final int numElems = 100;
        String x, y;
        SymmetricStack qs = new SymmetricStack();
        qs.setData(new int[numElems]);

        StringBuilder s = new StringBuilder();
        for (int i = 1; i < (numElems / 2) - 10; ++i) {
            s.append(i);
            qs.append(i);
        }

        x = qs.toString().replaceAll(" |\\(|\\)|\\*", "");
        if (s.toString().equals(x)) points += 0.5;
        else System.out.println("appened test 1 failed");

        // test 2
        int[] a = new int[numElems];
        for (int i = 0; i < numElems; ++i) a[i] = i;
        qs.setData(a);
        qs.setFirst(10);
        qs.setLast(numElems - 1);
        qs.append(-1);

        int[] b = new int[numElems];
        for (int i = 0; i < numElems; ++i) b[i] = i;
        b[0] = -1;

        if (java.util.Arrays.equals(a, b)) points += 0.5;
        else System.out.println("appened test 2 failed");

        // test 3
        s = new StringBuilder();
        StringBuilder tmp = new StringBuilder();
        tmp.append(-1);
        for (int i = 0; i < numElems; ++i) {
            if (i <= numElems / 2) s.append(i);
            if (i >= numElems / 2 + 10) tmp.append(i);
            a[i] = i;
        }
        s.append(tmp);
        qs.setData(a);
        qs.setFirst(numElems / 2 + 10);
        qs.setLast(numElems / 2);
        qs.append(-1);

        x = qs.toString().replaceAll(" |\\(|\\)|\\*", "");
        if (s.toString().equals(x)) points += 0.5;
        else System.out.println("appened test 3 failed");

        return points;
    }

    public static double testRemoveFirst() {
        double points = 0;

        SymmetricStack qs = new SymmetricStack();

        final int numElems = 100;
        int[] a = new int[numElems];
        int first = 30;
        int last = 60;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numElems; ++i) {
            if (first < i && i <= last) s.append(i);
            a[i] = i;
        }
        qs.setData(a);
        qs.setFirst(first);
        qs.setLast(last);
        qs.removeFirst();

        String x = qs.toString().replaceAll(" |\\(|\\)|\\*", "");

        if (x.equals(s.toString())) points += 0.5;
        else System.out.println("removeFirst test 1 failed");

        // test 2
        a = new int[numElems];
        first = 60;
        last = 30;
        s = new StringBuilder();
        for (int i = 0; i < numElems; ++i) {
            if (!(last < i && i <= first)) s.append(i);
            a[i] = i;
        }
        qs.setData(a);
        qs.setFirst(first);
        qs.setLast(last);
        qs.removeFirst();

        x = qs.toString().replaceAll(" |\\(|\\)|\\*", "");

        if (x.equals(s.toString())) points += 0.5;
        else System.out.println("removeFirst test 2 failed");

        // test 3
        qs.setFirst(numElems / 2);
        qs.setLast(numElems / 2);
        qs.removeFirst();

        x = qs.toString().replaceAll(" |\\(|\\)|\\*", "");
        if (x.equals("")) points += 0.5;
        else System.out.println("removeFirst test 3 failed");

        return points;
    }

    public static double testRemoveLast() {
        double points = 0;

        SymmetricStack qs = new SymmetricStack();

        final int numElems = 100;
        int[] a = new int[numElems];
        int first = 30;
        int last = 60;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numElems; ++i) {
            if (first <= i && i < last) s.append(i);
            a[i] = i;
        }
        qs.setData(a);
        qs.setFirst(first);
        qs.setLast(last);
        qs.removeLast();

        String x = qs.toString().replaceAll(" |\\(|\\)|\\*", "");

        if (x.equals(s.toString())) points += 0.5;
        else System.out.println("removeLast test 1 failed");

        // test 2
        a = new int[numElems];
        first = 60;
        last = 30;
        s = new StringBuilder();
        for (int i = 0; i < numElems; ++i) {
            if (!(last <= i && i < first)) s.append(i);
            a[i] = i;
        }
        qs.setData(a);
        qs.setFirst(first);
        qs.setLast(last);
        qs.removeLast();

        x = qs.toString().replaceAll(" |\\(|\\)|\\*", "");

        if (x.equals(s.toString())) points += 0.5;
        else System.out.println("removeLast test 2 failed");

        // test 3
        qs.setFirst(numElems / 2);
        qs.setLast(numElems / 2);
        qs.removeLast();

        x = qs.toString().replaceAll(" |\\(|\\)|\\*", "");
        if (x.equals("")) points += 0.5;
        else System.out.println("removeLast test 3 failed");

        return points;
    }

    public static double testIncrease0() {
        // (_)    ->    *...*(_)*...*
        final int numElems = 8;
        SymmetricStack qs = new SymmetricStack();

        StringBuilder s = new StringBuilder();
        int[] a = new int[numElems];
        for (int i = 0; i < a.length; ++i) {
            s.append(i);
            a[i] = i;
        }
        qs.setData(a);
        qs.setFirst(0);
        qs.setLast(7);
        qs.increase();

        String x = qs.toString();
        int num = x.length() - x.replaceAll("\\*", "").length();
        x = qs.toString().replaceAll(" |\\(|\\)|\\*", "");

        if (x.equals(s.toString()) && num == numElems) {
            return 0.5;
        } else {
            System.out.println("increase test 0 failed");
            return 0;
        }
    }

    public static double testIncrease1() {
        // _)(_    ->    *...*(_)*...*
        final int numElems = 8;
        SymmetricStack qs = new SymmetricStack();

        StringBuilder s = new StringBuilder();
        int[] a = new int[numElems];
        for (int i = 0; i < a.length; ++i) {
            s.append(i);
            a[(i + 4) % numElems] = i;
        }
        qs.setData(a);
        qs.setFirst(4);
        qs.setLast(3);
        qs.increase();

        String x = qs.toString();
        int num = x.length() - x.replaceAll("\\*", "").length();
        x = qs.toString().replaceAll(" |\\(|\\)|\\*", "");

        if (x.equals(s.toString()) && num == numElems) {
            return 0.5;
        } else {
            System.out.println("increase test 1 failed");
            return 0;
        }
    }

    public static double testDecrease0() {
        // *...*(_)*...*    ->    *...*(_)*...* with roughly half amount of stars
        SymmetricStack qs = new SymmetricStack();

        int[] a = new int[42];
        for (int i = 0; i < a.length; ++i) a[i] = i;
        qs.setData(a);
        qs.setFirst(20);
        qs.setLast(25);
        StringBuilder s = new StringBuilder();
        for (int i = qs.getFirst(); i <= qs.getLast(); ++i) s.append(i);

        String x;
        x = qs.toString();
        int num_bevore = x.length() - x.replaceAll("\\*", "").length();
        qs.decrease();
        x = qs.toString();
        int num_after = x.length() - x.replaceAll("\\*", "").length();
        x = x.replaceAll(" |\\(|\\)|\\*", "");

        if (x.equals(s.toString()) && num_after * 2 < num_bevore + 2) {
            return 0.5;
        } else {
            System.out.println("decrease test 0 failed");
            return 0;
        }
    }

    public static double testDecrease1() {
        // _)*...*(_    ->    *...*(_)*...* with roughly half amount of stars
        SymmetricStack qs = new SymmetricStack();

        int[] a = new int[42];
        qs.setData(a);
        qs.setFirst(a.length - 2);
        qs.setLast(2);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < a.length; ++i) {
            a[(qs.getFirst() + i) % a.length] = i;
            if (i < 5) s.append(i);
        }

        String x;
        x = qs.toString();
        int num_bevore = x.length() - x.replaceAll("\\*", "").length();
        qs.decrease();
        x = qs.toString();
        int num_after = x.length() - x.replaceAll("\\*", "").length();
        x = x.replaceAll(" |\\(|\\)|\\*", "");

        if (x.equals(s.toString()) && num_after * 2 < num_bevore + 2) {
            return 0.5;
        } else {
            System.out.println("decrease test 1 failed");
            return 0;
        }
    }

    public static void main(String[] args) {
        double points = 0;

        points += SymmetricStackTest.testGetNumberOfElements();
        points += SymmetricStackTest.testPrepend();
        points += SymmetricStackTest.testAppend();
        points += SymmetricStackTest.testRemoveFirst();
        points += SymmetricStackTest.testRemoveLast();
        points += SymmetricStackTest.testIncrease0();
        points += SymmetricStackTest.testIncrease1();
        points += SymmetricStackTest.testDecrease0();
        points += SymmetricStackTest.testDecrease1();

        System.out.println("You got " + points + " points.");
    }
}
