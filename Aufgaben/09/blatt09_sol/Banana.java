package pgdp;

public class Banana extends Fruit {

    @Override
    public boolean isBanana() {
        return true;
    }

    @Override
    public int shelfLife() {
        return 7;
    }
}
