package pgdp;

public abstract class Grundflaeche implements Comparable<Grundflaeche> {

    @Override
    public final int compareTo(Grundflaeche o) {
        if (o == null)
            throw new NullPointerException();
        else  {
            // we ignore unprecise arithmetic operations
            double diff = flaeche() - ((Grundflaeche) o).flaeche();
            // we do not cast to int as then (-)0.x would be 0
            if (diff == 0)
                return 0;
            else if (diff < 0.0)
                return -1;
            else
                return 1;
        }
    }

    public abstract double umfang();

    public abstract double flaeche();

    @Override
    public String toString() {
        return "Umfang: " + umfang() + "; Flaeche: " + flaeche();
    }
}
