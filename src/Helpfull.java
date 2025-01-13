

public class Helpfull {

    public static int char2num(char c) {
        switch (Character.toLowerCase(c)) {
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            case 'i': return 8;
            case 'j': return 9;
            case 'k': return 10;
            case 'l': return 11;
            case 'm': return 12;
            case 'n': return 13;
            case 'o': return 14;
            case 'p': return 15;
            case 'q': return 16;
            case 'r': return 17;
            case 's': return 18;
            case 't': return 19;
            case 'u': return 20;
            case 'v': return 21;
            case 'w': return 22;
            case 'x': return 23;
            case 'y': return 24;
            case 'z': return 25;
            default: return -1;
        }
    }

    public  static char intToChar(int x)
    {
        String s = "abcdefghijklmnopqrstuvwxyz";
        for (char c : s.toCharArray()) {
            if (char2num(c)==x) return c;
        }
        return 0;
    }
    public static boolean cellForm(String str) {
        if (str == null) {
            return false;
        }
        if (str.length() > 3 || str.length() < 2) {
            return false;
        }
        if (Character.isLetter(str.charAt(0))) {
            if (Character.isDigit(str.charAt(1)) && str.length() == 2) {
                return true;
            }
            if (str.length() == 3 && Character.isDigit(str.charAt(1)) && Character.isDigit(str.charAt(2))) {
                return true;
            }

        }
        return false;

    }
    public static int sCount(String str, char s) {
        int counter = 0;
        for (char ch : str.toCharArray()) {
            if (ch == s) counter++;
        }
        return counter;

    }
    public static int get_x(String str) {
        return char2num(str.charAt(0));
    }
    public static int get_y(String str) {
        return Integer.parseInt(str.substring(1,2));
    }
    public static boolean operator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

}