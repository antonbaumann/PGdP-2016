package pgdpws16;

public class CompareObjects {

    public static String copyString(String t) {
        String c = "";
        for (int i = 0; i < t.length(); i++)
            c += t.charAt(i);
        return c;
    }

    public static void main(String[] args) {

        String z = "hallo";
        String t = copyString(z);
        System.out.println(t + " == " + z + " = " + (t == z));
    }
}
