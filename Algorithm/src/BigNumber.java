
import java.util.Random;

public class BigNumber {

    private final Tool tool = new Tool();

    //Addtion the value to the left with the right.
    public String add(String a, String b) {
        String result = "";
        if (tool.check(a) && tool.check(b)) {
            if (a.length() >= b.length()) {
                b = tool.insert(b, a.length() - b.length());
            } else {
                a = tool.insert(a, b.length() - a.length());
            }
            int r = 0;
            for (int i = a.length() - 1; i >= 0; i--) {
                if (i == 0) {
                    result = tool.getValueAt(a, 0) + tool.getValueAt(b, 0) + r + result;
                    break;
                }
                if (tool.getValueAt(a, i) + tool.getValueAt(b, i) + r <= 9) {
                    result = tool.getValueAt(a, i) + tool.getValueAt(b, i) + r + result;
                    r = 0;
                } else {
                    result = tool.getValueAt(a, i) + tool.getValueAt(b, i) + r - 10 + result;
                    r = 1;
                }
            }
        } else if (tool.check(a) && !tool.check(b)) {
            result = sub(a, b.substring(1));
        } else if (!tool.check(a) && tool.check(b)) {
            result = sub(b, a.substring(1));
        } else {
            result = "-" + add(a.substring(1), b.substring(1));
        }
        return result;
    }

    //Substraction the value to the left with the right.
    public String sub(String a, String b) {
        String result = "";
        if (tool.compare(a, b) == 0) {
            return result + "0";
        } else if (tool.check(a) && !tool.check(b)) {
            return add(a, b.substring(1));
        } else if (!tool.check(a) && tool.check(b)) {
            return "-" + add(a.substring(1), b);
        } else if (!tool.check(a) && !tool.check(b)) {
            return sub(b.substring(1), a.substring(1));
        } else {
            if (tool.compare(a, b) == -1) {
                return "-" + sub(b, a);
            } else {
                if (a.length() >= b.length()) {
                    b = tool.insert(b, a.length() - b.length());
                } else {
                    a = tool.insert(a, b.length() - a.length());
                }
                int r = 0;
                for (int i = a.length() - 1; i >= 0; i--) {
                    if (i == 0) {
                        result = tool.getValueAt(a, 0) - tool.getValueAt(b, 0) - r + result;
                        break;
                    }
                    if (tool.getValueAt(a, i) - tool.getValueAt(b, i) - r < 0) {
                        result = tool.getValueAt(a, i) - tool.getValueAt(b, i) - r + 10 + result;
                        r = 1;
                    } else {
                        result = tool.getValueAt(a, i) - tool.getValueAt(b, i) - r + result;
                        r = 0;
                    }
                }
            }
        }
        return tool.trim(result);
    }

    //Multiply the value to the left with the right.
    public String mul(String a, String b) {
        if (a.length() > b.length()) {
            return mul(b, a);
        }
        if (!tool.check(a) && !tool.check(b)) {
            return mul(a.substring(1), b.substring(1));
        } else if (!tool.check(a) && tool.check(b)) {
            return "-" + mul(a.substring(1), b);
        } else if (tool.check(a) && !tool.check(b)) {
            return "-" + mul(a, b.substring(1));
        }
        String[] sigma = new String[a.length()];
        for (int i = a.length() - 1; i >= 0; i--) {
            if (mul(b, tool.getValueAt(a, i)) != null) {
                sigma[i] = tool.pow(mul(b, tool.getValueAt(a, i)), a.length() - i - 1);
            } else {
                sigma[i] = "0";
            }
        }
        return tool.sigma(sigma);
    }

    //Multiply the value to the left with the right value.
    public String mul(String a, int b) {
        String result = null;
        if (tool.check(a) && b > 0 && b <= 9) {
            result = "";
            int r = 0;
            for (int i = a.length() - 1; i >= 0; i--) {
                if (i == 0) {
                    result = tool.getValueAt(a, 0) * b + r + result;
                    break;
                }
                if (tool.getValueAt(a, i) * b + r <= 9) {
                    result = tool.getValueAt(a, i) * b + r + result;
                    r = 0;
                } else {
                    result = (tool.getValueAt(a, i) * b + r) % 10 + result;
                    r = (tool.getValueAt(a, i) * b + r) / 10;
                }
            }
        } else if (!tool.check(a) && b > 0 && b <= 9) {
            return "-" + mul(a.substring(1), b);
        }
        return result;
    }

