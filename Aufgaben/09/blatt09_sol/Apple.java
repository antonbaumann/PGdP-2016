package pgdp;

public class Apple extends Fruit {

    @Override
    public boolean isApple() {
        return true;
    }

    @Override
    public int shelfLife() {
        return 30;
    }
}
