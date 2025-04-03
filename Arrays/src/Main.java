import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Arrays!");
        Random rand = new Random(0);
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите размер массива: ");
        /*int n = 5; //sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(0, 100);
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
        System.out.println("Сумма элементов массива: " + IntStream.of(arr).sum());
        System.out.println("Среднее арифметическое: " + IntStream.of(arr).average().getAsDouble());
        System.out.println("Минимальное значение: " + IntStream.of(arr).min().getAsInt());
        System.out.println("Максимальное значение: " + IntStream.of(arr).max().getAsInt());*/

        System.out.print("Введите количество строк: ");
        int rows = sc.nextInt();
        System.out.println("Введите количество столбцов: ");
        int cols = sc.nextInt();
        int[][] arr = new int[rows][cols];
        for (int i=0 ; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
            {
                arr[i][j]=rand.nextInt(100);
            }
        }
        for (int i=0 ; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
            {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }

        int[] flatten = Arrays.stream(arr)
                .flatMapToInt(Arrays::stream)
                .toArray();
        System.out.println("Сумма элементов массива: " + IntStream.of(flatten).sum());
        System.out.println("Среднее арифметическое: " + IntStream.of(flatten).average().getAsDouble());
        System.out.println("Минимальное значение: " + IntStream.of(flatten).min().getAsInt());
        System.out.println("Максимальное значение: " + IntStream.of(flatten).max().getAsInt());

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        for (int i=0 ; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
            {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }

    }
}