    //Divides left-hand operand by right-hand operand.
    public String div(String a, String b) {
        String result = "";
        String a1, a2;
        if (tool.compare(a.substring(0, b.length()), b) == -1) {
            a1 = a.substring(0, b.length() + 1);
            a2 = a.substring(b.length() + 1);
        } else {
            a1 = a.substring(0, b.length());
            a2 = a.substring(b.length());
        }
        while (!a2.equals("")) {
            if (a2.length() == 1) {
                result += divInt(a1, b);
//                System.out.println("a1 = " + a1);
//                System.out.println("b = " + b);
//                System.out.println("a2 = " + a2);
//                System.out.println("result = " + result);
                a1 = sub(a1, mul(b, Integer.toString(divInt(a1, b)))) + a2;
                a2 = "";
                break;
            }
            result += divInt(a1, b);
//            System.out.println("a1 = " + a1);
//            System.out.println("b = " + b);
//            System.out.println("a2 = " + a2);
//            System.out.println("result = " + result);
            a1 = sub(a1, mul(b, Integer.toString(divInt(a1, b)))) + a2.substring(0, 1);
            a2 = a2.substring(1);
        }
        result += divInt(a1, b);
        return result;
    }

    public int divInt(String a, String b) {
        int i;
        if (tool.compare(a, b) == -1) {
            return 0;
        } else if (tool.compare(a, b) == 0) {
            return 1;
        }
        for (i = 2; i <= 10; i++) {
            if (tool.compare(a, mul(b, Integer.toString(i))) == -1) {
                i--;
                return i;
            }
        }
        return i;
    }

    //Divides left-hand operand by right-hand operand and returns remainder.
    public String mod(String a, String b) {
        String a1, a2;
        if (tool.compare(a.substring(0, b.length()), b) == -1) {
            a1 = a.substring(0, b.length() + 1);
            a2 = a.substring(b.length() + 1);
        } else {
            a1 = a.substring(0, b.length());
            a2 = a.substring(b.length());
        }
        while (!a2.equals("")) {
            if (a2.length() == 1) {
                a1 = sub(a1, mul(b, Integer.toString(divInt(a1, b)))) + a2;
                a2 = "";
                break;
            }
            a1 = sub(a1, mul(b, Integer.toString(divInt(a1, b)))) + a2.substring(0, 1);
            a2 = a2.substring(1);
        }
        a1 = sub(a1, mul(b, Integer.toString(divInt(a1, b)))) + a2;
        return a1;
    }

    class Tool {

        /*Remove the zero digits of the input chain.
        For instance, "000036543" -> "36543", "001300315" -> "1300315". */
        public String trim(String parameter) {
            if (check(parameter)) {
                while (parameter.startsWith("0")) {
                    if (parameter.length() == 1) {
                        break;
                    }
                    parameter = parameter.substring(1);
                }
            } else {
                parameter = "-" + trim(parameter.substring(1));
            }
            return parameter;
        }

        //Add the number of Zero digits to the input stream.
        public String insert(String parameter, int amount) {
            for (int i = 0; i < amount; i++) {
                parameter = "0" + parameter;
            }
            return parameter;
        }

        public int compare(String a, String b) {
            a = trim(a);
            b = trim(b);
            if (a.equals(b)) {
                return 0;
            }
            if (check(a) && !check(b)) {
                return 1;
            }
            if (!check(a) && check(b)) {
                return -1;
            }
            if (!check(a) && !check(b)) {
                if (compare(a.substring(1), b.substring(1)) == 1) {
                    return -1;
                } else {
                    return 1;
                }
            }
            if (a.length() > b.length()) {
                return 1;
            }
            if (a.length() < b.length()) {
                return -1;
            }
            for (int i = 0; i < a.length(); i++) {
                if (getValueAt(a, i) > getValueAt(b, i)) {
                    return 1;
                }
                if (getValueAt(a, i) < getValueAt(b, i)) {
                    return -1;
                }
            }
            return 0;
        }

        public int getValueAt(String parameter, int index) {
            return Character.getNumericValue(parameter.charAt(index));
        }

        //Check that some string types are negative or positive.
        public boolean check(String parameter) {
            if (parameter.startsWith("-")) {
                return false;
            } else {
                return true;
            }
        }

        public String randomNumber(int amount, boolean length) {
            String result = "";
            Random ran = new Random();
            for (int i = 0; i < amount; i++) {
                if (i == 0) {
                    if (length) {
                        int ra = ran.nextInt(2);
                        if (ra == 1) {
                            result = "-" + result;
                        }
                    }
                }
                int j = ran.nextInt(10);
                result += j;
            }
            return trim(result);
        }

        public String pow(String base, int exponenet) {
            for (int i = 0; i < exponenet; i++) {
                base += "0";
            }
            return base;
        }

        public String sigma(String[] sigma) {
            String result = "0";
            for (String string : sigma) {
                result = add(result, string);
            }
            return result;
        }
    }
}
