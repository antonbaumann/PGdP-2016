
package pgdp;

public class NotEnoughLower extends NotEnoughLetter{
    
    public NotEnoughLower(int should, int is){
        super(should, is);
    }
    
    @Override
    public String toString(){
        return "NotEnoughLowerCaseException: not enough lower case letters\npassword should contain "
                + should + " lower case letter(s)" +
                "\nbut contains " + is + " lower case letter(s)";
    }
}
