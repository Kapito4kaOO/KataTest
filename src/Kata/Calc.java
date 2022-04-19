package Kata;
import java.util.Scanner;

public class Calc {

    public static void main(String[] args){
        System.out.println("Введите выражение:");
        Scanner StrInitial = new Scanner(System.in);
        String str = StrInitial.nextLine().replaceAll("\\s","");  // Убираем из введенного выражения пробелы
        Main calculator = new Main();
        String answer = calculator.calc(str); // Получаем строку- решение выражения или строку с текстом ошибки
        System.out.println("Ответ: " + answer); // Выводим ответ
    }
}


