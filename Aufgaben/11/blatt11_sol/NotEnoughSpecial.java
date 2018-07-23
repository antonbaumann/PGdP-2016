
package pgdp;


public class NotEnoughSpecial extends NotEnoughExc{
    
    public NotEnoughSpecial(int should, int is){
        super(should, is);
    }
    
    @Override
    public String toString(){
        return "NotEnoughSpecialCharException: not enough special characters\npassword should contain "
                + should + " special character(s)" +
                "\nbut contains " + is + " special character(s)";
    }
}
