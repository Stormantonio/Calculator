package com.chernenko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Main {
    private static String listener = null;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static double y;

    public static void main(String[] args) throws IOException {
        Calculator calculator = new Calculator();
        double x;
        while (true) {
            double result = 0;
            System.out.print(" _start enter number or 'exit'_ : ");
            if (checkString()) {
                x = Double.parseDouble(listener);
                while (true) {
                    System.out.print("['+' '-' '*' '/' '=' 'c' 'exit']: ");
                    listener = reader.readLine().trim().toLowerCase();
                    switch (listener) {
                        case "+":
                            isDouble();
                            result = calculator.addition(x, y);
                            x = result;
                            getResult(result, false);
                            break;
                        case "-":
                            isDouble();
                            result = calculator.subtraction(x, y);
                            x = result;
                            getResult(result, false);
                            break;
                        case "/":
                            isDouble();
                            if (y != 0) {
                                result = calculator.division(x, y);
                                x = result;
                                getResult(result, false);
                            } else {
                                System.out.println(" !!!!!! Division by zero !!!!!!");
                                isDouble();
                                x = y;
                            }
                            break;
                        case "*":
                            isDouble();
                            result = calculator.multiplication(x, y);
                            getResult(result, false);
                            x = result;
                            break;
                        case "c":
                            result = 0;
                            System.out.print("[ 0 ]");
                            isDouble();
                            x = y;
                            break;
                        case "=":
                            getResult(result, true);
                            break;
                        case "exit":
                            return;
                        default:
                            System.out.println("          Wrong command!");
                            break;
                    }
                }
            } else if (listener.equals("exit")) {
                return;
            } else {
                System.out.print(" !!! Wrong command! Try more !!! ");
            }
        }
    }

    private static boolean checkString() {
        try {
            listener = reader.readLine().trim().toLowerCase();
            Double.parseDouble(String.valueOf(tryParse(listener)));
        } catch (NumberFormatException | IOException e) {
            return false;
        }
        return true;
    }

    private static void isDouble() {
        try {
            System.out.print("  enter the next number, please : ");
            listener = reader.readLine().trim().toLowerCase();
            y = Double.parseDouble(String.valueOf(tryParse(listener)));
        } catch (NumberFormatException | IOException e) {
            System.out.println("!!!! you must enter a number !!!! ");
            isDouble();
        }
    }

    private static void getResult(double result, boolean equally) {
        int res = (int) result;
        NumberFormat nf = new DecimalFormat("#.###########");
        if (result - res == 0 && equally) {
            System.out.println(" *********** RESULT *********** : " + res);
        } else if (result - res != 0 && equally) {
            System.out.println(" *********** RESULT *********** : " + nf.format(result));
        } else if (result - res == 0 && !equally) {
            System.out.println(" _intermediate result, continue_: " + res);
        } else {
            System.out.println(" _intermediate result, continue_: " + nf.format(result));
        }
    }

    private static double tryParse(String str) {
        double res;
        try {
            res = Double.parseDouble(str.replace(",", "."));
        } catch (Exception ex) {
            return 0;
        }
        return res;
    }
}
