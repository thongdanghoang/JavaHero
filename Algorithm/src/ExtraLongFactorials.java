
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jingtian
 */
public class ExtraLongFactorials {

    static void extraLongFactorials(int n) {
        String str = "1";
        for (int i = 1; i <= n; i++) {
            str = multiplying(str, i);
        }
        System.out.println(str);
    }

    static int getLast(String number) {
        return Integer.parseInt(number.substring(number.length() - 1, number.length()));
    }

    //ok
    static String multiplying(String a, int b) {
        String result = "";
        int r = 0;
        while (a.length() > 0) {
            result = (int) ((b * getLast(a) + r) % 10) + result;
            r = (int) ((b * getLast(a) + r) / 10);
            a = a.substring(0, a.length() - 1);
            if (a.length() == 0 && r != 0) {
                result = r + result;
            }
        }
        return result;
    }

    static String multiplying(String a, String b) {
        String result = "";
        ArrayList<String> ls = new ArrayList<>();
        int count = 1;
        while (b.length() > 0) {
            ls.add(multiplying(multiplying(a, getLast(b)), count));
            b = b.substring(0, b.length() - 1);
            count *= 10;
        }
        result = adding(ls);
        return result;
    }

    //ok
    static String adding(ArrayList<String> ls) {
        String result = "0";
        for (String l : ls) {
            result = adding(l, result);
        }
        return result;
    }

    //ok
    static String adding(String a, String b) {
        String result = "";
        int r = 0;
        while (a.length() > 0 || b.length() > 0) {
            result = (getLast(a) + getLast(b) + r) % 10 + result;
            r = (int) (getLast(a) + getLast(b) + r) / 10;
            a = a.substring(0, a.length() - 1);
            b = b.substring(0, b.length() - 1);
            if (a.length() == 0 && b.length() == 0) {
                if (r == 0) {
                    break;
                } else {
                    result = r + result;
                }
            } else if (a.length() == 0) {
                result = (Integer.parseInt(b) + r) + result;
            } else if (b.length() == 0) {
                result = (Integer.parseInt(a) + r) + result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        extraLongFactorials(30);
    }
}
