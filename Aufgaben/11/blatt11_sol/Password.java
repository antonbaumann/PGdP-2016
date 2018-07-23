package pgdp;

public class Password {

    private final int nrUpperShould, nrLowerShould, nrSpecialShould, nrNumbersShould, lengthShould;
    private final char[] illegalChars;

    private static boolean matchesIllegalCharacter(char[] illegalChars, char c) {

        for (int i = 0; i < illegalChars.length; i++) {
            if (c == illegalChars[i])
                return true;
        }
        return false;
    }

    public Password(int nrUpperShould, int nrLowerShould, int nrSpecialShould, int nrNumbersShould, int lengthShould, char[] illegalChars) {
        this.nrUpperShould = nrUpperShould;
        this.nrLowerShould = nrLowerShould;
        this.nrSpecialShould = nrSpecialShould;
        this.nrNumbersShould = nrNumbersShould;
        this.lengthShould = lengthShould;
        this.illegalChars = illegalChars;
    }

    public void checkFormat(String pwd) throws IllegalCharExc, NotEnoughExc, NotLongEnoughExc {

        int nrUpperIs = 0;
        int nrLowerIs = 0;
        int nrSpecialIs = 0;
        int nrNumbersIs = 0;
        int lengthIs = pwd.length();

        if (lengthIs < lengthShould) {
            throw new NotLongEnoughExc(lengthShould, lengthIs);
        }

        for (int i = 0; i < lengthIs; i++) {
            //lower case
            if (pwd.charAt(i) >= 'a' && pwd.charAt(i) <= 'z')
                nrLowerIs++;
            //upper case
            else if (pwd.charAt(i) >= 'A' && pwd.charAt(i) <= 'Z')
                nrUpperIs++;
            //numbers
            else if (pwd.charAt(i) >= '0' && pwd.charAt(i) <= '9')
                nrNumbersIs++;
            //illegal character
            else if (matchesIllegalCharacter(illegalChars, pwd.charAt(i))) {
                throw new IllegalCharExc(pwd.charAt(i));
            } //special character
            else
                nrSpecialIs++;
        }

        if (nrUpperIs < nrUpperShould)
            throw new NotEnoughUpper(nrUpperShould, nrUpperIs);
        if (nrLowerIs < nrLowerShould)
            throw new NotEnoughLower(nrLowerShould, nrLowerIs);
        if (nrSpecialIs < nrSpecialShould)
            throw new NotEnoughSpecial(nrSpecialShould, nrSpecialIs);
        if (nrNumbersIs < nrNumbersShould)
            throw new NotEnoughNumber(nrNumbersShould, nrNumbersIs);

    }

    public static void main(String[] args) {
        String password = "a1aa!aa?aa2aBaA";
        Password pwd = new Password(2, 2, 2, 2, 12, new char[]{'\n', '\t', '\r', ' '});

        try {
            pwd.checkFormat(password);
        } catch (NotEnoughExc | NotLongEnoughExc | IllegalCharExc e) {
            System.out.println("wrong password format");
            System.out.println(e);
        }
    }
}
