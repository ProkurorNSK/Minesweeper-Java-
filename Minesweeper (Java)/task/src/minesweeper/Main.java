package minesweeper;

import java.util.Random;
import java.util.Scanner;

public class Main {
    final static int height = 11;
    final static int width = 11;
    final static int[][] field = new int[height][width];
    static int countMines;
    static int markedMines = 0;
    static int markedSpace = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many mines do you want on the field? ");
        countMines = sc.nextInt();
        init(countMines);
        print();

        while (countMines != markedMines || markedSpace != 0) {
            System.out.print("Set/delete mines marks (x and y coordinates): ");
            int x = sc.nextInt();
            int y = sc.nextInt();
            switch (field[y][x]) {
                case 0 -> {
                    field[y][x] = 10;
                    markedSpace++;
                }
                case 10 -> {
                    field[y][x] = 0;
                    markedSpace--;
                }
                case 9 -> {
                    field[y][x] = 19;
                    markedMines++;
                }
                case 19 -> {
                    field[y][x] = 9;
                    markedMines--;
                }
                default -> {
                    System.out.println("There is a number here!");
                    continue;
                }
            }
            print();
        }
        System.out.println("Congratulations! You found all the mines!");
    }

    private static void print() {
        System.out.print(" |");
        for (int i = 1; i < width - 1; i++) {
            System.out.print(i);
        }
        System.out.println("|");

        System.out.print("-|");
        for (int i = 1; i < width - 1; i++) {
            System.out.print("-");
        }
        System.out.println("|");

        for (int j = 1; j < height - 1; j++) {
            System.out.print(j + "|");
            for (int i = 1; i < width - 1; i++) {
                switch (field[j][i]) {
                    case 0, 9 -> System.out.print(".");
                    case 10, 19 -> System.out.print("*");
                    default -> System.out.print(field[j][i]);
                }
            }
            System.out.println("|");
        }

        System.out.print("-|");
        for (int i = 1; i < width - 1; i++) {
            System.out.print("-");
        }
        System.out.println("|");
    }

    private static int calculate(int y, int x) {
        int result = 0;
        for (int j = y - 1; j < y + 2; j++) {
            for (int i = x - 1; i < x + 2; i++) {
                result += field[j][i] == 9 ? 1 : 0;
            }
        }
        return result;
    }

    private static void init(int number) {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                field[j][i] = 0;
            }
        }

        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int x = random.nextInt(width - 2) + 1;
            int y = random.nextInt(height - 2) + 1;
            if (field[y][x] == 9) {
                i--;
            } else {
                field[y][x] = 9;
            }
        }

        for (int j = 1; j < height - 1; j++) {
            for (int i = 1; i < width - 1; i++) {
                if (field[j][i] != 9) {
                    field[j][i] = calculate(j, i);
                }
            }
        }
    }
}
