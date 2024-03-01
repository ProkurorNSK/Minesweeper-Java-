package minesweeper;

import java.util.Random;
import java.util.Scanner;

public class Main {
    final static int height = 11;
    final static int width = 11;
    final static int[][] field = new int[height][width];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many mines do you want on the field?");
        init(sc.nextInt());
        print();
    }

    private static void print() {
        for (int j = 1; j < height - 1; j++) {
            for (int i = 1; i < width - 1; i++) {
                switch (field[j][i]) {
                    case 0 -> System.out.print(".");
                    case 9 -> System.out.print("X");
                    default -> System.out.print(field[j][i]);
                }
            }
            System.out.println();
        }
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
