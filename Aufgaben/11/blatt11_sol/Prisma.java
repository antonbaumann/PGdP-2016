package pgdp;

public class Prisma implements Comparable<Prisma> {

    private final Grundflaeche basis;
    private final int hoehe;

    @Override
    public int compareTo(Prisma o) {
        if (o == null)
            throw new NullPointerException();

        else  {
            // ignore unprecise arithmetic operations
            double diff = volumen() - ((Prisma) o).volumen();
            // we do not cast to int as then (-)0.x would be 0
            if (diff == 0)
                return 0;
            else if (diff < 0)
                return -1;
            else
                return 1;
        }
    }

    public Grundflaeche getBasis() {
        return basis;
    }

    public int getHöhe() {
        return hoehe;
    }

    public Prisma(Grundflaeche basis, int höhe) {
        this.basis = basis;
        this.hoehe = höhe;
    }

    public double oberflaeche() {
        return hoehe * basis.umfang() + 2 * basis.flaeche();
    }

    public double volumen() {
        return hoehe * basis.flaeche();
    }

    @Override
    public String toString() {
        return "Prisma{" + "basis= " + basis + ", hoehe= " + hoehe + ", Volumen= " + volumen()
                + ", Oberflaeche= " + oberflaeche() + '}';
    }

}
