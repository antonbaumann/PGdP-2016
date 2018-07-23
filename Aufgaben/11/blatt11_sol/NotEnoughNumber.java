package pgdp;

public class NotEnoughNumber extends NotEnoughExc{
    
    public NotEnoughNumber(int should, int is){
        super(should, is);
    }
    
    @Override
    public String toString(){
        return "NotEnoughNumberException: not enough numbers\npassword should contain "
                + should + " number(s)" +
                "\nbut contains " + is + " number(s)";
    }
}
