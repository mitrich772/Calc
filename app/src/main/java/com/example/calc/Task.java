package com.example.calc;
import static java.lang.Math.max;
import static java.lang.Math.round;
public class Task {
    public String generateTaskEs(int diff) {
        if (diff == 1) {
            String[] operations = new String[]{"+", "-"};
            String oper = operations[(int) round(Math.random())];
            int a = (int) ((int) 1 + Math.random() * 10);
            int b = (int) ((int) 1 + Math.random() * 10);
            int ans;
            if (oper == "+") {
                ans = a + b;
                return a + "" + oper + "" + b + "=?" + "n" + ans;
            } else {
                ans = Math.max(a, b) - Math.min(a, b);
                return Math.max(a, b) + "" + oper + "" + Math.min(a, b) + "=?" + "n" + ans;
            }
        }
        else if (diff == 2) {
            String[] operations = new String[]{"+", "-"};
            String oper = operations[(int) round(Math.random())];
            int a = (int) ((int) 10 + Math.random() * 40);
            int b = (int) ((int) 10 + Math.random() * 40);
            int ans;
            if (oper == "+") {
                ans = a + b;
                return a + "" + oper + "" + b + "=?" + "n" + ans;
            } else {
                ans = Math.max(a, b) - Math.min(a, b);
                return Math.max(a, b) + "" + oper + "" + Math.min(a, b) + "=?" + "n" + ans;
            }
        }
        else if (diff >= 3) {
            String[] operations = new String[]{"*", "-", "+", "/","*","*","/","/"};
            String oper = operations[(int) round(Math.random() * 7)];
            int a = (int) ((int) 2 + Math.random() * 60);
            int b = (int) ((int) 2 + Math.random() * 60);
            int ans;
            if (oper == "+") {
                ans = a + b;
                return a + "" + oper + "" + b + "=?" + "n" + ans;
            }
            if (oper == "*") {
                a = (int) ((int) 2 + Math.random() * 8);
                b = (int) ((int) 2 + Math.random() * 8);
                ans = a * b;
                return a + "" + oper + "" + b + "=?" + "n" + ans;
            }
            if (oper == "/") {
                int c = Math.max(a, b) - (Math.max(a, b) % Math.min(a, b));
                ans = c / Math.min(a, b);
                return c + "" + oper + "" + Math.min(a, b) + "=?" + "n" + ans;
            }
            if (oper == "-") {
                ans = Math.max(a, b) - Math.min(a, b);
                return Math.max(a, b) + "" + oper + "" + Math.min(a, b) + "=?" + "n" + ans;
            }
        }
        return "1n1";
    }
    public String generateInequality(int diff) {
        String[] operations = new String[]{">", "<"};
        String oper = operations[(int) round(Math.random())];
        String ans;
        if (diff == 1) {
            int a = (int) ((int) 1 + Math.random() * 10);
            int b = (int) ((int) 1 + Math.random() * 10);
            if(a==b){
                a +=1;
            }
            if (oper == ">") {
                if (a > b) {
                    ans = "1";
                } else {
                    ans = "0";
                }
                return a + "" + oper + "" + b + "n" + ans;
            }
            if (oper == "<") {
                if (a < b) {
                    ans = "1";
                } else {
                    ans = "0";
                }
                return a + "" + oper + "" + b + "n" + ans;
            }
        }
        else if (diff >= 2){
            if(diff > 3){
                diff = 3;
            }
            String[] problem = this.generateTaskEs(diff).split("n");
            String example = problem[0];
            example = example.substring(0, example.length() - 2);
            int answer  = Integer.parseInt(problem[1]);
            int rightNum = (int) (9 + Math.random() * 41);
            if (oper == ">") {
                if (answer > rightNum) {
                    ans = "1";
                } else {
                    ans = "0";
                }
                return example + "" + oper + "" + rightNum + "n" + ans;
            }
            if (oper == "<") {
                if (answer < rightNum) {
                    ans = "1";
                } else {
                    ans = "0";
                }
                return example + "" + oper + "" + rightNum + "n" + ans;
            }
        }
        return null;
    }
}


