
public class TimeConversion {

    public static void main(String[] args) {
        String input = "06:40:03AM";
        String result = timeConversion(input);
        System.out.println("result = " + result);
    }

    private static int getHour(String hour) {
        return Integer.parseInt(hour.substring(0, 2));
    }

    private static String timeConversion(String input) {
        String result = "";
        if (input.endsWith("PM")) {
            if (getHour(input) == 12) {
                result = input.substring(0, 8);
            } else {
                result = (getHour(input) + 12) + input.substring(2, 8);
            }
        } else {
            if (getHour(input) == 12) {
                result = "00" + input.substring(2, 8);
            } else {
                result = input.substring(0, 8);
            }
        }
        return result;
    }

}
