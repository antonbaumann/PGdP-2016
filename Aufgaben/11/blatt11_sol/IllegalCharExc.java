package pgdp;

public class IllegalCharExc extends Exception {

    private final char used;

    public IllegalCharExc(char used) {
        this.used = used;
    }

    @Override
    public String toString() {
        String msg;
        switch (used) {
            case '\n':
                msg = "line break (\\n)";
                break;
            case '\t':
                msg = "tab (\\t)";
                break;
            case '\r':
                msg = "carriage return (\\r)";
                break;
            case ' ':
                msg = "whitespace";
                break;
            case '\b':
                msg = "backspace (\\b)";
                break;
            case '\f':
                msg = "formfeed (\\f)";
                break;
            default:
                msg = "" + used;
        }

        return "Illegal Character Exception: the following character is not allowed " + msg;
    }

}
