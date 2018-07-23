/**
 * Klasse der Raubtiere.
 */
public class Predator extends Animal {

    private final int daysToLiveWithoutPrey;

    private int daysWithoutPreyLeft;

    public Predator(int daysToLiveWithoutPrey, boolean female){
        super(female);
        this.daysToLiveWithoutPrey = daysToLiveWithoutPrey;
        this.daysWithoutPreyLeft = daysToLiveWithoutPrey;
    }

    public int daysBeforeDeath() {
        return daysWithoutPreyLeft;
    }

    @Override
    public void sunset(){
        if(daysWithoutPreyLeft > 0){
            daysWithoutPreyLeft--;
        } else if (this.alive && this.daysWithoutPreyLeft == 0) {
            this.alive = false;
            if (this.position != null) {
                this.position.removeAnimal(this);
            }
        }
    }

    public void eatPrey(){
        this.daysWithoutPreyLeft = daysToLiveWithoutPrey;
    }

}
