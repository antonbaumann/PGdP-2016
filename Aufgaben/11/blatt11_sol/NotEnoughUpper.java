
package pgdp;

public class NotEnoughUpper extends NotEnoughLetter{
    
    public NotEnoughUpper(int should, int is){
        super(should, is);
    }
    
    @Override
    public String toString(){
        return "NotEnoughUpperCaseException: not enough upper case letters\npassword should contain "
                + should + " upper case letter(s)" +
                "\nbut contains " + is + " upper case letter(s)";
    }
}
