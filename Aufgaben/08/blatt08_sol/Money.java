package pgdp;

public class Money {

    private final int cent;

    public Money() {
        this(0);
    }

    public Money(int cent) {
        this.cent = cent;
    }

    public int getCent() {
        return cent;
    }

    public Money addMoney(Money m) {
        return new Money(this.cent + m.cent);
    }

    @Override
    public String toString() {
        String out;
        int mycent;

        if (cent < 0) {
            out = "-";
            mycent = -cent;
        } else {
            out = "";
            mycent = cent;
        }

        out += mycent / 100;
        out += ",";
        out += (mycent / 10) % 10;
        out += mycent % 10;

        return out + " Euro";
    }
}
