
public class Palindrom extends MiniJava {

    public static char[] toCharArray(String input) {
        char[] charArray = new char[input.length()];
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = input.charAt(i);
        }
        return charArray;
    }

    public static boolean isPalindrome(char[] input) {

        for (int i = 0; i < input.length / 2; i++) {
            if (input[i] != input[input.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static String toLowerCase(String input) {
        String tmp = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') {
                tmp += (char) (input.charAt(i) + ('a' - 'A'));
            } else {
                tmp += input.charAt(i);
            }
        }
        return tmp;
    }

    public static void main(String[] args) {

        String inputStr = "";
        while (inputStr.length() == 0) {
            inputStr = readString();
        }

        inputStr = toLowerCase(inputStr);
        char[] charInput = toCharArray(inputStr);

        if (isPalindrome(charInput)) {
            write("Input is a palindrome.");
        } else {
            write("Input is not a palindrome.");
        }
    }
}
