
public class Player implements Runnable {

    private RockPaperScissors.RPS choice;

    @Override
    public synchronized void run() {
        while (choice != null) try { wait(); } catch (InterruptedException ignored) { return; }
        int i = new java.util.Random().nextInt(3);
        switch (i) {
            case 0:  choice = RockPaperScissors.RPS.SCISSOR; break;
            case 1:  choice = RockPaperScissors.RPS.ROCK; break;
            default: choice = RockPaperScissors.RPS.PAPER; break;
        }
        notify();
        run();
    }

    public synchronized RockPaperScissors.RPS getChoice() throws InterruptedException {
        while (choice == null) wait();
        RockPaperScissors.RPS result = choice;
        choice = null;
        notify();
        return result;
    }
}
