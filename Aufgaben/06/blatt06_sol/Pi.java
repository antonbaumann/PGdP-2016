
public class Pi {

    // Kompakt
    public static double piRec(long n) {
        if (0 == n) {
            return 4.0;
        } else { //+/-4 == (4-8*(n%2))
            return (4 - 8 * (n % 2)) / (2 * n + 1.0) + piRec(n - 1);
        }
    }

    // Alternative
    public static double piSwitchRec(long n) {
        if (0 == n % 2)
            return piPosRec(n);
        else
            return piNegRec(n);
    }

    public static double piPosRec(long n) {
        if (0 == n)
            return 4.0;
        else
            return 4.0 / (2 * n + 1) + piNegRec(n - 1);
    }

    public static double piNegRec(long n) {
        if (0 == n)
            return 4.0; //shouldn't happen
        else
            return -4.0 / (2 * n + 1) + piPosRec(n - 1);
    }

    // Iterative Loesung
    public static double piIt(long n) {
        double pi = 0.0;
        double f = 4.0;

        for (long i = n; i >= 0; i--) {
            pi += f / (2 * i + 1);
            f = -f;
        }

        return pi;
    }

    public static void main(String[] args) {
        System.out.println(piRec(1000));
        System.out.println(piSwitchRec(1000));
        System.out.println(piIt(1000));
    }
}

