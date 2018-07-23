
package pgdp;

public class NotLongEnoughExc extends Exception{
    
    private final int should, is;
    
    public NotLongEnoughExc(int should, int is){
        this.should = should;
        this.is = is;
    }
    
    @Override
    public String toString(){
        return "NotLongEnoughException: password must have length: " + should
                + "\nbut has length: " + is;
    }
    
}
