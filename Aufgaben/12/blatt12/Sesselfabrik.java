package pgdp;

public class Sesselfabrik implements Runnable {

    private int sesselBestellbar = 0;
    private int lagerPlaetzeFrei = 20;
    private boolean rampeBelegt = false;
    // synchronize variable sesselBestellbar
    private final Object lockSessel = new Object();
    // synchronize variable lagerPlaetzeFrei
    private final Object lockLager = new Object();
    // synchronize variable rampeBelegt
    private final Object lockRampe = new Object();

    @Override
    public void run() {
        try {
            while (true) {
                sesselEinlagern();
            }
        } catch (Exception e) {
            if (!(e instanceof InterruptedException))
                System.out.println("Unexpected Exception in Sesselfabrik.run()");
        }
    }

}
