import java.io.*;
import java.util.*;

/**
 * Created by anna on 26.09.16.
 */
public class Calcul {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //primer, kotor vvodim
        String primer;//

        try {
            System.out.println("Enter task");
            primer = reader.readLine();
            primer = opz(primer);
            //System.out.print(primer);
            System.out.printf("%.4f",calculate(primer));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Preobraz v obratn. polskuu zapis
     * @param primer Enter str
     * @return Result of algorithm opz
     */
    private static String opz(String primer) throws Exception {
        StringBuilder stack = new StringBuilder(""),strOut = new StringBuilder("");
        char c1, c2;

        for (int i = 0; i < primer.length(); i++) {
            c1 = primer.charAt(i);//parse primer to char elements
            if (isOperator(c1)) {
                while (stack.length() > 0) {
                    c2 = stack.substring(stack.length()-1).charAt(0);//last element of stack
                    if (isOperator(c2) && (prior(c1) <= prior(c2))) {
                        strOut.append(" ").append(c2).append(" ");
                        stack.setLength(stack.length()-1);
                    } else {
                        strOut.append(" ");
                        break;
                    }
                }
                strOut.append(" ");
                stack.append(c1);
            } else if ('(' == c1) {
                stack.append(c1);
            } else if (')' == c1) {
                c2 = stack.substring(stack.length()-1).charAt(0);
                while ('(' != c2) {
                    if (stack.length() < 1) {
                        throw new Exception("Exception: brackets!!!");
                    }
                    strOut.append(" ").append(c2);
                    stack.setLength(stack.length()-1);
                    c2 = stack.substring(stack.length()-1).charAt(0);
                }
                stack.setLength(stack.length()-1);
            } else {
                // if this is number
                strOut.append(c1);
            }
        }

        // left operators add to strOut
        while (stack.length() > 0) {
            strOut.append(" ").append(stack.substring(stack.length()-1));
            stack.setLength(stack.length()-1);
        }

        return  strOut.toString();
    }

    /**
     * true-if c is operator
     */
    private static boolean isOperator(char c) {
        switch (c) {
            case '-':
            case '+':
            case '*':
            case '/':
                return true;
        }
        return false;
    }

    /**
     * a priority
     * @param op char
     * @return int priority
     */
    private static int prior(char op) {
        switch (op) {
            case '*':
            case '/':
                return 2;
        }
        return 1; //  + and -
    }

    /**
     * calculate using opz
     * @param primer
     * @return double result
     */
    private static double calculate(String primer) throws Exception {
        double a, b, result=0;
        String s;
        Deque<Double> stack = new ArrayDeque<Double>();
        StringTokenizer st = new StringTokenizer(primer);//to parse string with spaces
        while(st.hasMoreTokens()) {
            try {
                s = st.nextToken().trim();//take the element from opz result
                if (1 == s.length() && isOperator(s.charAt(0))) {
                    if (stack.size() < 2) {
                        throw new Exception("Error");
                    }
                    b = stack.pop();
                    a = stack.pop();
                    switch (s.charAt(0)) {
                        case '+':
                            result=a+b;
                            break;
                        case '-':
                            result=a-b;
                            break;
                        case '/':
                            result=a/b;
                            break;
                        case '*':
                            result=a*b;
                            break;
                        default:
                            throw new Exception("Uncnown operator");
                    }
                    stack.push(result);
                } else {
                    a = Double.parseDouble(s);
                    stack.push(a);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (stack.size() > 1) {
            throw new Exception("Error");
        }

        return stack.pop();
    }
}
