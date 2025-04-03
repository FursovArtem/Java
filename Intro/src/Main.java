import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //easter();
        //palindrome();
        //fib();
    }

    static void easter() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите год: ");
        int year = sc.nextInt();
        int a = year % 19;
        int b = year % 4;
        int c = year % 7;
        int d = (19 * a + 15) % 30;
        int e = (2 * b + 4 * c + 6 * d + 6) % 7;
        int f = d + e;
        System.out.printf("Пасха %d %s %d%n", f <= 26 ? f + 4 : f - 26, f <= 26 ? "апреля" : "мая", year);
    }

    static void palindrome() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число: ");
        String s = sc.nextLine();
        System.out.println(new StringBuilder(s).reverse().toString().equals(s) ? "Это палиндром" : "Это не палиндром");
    }

    static void fib() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Предел: ");
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1));
        for (int i = 1; ; i++) {
            int next = list.get(i) + list.get(i - 1);
            if (next <= n) list.add(next);
            else break;
        }
        for (int i : list) {
            if (i <= n) System.out.print(i + " ");
        }
    }
}





