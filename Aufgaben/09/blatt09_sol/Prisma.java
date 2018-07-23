package pgdp;

public class Prisma {

    private Grundflaeche basis;
    private int hoehe;

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

    public boolean istWuerfel() {
        if (basis.istQuadrat()) {
            return basis.zuQuadrat().getLaenge() == hoehe;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Prisma{ " + "basis = " + basis + "; hoehe = " + hoehe + "; volumen = " + volumen()
                + "; oberflaeche = " + oberflaeche() + "; Wuerfel? = " + istWuerfel() + '}';
    }

}
