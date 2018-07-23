
public class RockPaperScissors implements Runnable {

    // this time you might learn something new from the example solution  =>  enum types
    // for the given task it is better to make use of an enum instead of an integer (discuss why in the tutorial)
    // therefore, the presented example solution differs a little bit from the interface we asked for
    public enum RPS {
        ROCK, PAPER, SCISSOR
    }

    public static void main(String[] args) {
        new RockPaperScissors().run();
    }

    @Override
    public void run() {
        Player p1 = new Player();
        Player p2 = new Player();
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        t1.start();
        t2.start();

        int t1_wins = 0;
        int t2_wins = 0;
        final int plays = 100;

        try {
            for (int i = 0; i < plays; ++i) {
                RPS x = p1.getChoice();
                RPS y = p2.getChoice();

                if (x != y) {
                    if ((x == RPS.ROCK && y == RPS.SCISSOR) || (x == RPS.PAPER && y == RPS.ROCK) || (x == RPS.SCISSOR && y == RPS.PAPER)) ++t1_wins;
                    else ++t2_wins;
                }
            }

            t1.interrupt();
            t2.interrupt();

            t1.join();
            t2.join();

            System.out.println("t1 won " + t1_wins + " times\nt2 won " + t2_wins + " times\ntie " + (plays - t1_wins - t2_wins) + " times");
        } catch (InterruptedException ignored) { }
    }
}
