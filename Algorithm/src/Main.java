
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BigNumber.Tool obj = new BigNumber().new Tool();
        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter a, b: ");
//        System.out.print("Enter a: ");
        String a = obj.randomNumber(32, false);
//        System.out.print("Enter b: ");
        String b = obj.randomNumber(8, false);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.print("Enter result (check div): ");
        String testDiv = sc.nextLine();
        System.out.print("Enter result (check div): ");
        String testMod = sc.nextLine();
        sc.close();
        if (testDiv.equals(new BigNumber().div(a, b))) {
            System.out.println("true div");
        } else {
            System.out.println("false div");
        }
        if (testMod.equals(new BigNumber().mod(a, b))) {
            System.out.println("true mod");
        } else {
            System.out.println("false mod");
        }
        System.out.println("div(a,b) = " + new BigNumber().div(a, b));
        System.out.println("mod(a,b) = " + new BigNumber().mod(a, b));
    }
}
