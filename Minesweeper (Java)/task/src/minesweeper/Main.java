package minesweeper;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    final static int height = 9;
    final static int width = 9;
    final static Cell[][] cells = new Cell[height + 2][width + 2];
    static int countMines;
    static int markedMines = 0;
    static int markedSpace = 0;

    static int opened = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many mines do you want on the field? ");
        countMines = sc.nextInt();
        init(countMines);
        print(false);

        while (!((countMines == markedMines && markedSpace == 0) || opened == height * width - countMines)) {
            System.out.print("Set/unset mines marks or claim a cell as free: ");
            int x = sc.nextInt();
            int y = sc.nextInt();
            String command = sc.next();
            if (Objects.equals(command, "mine")) {
                if (cells[y][x].isMarked) {
                    cells[y][x].isMarked = false;
                    if (cells[y][x].isMine) {
                        markedMines--;
                    } else {
                        markedSpace--;
                    }
                } else {
                    cells[y][x].isMarked = true;
                    if (cells[y][x].isMine) {
                        markedMines++;
                    } else {
                        markedSpace++;
                    }
                }
            } else if (Objects.equals(command, "free")) {
                if (cells[y][x].isMine) {
                    print(true);
                    System.out.println("You stepped on a mine and failed!");
                    return;
                } else {
                    open(y, x);
                }
            }
            print(false);
        }
        System.out.println("Congratulations! You found all the mines!");
    }

    private static void open(int y, int x) {
        cells[y][x].isOpened = true;
        opened++;
        if (cells[y][x].isMarked) {
            cells[y][x].isMarked = false;
        }
        if (cells[y][x].value == 0) {
            for (int j = y - 1; j < y + 2; j++) {
                for (int i = x - 1; i < x + 2; i++) {
                    if (j > 0 && j < height + 1 && i > 0 && i < width + 1 && !cells[j][i].isOpened) {
                        open(j, i);
                    }
                }
            }
        }
    }

    private static void print(boolean isGameOver) {
        System.out.print(" |");
        for (int i = 1; i < width + 1; i++) {
            System.out.print(i);
        }
        System.out.println("|");

        System.out.print("-|");
        for (int i = 1; i < width + 1; i++) {
            System.out.print("-");
        }
        System.out.println("|");

        for (int j = 1; j < height + 1; j++) {
            System.out.print(j + "|");
            for (int i = 1; i < width + 1; i++) {
                if (isGameOver && cells[j][i].isMine) {
                    System.out.print("X");
                } else if (cells[j][i].isMarked) {
                    System.out.print("*");
                } else if (cells[j][i].isOpened && cells[j][i].value == 0) {
                    System.out.print("/");
                } else if (cells[j][i].isOpened && cells[j][i].value > 0) {
                    System.out.print(cells[j][i].value);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("|");
        }

        System.out.print("-|");
        for (int i = 1; i < width + 1; i++) {
            System.out.print("-");
        }
        System.out.println("|");
    }

    private static int calculate(int y, int x) {
        int result = 0;
        for (int j = y - 1; j < y + 2; j++) {
            for (int i = x - 1; i < x + 2; i++) {
                result += cells[j][i].isMine ? 1 : 0;
            }
        }
        return result;
    }

    private static void init(int number) {
        for (int j = 0; j < height + 2; j++) {
            for (int i = 0; i < width + 2; i++) {
                cells[j][i] = new Cell();
            }
        }

        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int x = random.nextInt(width) + 1;
            int y = random.nextInt(height) + 1;
            if (cells[y][x].isMine) {
                i--;
            } else {
                cells[y][x].isMine = true;
            }
        }

        for (int j = 1; j < height + 1; j++) {
            for (int i = 1; i < width + 1; i++) {
                if (!cells[j][i].isMine) {
                    cells[j][i].value = calculate(j, i);
                }
            }
        }
    }

    static class Cell {
        int value;
        boolean isMarked;

        boolean isOpened;

        boolean isMine;

    }
}
