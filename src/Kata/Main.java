package Kata;

import static java.lang.Integer.parseInt;

public class Main {

    static boolean numArabic;

    public static String calc(String input) {

        char operator;
        int num1, num2;
        int res;
        String answer = error();

        operator = searchOperator(input); // Вычисляем оператор выражения
        if (operator!='0') {
            int operatorIndex = input.indexOf(operator);

            num1 = searchNumber(input, 0, operatorIndex); // Получаем первый операнд
            boolean arabic = numArabic;

            if (num1!=0) {
                num2 = searchNumber(input, operatorIndex + 1, input.length());  // Получаем второй операнд

                if (arabic==numArabic && num2!=0){  // если числа только арабские или только римские, вычисляем выражение
                    int result = calculation(num1, num2, operator);
                    if (numArabic) answer = Integer.toString(result); // если числа арабские, то возвращаем ответ в виде строки
                    if (!numArabic && result>0 && result<101){ // если числа риммкие, то полученный результат коныертируем в римский формат
                        answer = arabToRoman(result);
                    }
                }
            }
        }
        return answer;
    }

    static char searchOperator(String input){

        char operator='+';

        int operatorIndex = input.indexOf(operator);
        if (operatorIndex == -1){
            operatorIndex = input.indexOf('-');
            if (operatorIndex!=-1){
                operator = '-';
            }else {
                operatorIndex = input.indexOf('*');
                if (operatorIndex!=-1){
                    operator = '*';
                }else {
                    operatorIndex = input.indexOf('/');
                    if (operatorIndex != -1) {
                        operator = '/';
                    } else
                        operator='0';
                }
            }
        }
        return operator;
    }

    static int searchNumber(String input, int beginIndex, int endIndex) throws NumberFormatException  {
        String numString = input.substring(beginIndex, endIndex);
        int res = 0;

        if (isNumeric(numString) && checkNum(numString)) {
            numArabic = true;
            res = parseInt(numString);
        }
        else {
            int numRoman = isRoman(numString);
            if (numRoman != 0) {
                numArabic = false;
                res = numRoman;
            }
        }
        return res;
    }

    public static boolean isNumeric(String numString) {
        try {
            Integer.parseInt(numString);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static int isRoman(String numString) {

        int res = 0;
        try {
            Roman numRoman =  Roman.valueOf(numString);
            res = numRoman.getArabic();
        } catch (NumberFormatException e) {
            System.out.println("Введено некорректное выражение");
        }
        return res;
    }

    static boolean checkNum(String numString) {
        boolean result = true;

        int num = parseInt(numString);
        if (num < 1 || num > 10) {
            result = false;
        }

        return result;
    }

    static int calculation(int num1, int num2, char operator){

        int res;
        switch(operator) {
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num1-num2;
                break;
            case '*':
                res = num1*num2;
                break;
            default: res = num1/num2;
        }
        return res;
    }

    static String error() {
        return "Введено некорректное выражение";
    }

    public static String arabToRoman(int res) {

        String s = "";

        if (res == 100) {
            s = "C";
            res -= 100;
        }

        while (res >= 90) {
            s += "XC";
            res -= 90;
        }
        while (res >= 50) {
            s += "L";
            res -= 50;
        }
        while (res >= 40) {
            s += "XL";
            res -= 40;
        }
        while (res >= 10) {
            s += "X";
            res -= 10;
        }
        while (res >= 9) {
            s += "IX";
            res -= 9;
        }
        while (res >= 5) {
            s += "V";
            res -= 5;
        }
        while (res >= 4) {
            s += "IV";
            res -= 4;
        }
        while (res >= 1) {
            s += "I";
            res -= 1;
        }
        return s;
    }
}





