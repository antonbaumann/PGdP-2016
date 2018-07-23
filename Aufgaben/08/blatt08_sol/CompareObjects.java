package pgdpws16;

public class CompareObjects {

    public static String copyString(String t) {
        String c = "";
        for (int i = 0; i < t.length(); i++)
            c += t.charAt(i);
        return c;
    }

    public static boolean equals(String a, String b) {
        if (a.length() != b.length())
            return false;
        for (int i = 0; i < a.length(); i++)
            if (a.charAt(i) != b.charAt(i))
                return false;
        return true;
    }

    public static void main(String[] args) {

        String z = "hallo";
        String t = copyString(z);
        System.out.println(t + " == " + z + " = " + (t == z));
        System.out.println("equals(" + t + "," + z + ") = " + equals(t, z));
        
        t = "123 5\u03C0cde";
        z = copyString(t);
        System.out.println(t + " == " + z + " = " + (t == z));
        System.out.println("equals(" + t + "," + z + ") = " + equals(t, z));
        System.out.println("t = " + t + "; z = " + z + "; t.equals(z) = " + t.equals(z));

    }
}
 
