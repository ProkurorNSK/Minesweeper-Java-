package minesweeper;

import java.util.Random;
import java.util.Scanner;

public class Main {
    final static int height = 9;
    final static int width = 9;
    final static char[][] field = new char[height][width];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many mines do you want on the field?");
        init(sc.nextInt());
        print();
    }

    private static void print() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                System.out.print(field[j][i]);
            }
            System.out.println();
        }
    }

    private static void init(int number) {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                field[j][i] = '.';
            }
        }

        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (field[y][x] == 'X') {
                i--;
            } else {
                field[y][x] = 'X';
            }
        }
    }
}
