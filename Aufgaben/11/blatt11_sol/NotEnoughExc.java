
package pgdp;

public class NotEnoughExc extends Exception{
    
    final int is, should;
    
    public NotEnoughExc(int should, int is){
        this.should = should;
        this.is = is;
    }
}
