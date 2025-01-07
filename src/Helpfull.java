public class Helpfull {

    public static int chr_2_int(char c) {
        c = Character.toLowerCase(c);
        if (c >= 'a' && c <= 'z') {
            return c - 'a';
        }
        return -1;
    }

    public static char int_2_chr(int x) {
        if (x >= 0 && x < 26) {
            return (char) ('a' + x);
        }
        return 0;
    }

    public static int instancecounter(String str, char s) {
        int counter = 0;
        for (char ch : str.toCharArray()) {
            if (ch == s) {
                counter++;
            }
        }
        return counter;
    }

    public static boolean is_opt(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static int get_x(String str) {
        return chr_2_int(str.charAt(0));
    }

    public static int get_y(String str) {
        return Integer.parseInt(str.substring(1, 2));
    }

    public static boolean subCellCall(String str) {
        if (str == null || str.length() < 2 || str.length() > 3) {
            return false;
        }

        if (Character.isLetter(str.charAt(0))) {
            if (Character.isDigit(str.charAt(1))) {
                if (str.length() == 2 || (str.length() == 3 && Character.isDigit(str.charAt(2)))) {
                    return true;
                }
            }
        }
        return false;
    }
}
