package com.example.calc;
import static java.lang.Math.max;
import static java.lang.Math.round;
;import java.util.ArrayList;

public class Game {
    long timeToFinish = 30000;
    int difficulty = 1;
    int trueAns = 0;
    int falseAns = 0;
    int curAns = 0;
    public int getTaskNumber(){
        return curAns;
    }
    public void checkRight(int thAns, int rAns){
        if (thAns == rAns){
            trueAns += 1;
            curAns += 1;
            difficulty = (int) Math.ceil((float)curAns / 4.0);
            timeToFinish = (long)(1000 * (10 + 10 / Math.sqrt((double)this.curAns)));
        }
        else{
            curAns += 1;
            falseAns += 1;
            difficulty = (int) Math.ceil((float)curAns / 4.0);
            timeToFinish = (long)(1000 * (10 + 10 / Math.sqrt((double)this.curAns)));
        }
    }
    public void timeIsOut() {
        falseAns += 1;
        curAns += 1;
        difficulty = (int) Math.ceil((float)curAns / 4.0);
        timeToFinish = (long)(1000 * (10 + 10 / Math.sqrt((double)this.curAns)));
    }

    public String[] getTask(int difficulty){
        String res = generateTaskEs(difficulty);
        return res.split("n");
    }
    public String[] getInequality(int difficulty){
        String res = generateInequality(difficulty);
        return res.split("n");
    }
    public void reset(){
        curAns = 0;
        trueAns = 0;
        falseAns = 0;
        difficulty = 1;
    }
    public String generateTaskEs(int diff) {
        if (diff == 1) {
            String[] operations = new String[]{"+", "-"};
            String oper = operations[(int) round(Math.random())];
            int a = (int) ((int) 1 + Math.random() * 10);
            int b = (int) ((int) 1 + Math.random() * 10);
            String k  = Integer.toString(Math.max(a, b)) + oper + Integer.toString(Math.min(a, b));
            int ans = (int) eval(k);
            return Math.max(a, b) + "" + oper + "" + Math.min(a, b) + "=?" + "n" + ans;
        }
        else if (diff == 2) {
            String[] operations = new String[]{"+", "-"};
            String oper = operations[(int) round(Math.random())];
            int a = (int) ((int) 10 + Math.random() * 40);
            int b = (int) ((int) 10 + Math.random() * 40);
            String k  = Integer.toString(Math.max(a, b)) + oper + Integer.toString(Math.min(a, b));
            int ans = (int) eval(k);
            return Math.max(a, b) + "" + oper + "" + Math.min(a, b) + "=?" + "n" + ans;

        }
        else if (diff == 3) {
            String[] operations = new String[]{"*", "-", "+", "/"};
            String oper = operations[(int) round(Math.random() * 3)];
            int a = (int) (2 + Math.random() * 60);
            int b = (int) (2 + Math.random() * 30);
            String k  = Integer.toString(Math.max(a, b)) + oper + Integer.toString(Math.min(a, b));
            if(oper == "*"){
                a = (int) (2 + Math.random() * 20);
                b = (int) (2 + Math.random() * 20);
                k  = a + oper + b;
                int ans = (int) eval(k);
                return a + "" + oper + "" + b + "=?" + "n" + ans;
            }
            else if(oper == "/"){
                int delimoe = (int) (2 + Math.random() * 60);
                ArrayList<Integer> delitel = findDels(delimoe);
                a = delitel.get((int) round(Math.random() * delitel.size()) % delitel.size());
                if(a == delimoe){
                    delimoe = delimoe*(int) (2 + Math.random() * 5);
                }
                k  = delimoe + oper + a;
                int ans = (int) eval(k);
                return delimoe + "" + oper + "" + a + "=?" + "n" + ans;

            }
            int ans = (int) eval(k);
            return Math.max(a, b) + "" + oper + "" + Math.min(a, b) + "=?" + "n" + ans;
        } else if (diff >= 4) {
            String oper = "+";
            String a = getTask(1)[0];
            String b = getTask(1)[0];
            String c = a.substring(0,a.length() - 2);
            String d = b.substring(0,b.length() - 2);
            String k = c + oper + d;
            int ans = (int) eval(k);
            return c + "" + oper + "" + d + "=?" + "n" + ans;
        }

        return "1n1";
    }
    public ArrayList<Integer> findDels(int number) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 2; i <= round(Math.sqrt(number)); i++) {
            if (number % i == 0) {
                result.add(i);
                if (!result.contains(number / i)) {
                    result.add(number / i);
                }
            }
        }
        if(result.size() == 0){
            result.add(number);
        }
        return result;
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
        else if (diff == 2){
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
        else if(diff == 3){
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
        else if(diff >= 4){
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

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}

